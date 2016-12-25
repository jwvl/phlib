package eu.jwvl.phonlib.representation.syllable.model;

import eu.jwvl.phonlib.representation.syllable.stress.Stress;
import eu.jwvl.phonlib.symbol.encoding.trie.NodeType;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by janwillem on 20/06/16.
 */
public class SyllablePart {
    private final NodeType type;
    private final byte[] contents;
    private final Stress stress;

    public SyllablePart(NodeType type, byte[] contents, Stress stress) {
        this.type = type;
        this.contents = contents;
        this.stress = stress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SyllablePart that = (SyllablePart) o;
        return type == that.type  &&
                stress == that.stress &&
                Arrays.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, contents, stress);
    }
}
