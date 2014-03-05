package org.smpp;

/**
 * Created by c on 12/12/13.
 */
public abstract class PDU {

    private long commandStatus;
    private long commandSequenceNumber;

    public abstract long getCommandID();
    public abstract PDUEncoder encode();

    public long getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(long commandStatus) {
        this.commandStatus = commandStatus;
    }

    public long getCommandSequenceNumber() {
        return commandSequenceNumber;
    }

    public void setCommandSequenceNumber(long commandSequenceNumber) {
        this.commandSequenceNumber = commandSequenceNumber;
    }

    protected void decodeHeader(PDUDecoder decoder) {
        setCommandStatus(decoder.getStatus());
        setCommandSequenceNumber(decoder.getSequenceNumber());
    }

    protected void encodeHeader(PDUEncoder encoder) {
        encoder.writeUInt32(0); // skip length
        encoder.writeUInt32(getCommandID());
        encoder.writeUInt32(getCommandStatus());
        encoder.writeUInt32(getCommandSequenceNumber());
    }
}
