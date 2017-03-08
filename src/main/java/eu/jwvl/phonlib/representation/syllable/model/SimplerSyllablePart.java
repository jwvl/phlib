package eu.jwvl.phonlib.representation.syllable.model;

import eu.jwvl.phonlib.symbol.encoding.trie.NodeType;

import java.util.Objects;

/**
 * Created by janwillem on 05/03/2017.
 */
public class SimplerSyllablePart {
    private final String asString;
    private final NodeType nodeType;
    private final int id;
    private final byte[] contents;

    public SimplerSyllablePart(NodeType nodeType, String asString, byte[] contents, int counter) {
        this.asString = asString;
        this.nodeType = nodeType;
        this.contents = contents;
        this.id = counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplerSyllablePart that = (SimplerSyllablePart) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return asString;
    }
}

