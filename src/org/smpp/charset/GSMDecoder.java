package org.smpp.charset;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

/**
 * Created with IntelliJ IDEA.
 * User: c
 * Date: 23.09.13
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */
public class GSMDecoder extends CharsetDecoder {

    private GSMCharset charset;

    public GSMDecoder(GSMCharset charset) {
        super(charset, /*averageCharsPerByte*/ 1.1f, /*maxCharsPerByte*/ 2.0f);
        this.charset = charset;
    }

    private boolean ext = false;

    @Override
    protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {

        char[] charsetBasic = this.charset.getCharsetTable();
        char[] charsetExtended = this.charset.getCharsetTableExtended();

        try {

            while (true) {

                int i = in.get() & 0xff;  // here be dragons!
                if (this.ext) {
                    this.ext = false;
                    out.put(charsetExtended[i]);
                } else if (i == 0x1b) {
                    this.ext = true;
                } else {
                    out.put(charsetBasic[i]);
                }
            }
        } catch (BufferUnderflowException x) {
            return CoderResult.UNDERFLOW;
        } catch (BufferOverflowException x) {
            return CoderResult.OVERFLOW;
        }
    }
}
