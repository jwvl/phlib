package eu.jwvl.phonlib.symbol.encoding.trie;

import java.util.Set;

/**
 * Created by janwillem on 20/06/16.
 */
public abstract class TrieNode {
    protected TrieNode(NodeType nodeType) {
        this.type = nodeType;
    }

    public abstract boolean isLeaf();
    public abstract Set<String> getWords();
    public abstract TrieNode getChild(byte code);
    public final NodeType type;

}
