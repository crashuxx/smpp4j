package org.smpp;

/**
 * Created by c on 12/12/13.
 */
public class PDU {

/*    private long offset = 0;

    private byte head[] = new byte[16];
    private byte body[] = null;
*/
    private long sequence = 0;
    private long status = 0;

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
