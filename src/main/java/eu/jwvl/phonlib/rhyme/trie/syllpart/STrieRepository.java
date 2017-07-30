package eu.jwvl.phonlib.rhyme.trie.syllpart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Created by janwillem on 15/04/2017.
 */
public class STrieRepository {
    private final Multimap<STrieNode,String> nodesToWordforms;
    private final Multimap<byte[],STrieNode> nodesByFirstByte;
    private final STrieNode root;

    public STrieRepository() {
        nodesToWordforms = HashMultimap.create();
        root = new STrieNode(new byte[0],null);
        nodesByFirstByte = HashMultimap.create();
    }

    public void addByteString(byte[][] syllables, String wordForm) {
        STrieNode next = root;
        for (int i=0; i < syllables.length; i++) {
            byte[] currentValue = syllables[i];
            next = next.getOrAddSuccessor(currentValue);
            if (currentValue.length > 0) {
                nodesByFirstByte.put(currentValue,next);
            }
        }
        nodesToWordforms.put(next,wordForm);
    }

    public STrieNode getRoot() {
        return root;
    }
}
