package org.smpp.pdu;

import org.smpp.PDU;
import org.smpp.PDUDecoder;
import org.smpp.PDUEncoder;

/**
 * Created by c on 3/4/14.
 */
public class BindTransmiter extends PDU {

    final static long BIND_TRANSMITTER = 0x00000002;

    /**
     * max 16
     */
    String systemId = "";
    /**
     * max 9
     */
    String password = "";
    /**
     * max 13
     */
    String systemType = "";

    byte interfaceVersion;
    byte addrTon;
    byte addrNpi;
    /**
     * max 41
     */
    String addressRange = "";

    public BindTransmiter() {
    }

    public BindTransmiter(PDUDecoder decoder) {
        decodeHeader(decoder);
        decode(decoder);
    }

    public PDUEncoder encode() {
        PDUEncoder encoder = new PDUEncoder();
        encodeHeader(encoder);
        encoder.writeString(getSystemId());
        encoder.writeString(getPassword());
        encoder.writeString(getSystemType());
        encoder.writeByte(getInterfaceVersion());
        encoder.writeByte(getAddrTon());
        encoder.writeByte(getAddrNpi());
        encoder.writeString(getAddressRange());

        encoder.seal();

        return encoder;
    }

    private void decode(PDUDecoder decoder) {
        setSystemId(decoder.readString(16));
        setPassword(decoder.readString(9));
        setSystemType(decoder.readString(13));
        setInterfaceVersion(decoder.readUInt8());
        setAddrTon(decoder.readUInt8());
        setAddrNpi(decoder.readUInt8());
        setAddressRange(decoder.readString(41));
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public byte getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(byte interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public byte getAddrTon() {
        return addrTon;
    }

    public void setAddrTon(byte addrTon) {
        this.addrTon = addrTon;
    }

    public byte getAddrNpi() {
        return addrNpi;
    }

    public void setAddrNpi(byte addrNpi) {
        this.addrNpi = addrNpi;
    }

    public String getAddressRange() {
        return addressRange;
    }

    public void setAddressRange(String addressRange) {
        this.addressRange = addressRange;
    }

    @Override
    public long getCommandID() {
        return BIND_TRANSMITTER;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (this.getClass() != object.getClass()) {
            return false;
        }

        BindTransmiter pdu = (BindTransmiter) object;

        if (!getSystemId().equals(pdu.getSystemId())) {
            return false;
        }

        if (!getPassword().equals(pdu.getPassword())) {
            return false;
        }

        if (!getSystemType().equals(pdu.getSystemType())) {
            return false;
        }

        if (getInterfaceVersion() != pdu.getInterfaceVersion()) {
            return false;
        }

        if (getAddrTon() != pdu.getAddrTon()) {
            return false;
        }

        if (getAddrNpi() != pdu.getAddrNpi()) {
            return false;
        }

        if (!getAddressRange().equals(pdu.getAddressRange())) {
            return false;
        }

        return true;
    }
}