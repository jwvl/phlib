package eu.jwvl.phonlib.symbol.encoding.trie;

import eu.jwvl.phonlib.symbol.encoding.ByteCoder;

/**
 * Created by janwillem on 20/06/16.
 */
public class PhoneTrie {
    private final ByteCoder byteCoder;

    public PhoneTrie(ByteCoder byteCoder) {
        this.byteCoder = byteCoder;
    }
}
