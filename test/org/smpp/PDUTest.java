package org.smpp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class PDUTest {

    @Test
    public void decoderEquals() {

        PDUEncoder encoder = new PDUEncoder();

        encoder.writeUInt32(0);
        encoder.writeUInt32(0);
        encoder.writeUInt32(0);
        encoder.writeUInt32(0);

        encoder.seal();

        encoder.dump();

        assertEquals(16, encoder.length());

        byte[] expected = new byte[]{
                0x00, 0x00, 0x00, 0x10,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00
        };

        assertArrayEquals(expected, Arrays.copyOfRange(encoder.getBytes(), 0, encoder.length()));
    }

    @Test
    public void encodeDecode() {

        PDUEncoder encoder = new PDUEncoder();

        encoder.writeUInt32(0);
        encoder.writeUInt32(Integer.MAX_VALUE);
        encoder.writeUInt32(0x12345678l);
        encoder.writeUInt32(0xFFFFFFFFl);

        encoder.writeUInt32(0xAFBFCFDFl);
        encoder.writeUInt16(0x9F8F);
        encoder.writeUInt8((short) 255);

        encoder.seal();

        encoder.dump();

        assertEquals(23, encoder.length());

        PDUDecoder decoder = new PDUDecoder();

        int read = decoder.write( encoder.getBytes(), encoder.length() );
        assertEquals(encoder.length(), read);
        decoder.rewind();

        assertEquals(23, decoder.getLength());
        assertEquals(0x7FFFFFFFl, decoder.getId());
        assertEquals(0x12345678l, decoder.getStatus());
        assertEquals(0xFFFFFFFFl, decoder.getSequence());

        assertEquals(0xAFBFCFDFl, decoder.readUInt32());
        assertEquals(0x9F8F, decoder.readUInt16());
        assertEquals(255, decoder.readUInt8());
    }
}
