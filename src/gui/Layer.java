package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:34
 */

import java.awt.*;
import java.util.List;

public class Layer  extends Rectangle {
    private List<Node> nodes;
    private GraphPanel graph;
    private int layerID;
    private Dimension dimension;
    private Point position;

    public Layer(GraphPanel graph, int layerID){
        super(new Point(layerID * 150, (int)((graph.getWidth() - 30) / 2) + 15), new Dimension(graph.getWidth()-30, 100));
        this.graph = graph;
        this.layerID = layerID;
        this.dimension = super.getSize();
        this.position = super.getLocation();
    }

    public GraphPanel getGraph() {
        return graph;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
