package eu.jwvl.phonlib.rhyme.trie.bcode;

/**
 * Created by janwillem on 16/04/2017.
 */
public class CostNode implements Comparable<CostNode> {
    private final double cost;
    private final int position;
    private final BTrieNode node;

    public CostNode(double cost, int position, BTrieNode node) {
        this.cost = cost;
        this.position = position;
        this.node = node;
    }

    @Override
    public int compareTo(CostNode o) {
        return Double.compare(cost,o.cost);
    }

    public byte[] getBytesRecursive() {
        byte[] result = new byte[position+1];
        BTrieNode toAdd = node;
        int index = position;
        while (index >= 0 && toAdd != null) {
            result[index] = toAdd.value();
            index--;
            toAdd = toAdd.getParent();
        }
        return result;
    }

    public BTrieNode getNode() {
        return node;
    }

    public int getPosition() {
        return position;
    }

    public double getCost() {
        return cost;
    }
}
