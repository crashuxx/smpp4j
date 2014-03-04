package org.smpp.charset;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

/**
 * Created with IntelliJ IDEA.
 * User: c
 * Date: 23.09.13
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class GSMEncoder extends CharsetEncoder {

    private GSMCharset charset;

    public GSMEncoder(GSMCharset charset) {
        super(charset, /*averageBytesPerChar*/ 1.1f, /*maxBytesPerChar*/ 2.0f);
        this.charset = charset;
    }

    @Override
    protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {

        try {

            while (true) {
                out.put(this.getByteForChar(in.get()));
            }
        } catch (BufferUnderflowException x) {
            return CoderResult.UNDERFLOW;
        } catch (BufferOverflowException x) {
            return CoderResult.OVERFLOW;
        }
    }

    private byte[] getByteForChar(char c) {

        char[] charsetBasic = this.charset.getCharsetTable();

        for (int i = 0; i < charsetBasic.length; i++) {
            if (charsetBasic[i] == c && i != 0x1b) {
                return new byte[]{(byte) i};
            }
        }

        char[] charsetExtended = this.charset.getCharsetTableExtended();
        for (int i = 0; i < charsetExtended.length; i++) {
            if (charsetExtended[i] == c) {
                return new byte[]{0x1b, (byte) i};
            }
        }

        return new byte[]{0x60};
    }

    public boolean canEncode(char c) {
        return true;
    }

    public boolean isLegalReplacement(byte[] repl) {
        return true;
    }
}
