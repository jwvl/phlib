package eu.jwvl.phonlib.symbol.encoding.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by janwillem on 20/06/16.
 */
public class TrieLeaf extends TrieNode{
    private final List<String> strings;

    public TrieLeaf(NodeType type) {
        super(type);
        strings = new ArrayList<>();
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Set<String> getWords() {
        return null;
    }

    @Override
    public TrieNode getChild(byte code) {
        return null;
    }
}
