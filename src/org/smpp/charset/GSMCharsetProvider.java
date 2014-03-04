package org.smpp.charset;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

public class GSMCharsetProvider extends java.nio.charset.spi.CharsetProvider {

    private static final String CHARSET_NAME = "GSM";

    private Charset charset = null;

    public GSMCharsetProvider() {
        this.charset = new GSMCharset(this.CHARSET_NAME, new String[]{"GSM", "gsm"});
    }

    public Charset charsetForName(String charsetName) {

        if (charsetName.equalsIgnoreCase(this.CHARSET_NAME)) {
            return this.charset;
        }

        return null;
    }

    /**
     * Return an Iterator over the set of Charset objects we provide.
     *
     * @return An Iterator object containing references to all the
     * Charset objects provided by this class.
     */
    public Iterator charsets() {
        ArrayList list = new ArrayList();
        list.add(this.charset);
        return list.iterator();
    }
}
