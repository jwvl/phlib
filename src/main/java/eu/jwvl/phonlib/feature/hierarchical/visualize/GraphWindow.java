package eu.jwvl.phonlib.feature.hierarchical.visualize;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import eu.jwvl.phonlib.feature.hierarchical.FeatureNode;

import javax.swing.*;

/**
 * Created by janwillem on 27/09/15.
 */
public class GraphWindow extends JFrame {

    public GraphWindow(FeatureTreeGraph featureTreeGraph) {
        DirectedSparseGraph<FeatureNode, FeatureEdge> graph = featureTreeGraph.getGraph();
        VisualizationViewer vv = new VisualizationViewer(new FRLayout(graph));
        this.setSize(800,600);
        this.getContentPane().add(vv);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
}
