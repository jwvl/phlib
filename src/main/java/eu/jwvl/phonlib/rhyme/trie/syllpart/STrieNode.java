package eu.jwvl.phonlib.rhyme.trie.syllpart;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Created by janwillem on 05/05/2017.
 */
public class STrieNode {
    private final byte[] value;
    private final Map<byte[],STrieNode> successors;
    private final STrieNode parent;
    private final long id;
    private static long idCounter;

    public STrieNode(byte[] value, STrieNode parent) {
        this.value = value;
        this.parent = parent;
        this.id = idCounter++;
        successors = Maps.newHashMapWithExpectedSize(8);
    }

    public STrieNode getOrAddSuccessor(byte[] value) {
        STrieNode successor = successors.get(value);
        if (successor == null) {
            successor = new STrieNode(value, this);
            successors.put(value,successor);
        }
        return successor;
    }

    public boolean isTerminal() {
        return successors.isEmpty();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public Collection<STrieNode> getSuccessors() {
        return successors.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        STrieNode sTrieNode = (STrieNode) o;
        return id == sTrieNode.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public byte[] value() {
        return value;
    }

    public STrieNode getParent() {
        return parent;
    }
}
