package eu.jwvl.phonlib.rhyme.trie.bcode;

import com.google.common.collect.Lists;
import eu.jwvl.phonlib.representation.segment.StringPhoneMap;

import java.util.List;
import java.util.Stack;

/**
 * Created by janwillem on 06/05/2017.
 */
public class BTrieSequenceFinder {
    private BTrieRepository repository;
    private Stack<SearchNode> nodeStack;
    private StringPhoneMap stringPhoneMap;
    private List<BTrieNode> found;

    public BTrieSequenceFinder(BTrieRepository repository, StringPhoneMap stringPhoneMap) {
        this.repository = repository;
        this.stringPhoneMap = stringPhoneMap;
        this.found = Lists.newArrayList();
        this.nodeStack = new Stack<>();
    }

    public List<BTrieNode> getAllForSequence(String asString) {
        return getAllForSequence(stringPhoneMap.encodeAsBytes(asString));
    }

    public List<BTrieNode> getAllForSequence(byte[] sequence) {
        byte firstByte = sequence[0];
        for (BTrieNode bTrieNode: repository.getAllForByte(firstByte)) {
            nodeStack.add(new SearchNode(1, bTrieNode));
        }

        while (!nodeStack.isEmpty()) {
            SearchNode next = nodeStack.pop();
            next.expand(sequence);
        }
        return found;
    }

    private class SearchNode {
        final int index;
        final BTrieNode node;

        public SearchNode(int index, BTrieNode node) {
            this.index = index;
            this.node = node;
        }

        public void expand(byte[] sequence) {
            if (index >= sequence.length) {
                if (node.isTerminal()) {
                    found.add(node);
                }
                else {
                    for (BTrieNode successor : node.getSuccessors()) {
                        nodeStack.add(new SearchNode(index, successor));
                    }
                }
            } else {
                BTrieNode matchingSuccessor = node.getSuccessor(sequence[index]);
                if (matchingSuccessor != null) {
                    nodeStack.add(new SearchNode(index+1,matchingSuccessor));
                }
                BTrieNode zeroSuccessor = node.getSuccessor(stringPhoneMap.getPhone("-").getByteValue());
                if (zeroSuccessor != null) {
                    nodeStack.add(new SearchNode(index,zeroSuccessor));
                }
            }
        }
    }

}
