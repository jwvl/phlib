package eu.jwvl.phonlib.rhyme.trie.bcode;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;

/**
 * Created by janwillem on 15/04/2017.
 */
public class BTrieRepository {
    private final Multimap<BTrieNode,String> nodesToWordforms;
    private final Multimap<Byte,BTrieNode> nodesByFirstByte;
    private final BTrieNode root;

    public BTrieRepository() {
        nodesToWordforms = HashMultimap.create();
        root = new BTrieNode((byte)-1,null);
        nodesByFirstByte = HashMultimap.create();
    }

    public void addByteString(byte[] asBytes, String wordForm) {
        BTrieNode next = root;
        for (int i=0; i < asBytes.length; i++) {
            byte currentValue = asBytes[i];
            next = next.getOrAddSuccessor(currentValue);
            if (currentValue != 0) {
                nodesByFirstByte.put(currentValue,next);
            }
        }
        nodesToWordforms.put(next,wordForm);
    }

    public BTrieNode getRoot() {
        return root;
    }

    public Collection<BTrieNode> getAllForByte(byte initialByte) {
        return nodesByFirstByte.get(initialByte);
    }

    public Collection<String> getWordforms(BTrieNode terminal) {
        return nodesToWordforms.get(terminal);
    }
}
