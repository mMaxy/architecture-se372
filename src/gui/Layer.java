package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:34
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Layer  extends Rectangle {
    private List<Node> nodes;
    private GraphPanel graph;

    public int getLayerID() {
        return layerID;
    }

    private int layerID;
    private Dimension dimension;
    private Point position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Layer)) return false;
        if (!super.equals(o)) return false;

        Layer layer = (Layer) o;

        if (layerID != layer.layerID) return false;
        if (!graph.equals(layer.graph)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + graph.hashCode();
        result = 31 * result + layerID;
        return result;
    }

    public Layer(GraphPanel graph, int layerID){
        super(new Point(15, (layerID - 1) * 150), new Dimension(graph.getWidth()-30, 100));
        this.graph = graph;
        this.layerID = layerID;
        this.dimension = super.getSize();
        this.position = super.getLocation();
        this.nodes = new ArrayList<Node>();
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
