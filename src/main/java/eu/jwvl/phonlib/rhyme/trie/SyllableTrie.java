package eu.jwvl.phonlib.rhyme.trie;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import eu.jwvl.phonlib.representation.orth.OrthWord;
import eu.jwvl.phonlib.representation.syllable.model.SyllablePart;

import java.util.Map;
import java.util.Set;

/**
 * Created by janwillem on 02/09/16.
 */
public class SyllableTrie {
    Map<SyllablePart,SyllableTrie> children;
    Set<OrthWord> leaves;

    private SyllableTrie(Map<SyllablePart, SyllableTrie> children, Set<OrthWord> leaves) {
        this.children = children;
        this.leaves = leaves;
    }

    public static SyllableTrie create() {
        return new SyllableTrie(Maps.newHashMap(), Sets.newHashSet());
    }
}
