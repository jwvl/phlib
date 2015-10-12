package eu.jwvl.phonlib.feature.hierarchical.visualize;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import eu.jwvl.phonlib.feature.hierarchical.FeatureNode;
import eu.jwvl.phonlib.feature.hierarchical.FeatureTree;

/**
 * Created by janwillem on 27/09/15.
 */
public class FeatureTreeGraph {
    private DirectedSparseGraph<FeatureNode,FeatureEdge> graph;

    private FeatureTreeGraph(DirectedSparseGraph<FeatureNode, FeatureEdge> graph) {
        this.graph = graph;
    }

    public static FeatureTreeGraph createFromFeatureTree(FeatureTree input) {
        DirectedSparseGraph<FeatureNode,FeatureEdge> graph = new DirectedSparseGraph<>();
        FeatureTreeGraph result = new FeatureTreeGraph(graph);
        result.addRecursively(input.getRoot());
        return result;
    }

    private void addRecursively(FeatureNode node) {
        graph.addVertex(node);
        for (FeatureNode childNode: node.getChildren()) {
            addRecursively(childNode);
            FeatureEdge edge = new FeatureEdge(node.getId(), childNode.getId(), childNode.getDistinguishingFeature());
            graph.addEdge(edge,node,childNode);
        }
    }

    public DirectedSparseGraph<FeatureNode, FeatureEdge> getGraph() {
        return graph;
    }
}
