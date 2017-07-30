package eu.jwvl.phonlib.rhyme.trie.syllpart;

import com.google.common.collect.Lists;
import eu.jwvl.phonlib.representation.segment.StringPhoneMap;

import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by janwillem on 05/05/2017.
 */
public class STrieMatchFinder {

    private STrieRepository repository;
    private PriorityQueue<SCostNode> queue;
    private final SyllCostFunction costFunction;
    private List<SCostNode> finished;
    private StringPhoneMap phoneMap;

    public STrieMatchFinder(STrieRepository repository, SyllCostFunction costFunction, StringPhoneMap phoneMap) {
        this.repository = repository;
        this.queue = new PriorityQueue<>();
        this.costFunction = costFunction;
        this.finished = Lists.newArrayList();
        this.phoneMap = phoneMap;
    }

    public List<SCostNode> getBestN(int n, byte[] toMatch) {
        SCostNode root = new SCostNode(0.0,-1,repository.getRoot());
        queue.add(root);
        while(finished.size() < n && !queue.isEmpty()) {
            expandFirstInQueue(toMatch);
        }
        return finished;
    }

    private void expandFirstInQueue(byte[] toMatch) {
        SCostNode toExpand = queue.poll();
        if (toExpand.getNode().isTerminal()) {
            finished.add(toExpand);
            byte[][] bytes = toExpand.getBytesRecursive();
           // System.out.println(phoneMap.bytesToString(bytes) + ", cost: "+toExpand.getCost());
            return;
        }
        Collection<STrieNode> nodes = toExpand.getNode().getSuccessors();
        int successorPosition = toExpand.getPosition()+1;
        byte byteToMatch = toMatch[successorPosition];
        double oldCost = toExpand.getCost();
        for (STrieNode node: nodes) {
            byte[] successorByte = node.value();
           // double addedCost = costFunction.getTransformCost(byteToMatch,successorByte,successorPosition);
            double addedCost = 0;
            SCostNode child = new SCostNode(oldCost+addedCost, successorPosition, node);
            queue.add(child);
        }
    }

    public String printCurrentBest() {
        SCostNode currentBest = queue.peek();
        //byte[] bytes = currentBest.getBytesRecursive();
        return "NOT IMPLEMENTED YET"; //phoneMap.bytesToString(bytes) + ", cost: "+currentBest.getCost();
    }
}
