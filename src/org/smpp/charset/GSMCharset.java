package org.smpp.charset;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: c
 * Date: 23.09.13
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public class GSMCharset extends Charset {

    public GSMCharset(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    public char[] getCharsetTable() {
        return this.charsBasic;
    }

    public char[] getCharsetTableExtended() {
        return this.charsExtended;
    }

    @Override
    public boolean contains(Charset charset) {
        return equals(charset);
    }

    @Override
    public CharsetDecoder newDecoder() {
        return new GSMDecoder(this);
    }

    @Override
    public CharsetEncoder newEncoder() {
        return new GSMEncoder(this);
    }

    final char[] charsBasic = {
            '@',
            '£',
            '$',
            '¥',
            '\u00E8', //LATIN SMALL LETTER E WITH GRAVE
            '\u00E9', //LATIN SMALL LETTER E WITH ACUTE
            '\u00F9', //LATIN SMALL LETTER U WITH GRAVE
            '\u00EC', //LATIN SMALL LETTER I WITH GRAVE
            '\u00F2', //LATIN SMALL LETTER O WITH GRAVE
            '\u00C7', //LATIN CAPITAL LETTER C WITH CEDILLA
            '\n',     //LINE FEED // \u000A
            '\u00D8', //LATIN CAPITAL LETTER O WITH STROKE
            '\u00F8', //LATIN SMALL LETTER O WITH STROKE
            '\r', //CARRIAGE RETURN
            '\u00C5', //LATIN CAPITAL LETTER A WITH RING ABOVE
            '\u00E5', //LATIN SMALL LETTER A WITH RING ABOVE
            '\u0394', //GREEK CAPITAL LETTER DELTA
            '\u005F', //LOW LINE
            '\u03A6', //GREEK CAPITAL LETTER PHI
            '\u0393', //GREEK CAPITAL LETTER GAMMA
            '\u039B', //GREEK CAPITAL LETTER LAMBDA
            '\u03A9', //GREEK CAPITAL LETTER OMEGA
            '\u03A0', //GREEK CAPITAL LETTER PI
            '\u03A8', //GREEK CAPITAL LETTER PSI
            '\u03A3', //GREEK CAPITAL LETTER SIGMA
            '\u0398', //GREEK CAPITAL LETTER THETA
            '\u039E', //GREEK CAPITAL LETTER XI
            '\u0000', //ESCAPE TO EXTENSION TABLE
            '\u00C6', //LATIN CAPITAL LETTER AE
            '\u00E6', //LATIN SMALL LETTER AE
            '\u00DF', //LATIN SMALL LETTER SHARP S (German)
            '\u00C9', //LATIN CAPITAL LETTER E WITH ACUTE
            '\u0020', //SPACE
            '!', //EXCLAMATION MARK
            '"', //QUOTATION MARK
            '#', //NUMBER SIGN
            '\u00A4', //CURRENCY SIGN
            '%', //PERCENT SIGN
            '&', //AMPERSAND
            '\'', //APOSTROPHE
            '(', //LEFT PARENTHESIS
            ')', //RIGHT PARENTHESIS
            '*', //ASTERISK
            '+', //PLUS SIGN
            ',', //COMMA
            '-', //HYPHEN-MINUS
            '.', //FULL STOP
            '/', //SOLIDUS (SLASH)
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            ':', //COLON
            ';', //SEMICOLON
            '<', //LESS-THAN SIGN
            '=', //EQUALS SIGN
            '>', //GREATER-THAN SIGN
            '?', //QUESTION MARK
            '\u00A1', //INVERTED EXCLAMATION MARK
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z',
            '\u00C4', //LATIN CAPITAL LETTER A WITH DIAERESIS
            '\u00D6', //LATIN CAPITAL LETTER O WITH DIAERESIS
            '\u00D1', //LATIN CAPITAL LETTER N WITH TILDE
            '\u00DC', //LATIN CAPITAL LETTER U WITH DIAERESIS
            '\u00A7', //SECTION SIGN
            '\u00BF', //INVERTED QUESTION MARK
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z',
            '\u00E4', //LATIN SMALL LETTER A WITH DIAERESIS
            '\u00F6', //LATIN SMALL LETTER O WITH DIAERESIS
            '\u00F1', //LATIN SMALL LETTER N WITH TILDE
            '\u00FC', //LATIN SMALL LETTER U WITH DIAERESIS
            '\u00E0' //LATIN SMALL LETTER A WITH GRAVE
    };

    final private char[] charsExtended = {
            '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            //FORM FEED
            '\u000C', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            //CIRCUMFLEX ACCENT
            '\u005E', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            '{', '}', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\\', '\uFFFD', '\uFFFD',
            '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            '[', '~', ']', '\uFFFD', '|', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD', '\uFFFD',
            //EURO SIGN
            '\uFFFD', '\u20AC'
    };
}
