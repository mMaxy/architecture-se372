package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k
 * Date: 13.06.13
 * Time: 22:48
 */

import javax.swing.*;
import java.awt.*;
import graph.Graph;
import java.util.List;

public class GraphPanel extends JPanel{
    //private List<Node> nodes;
    //private Arc[] arcs;
    private List<Layer> layers;

    public GraphPanel(){
        super();
        this.setBackground(Color.white);
    }

    public GraphPanel(Graph graph){
        this();
        for (int i = 0; i < graph.getLayers(); i++)
            this.getLayers().add(new Layer(this, i));

        Graph.Node vx;
        Layer cl;
        for (int i = 0; i < graph.getVertexes().length; i++) {
            vx = graph.getVertexes()[i];
            cl = this.getLayers().get(vx.getLayer());
            cl.getNodes().add(new Node(cl, i));
        }

        for (Layer l : this.getLayers())
            for (Node n : l.getNodes()) {
                n.setIncomingArcsByIndexes(graph.getVertexes()[n.getNodeID()].getUsedBy());
                n.setOutgoingArcsByIndexes(graph.getVertexes()[n.getNodeID()].getUsing());
            }
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

}
