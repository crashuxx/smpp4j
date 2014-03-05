package org.smpp;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by c on 12/12/13.
 */
public class PDUDecoder {

    private ByteBuffer head;
    private ByteBuffer body = null;

    private long length = 0;
    private long id = 0;
    private long status = 0;
    private long sequence = 0;

    public PDUDecoder() {
        head = ByteBuffer.allocateDirect(16);
        head.order(ByteOrder.BIG_ENDIAN);
    }

    public long getLength() {
        return length;
    }

    public long getId() {
        return id;
    }

    public long getStatus() {
        return status;
    }

    public long getSequenceNumber() {
        return sequence;
    }

    public int write(byte[] buffer) {
        return write(buffer, buffer.length);
    }

    public int write(byte[] buffer, int bufferLength) {

        int i = 0;

        if (head.hasRemaining()) {
            i = (bufferLength > head.remaining()) ? head.remaining() : bufferLength;
            head.put(buffer, 0, i);
        }

        if (!head.hasRemaining() && body == null) {

            extractHead();

            if (length < 16) {
                // min length of pdu is 16bytes, package is corrupted
                throw new OutOfMemoryError();
            }

            if (length <= Integer.MAX_VALUE) {
                body = ByteBuffer.allocateDirect((int) (length - 16));
                body.order(ByteOrder.BIG_ENDIAN);
            } else {
                // to big to handle
                throw new OutOfMemoryError();
            }
        }

        if (body != null) {
            int read = (bufferLength - i) > body.remaining() ? body.remaining() : (bufferLength - i);
            body.put(buffer, i, read);
            i += read;
        }

        return i;
    }

    /**
     * Rewind internal position to beginning, for read or over write
     */
    public void rewind() {
        body.rewind();
    }

    private void extractHead() {
        head.rewind();

        length = head.getInt() & 0xffffffffL;
        id = head.getInt() & 0xffffffffL;
        status = head.getInt() & 0xffffffffL;
        sequence = head.getInt() & 0xffffffffL;
    }

    public byte readUInt8() {
        return body.get();
    }

    public int readUInt16() {
        return (body.getShort() & 0xffff);
    }

    public long readUInt32() {
        return (body.getInt() & 0xffffffffL);
    }

    public byte[] readCString(int maxLength) {

        int length = 0;
        body.mark();

        while (maxLength > 0 && length < maxLength) {
            if (body.get() == 0x00) {
                break;
            }
            length++;
        }

        byte[] cstring = new byte[length];

        if (length > 0) {
            body.reset();
            body.get(cstring, 0, length);
            body.get();// skip last char (0x00)
        }

        return cstring;
    }

    public String readString(int maxLength) {
        try {
            return readString(maxLength, "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readString(int maxLength, String encoding) throws UnsupportedEncodingException {

        String string = null;
        byte[] bytes = readCString(maxLength);

        if( bytes.length > 0 ) {
            string = new String(bytes, encoding);
        } else {
            string = "";
        }

        return string;
    }
}
