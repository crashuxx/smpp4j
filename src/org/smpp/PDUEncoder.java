package org.smpp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * Created by c on 15.12.13.
 */
public class PDUEncoder {

    ByteBuffer data;

    public PDUEncoder() {
        data = ByteBuffer.allocate(1024);
        data.order(ByteOrder.BIG_ENDIAN);
    }

    private void makeSpaceFor(int length) {

        while( data.remaining() < length ) {
            data.limit( data.limit()+512 );
        }
    }

    public void writeByte(byte value) {
        makeSpaceFor(1);
        data.put(value);
    }

    public void writeByte(byte[] value) {
        makeSpaceFor(value.length);
        data.put(value, 0, value.length);
    }

    public void writeUInt8(short value) {
        makeSpaceFor(1);
        data.put((byte)(value));
    }

    public void writeUInt16(int value) {
        makeSpaceFor(2);
        data.putShort((short)(value&0xffff));
    }

    public void writeUInt32(long value) {
        makeSpaceFor(1);
        data.putInt((int) (value & 0xffffffff));
    }

    public int length() {
        return data.limit();
    }

    public void seal() {
        data.flip();
        writeUInt32(data.limit());
        data.rewind();
    }

    public byte[] getBytes() {
        return data.array();
    }

    public void dump() {
        data.mark();
        int length = data.limit();

        System.out.println("Length: " + length);
        System.out.println("Hex dump:");

        for(int i = 0; i < length; i++) {
            System.out.printf("%02X ", data.get(i));
        }

        System.out.println();
        data.reset();
    }
}
