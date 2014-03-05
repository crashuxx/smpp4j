package org.smpp.pdu;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.smpp.PDUDecoder;
import org.smpp.PDUEncoder;

import javax.xml.bind.DatatypeConverter;

/**
 * Created by c on 3/5/14.
 */
@RunWith(JUnit4.class)
public class BindTransmiterTest {

    @Test
    public void encodeDecode() {

        BindTransmiter pdu = new BindTransmiter();

        pdu.setSystemId("sysid");
        pdu.setPassword("asd123");
//        bindTransmiter.setSystemType("");
        pdu.setInterfaceVersion((byte) 0x34);
        pdu.setAddrTon((byte) 48);
        pdu.setAddrNpi((byte) 0xa4);

        PDUEncoder encoder = pdu.encode();

        PDUDecoder decoder = new PDUDecoder();
        decoder.write(encoder.getBytes(), encoder.length());
        decoder.rewind();

        assertEquals(encoder.length(), decoder.getLength());

        BindTransmiter newPdu = new BindTransmiter(decoder);

        assertEquals(true, pdu.equals(newPdu));
    }

}
