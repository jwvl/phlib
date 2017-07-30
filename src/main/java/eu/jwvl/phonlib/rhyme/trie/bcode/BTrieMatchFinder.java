package eu.jwvl.phonlib.rhyme.trie.bcode;

import com.google.common.collect.Lists;
import eu.jwvl.phonlib.representation.segment.StringPhoneMap;
import eu.jwvl.phonlib.rhyme.trie.cost.ByteCostFunction;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by janwillem on 15/04/2017.
 */
public class BTrieMatchFinder {

    private BTrieRepository repository;
    private PriorityQueue<CostNode> queue;
    private final ByteCostFunction costFunction;
    private List<CostNode> finished;
    private StringPhoneMap phoneMap;
    private static boolean AVOID_EQUAL = true;

    public BTrieMatchFinder(BTrieRepository repository, ByteCostFunction costFunction, StringPhoneMap phoneMap) {
        this.repository = repository;
        this.queue = new PriorityQueue<>();
        this.costFunction = costFunction;
        this.finished = Lists.newArrayList();
        this.phoneMap = phoneMap;
    }

    public List<CostNode> getBestN(int n, byte[] toMatch) {
        clearContents();
        CostNode root = new CostNode(0.0,-1,repository.getRoot());
        queue.add(root);
        while(finished.size() < n && !queue.isEmpty()) {
            expandFirstInQueue(toMatch);
        }
        return finished;
    }

    private void expandFirstInQueue(byte[] toMatch) {
        CostNode toExpand = queue.poll();
        if (toExpand.getNode().isTerminal()) {
            byte[] bytes = toExpand.getBytesRecursive();
            if (!Arrays.equals(bytes,toMatch) || !AVOID_EQUAL) {
                finished.add(toExpand);
                System.out.println(phoneMap.bytesToString(bytes) + ", cost: " + toExpand.getCost());
            }
            return;
        }
        List<BTrieNode> nodes = toExpand.getNode().getSuccessors();
        int successorPosition = toExpand.getPosition()+1;
        byte byteToMatch = toMatch[successorPosition];
        double oldCost = toExpand.getCost();
        for (BTrieNode node: nodes) {
            byte successorByte = node.value();
            double addedCost = costFunction.getTransformCost(byteToMatch,successorByte,successorPosition);
            CostNode child = new CostNode(oldCost+addedCost, successorPosition, node);
            queue.add(child);
        }
    }

    public String printCurrentBest() {
        CostNode currentBest = queue.peek();
        byte[] bytes = currentBest.getBytesRecursive();
        return phoneMap.bytesToString(bytes) + ", cost: "+currentBest.getCost();
    }

    public void clearContents() {
        finished = Lists.newArrayList();
        queue.clear();
        return;
    }



}
