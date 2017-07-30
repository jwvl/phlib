package eu.jwvl.phonlib.rhyme.trie.syllpart;

/**
 * Created by janwillem on 05/05/2017.
 */
public class SCostNode implements Comparable<SCostNode>{
    private final double cost;
    private final int position;
    private final STrieNode node;

    public SCostNode(double cost, int position, STrieNode node) {
        this.cost = cost;
        this.position = position;
        this.node = node;
    }

    @Override
    public int compareTo(SCostNode o) {
        return Double.compare(cost,o.cost);
    }

    public byte[][] getBytesRecursive() {
        byte[][] result = new byte[position+1][];
        STrieNode toAdd = node;
        int index = position;
        while (index >= 0 && toAdd != null) {
            result[index] = toAdd.value();
            index--;
            toAdd = toAdd.getParent();
        }
        return result;
    }

    public STrieNode getNode() {
        return node;
    }

    public int getPosition() {
        return position;
    }

    public double getCost() {
        return cost;
    }
}
