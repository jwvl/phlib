package eu.jwvl.phonlib.rhyme.trie.bcode;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * Created by janwillem on 15/04/2017.
 */
public class BTrieNode {
    private final byte value;
    private final List<BTrieNode> successors;
    private final BTrieNode parent;
    private final int id;
    private static int idCounter;


    public BTrieNode(byte value, BTrieNode parent) {
        this.value = value;
        this.parent = parent;
        this.id = idCounter++;
        successors = Lists.newArrayListWithCapacity(8);
    }

    public BTrieNode getOrAddSuccessor(byte value) {
        for (BTrieNode successor: successors) {
            if (successor.value == value) {
                return successor;
            }
        }
        BTrieNode successor = new BTrieNode(value, this);
        successors.add(successor);
        return successor;
    }

    public BTrieNode getSuccessor(byte value) {
        for (BTrieNode successor: successors) {
            if (successor.value == value)
                return successor;
        }
        return null;
    }


    public boolean isTerminal() {
        return successors.isEmpty();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public List<BTrieNode> getSuccessors() {
        return successors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTrieNode bTrieNode = (BTrieNode) o;
        return id == bTrieNode.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public byte value() {
        return value;
    }

    public BTrieNode getParent() {
        return parent;
    }
}
