package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k
 * Date: 13.06.13
 * Time: 22:48
 */

import graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphPanel extends JPanel{
    //private List<Node> nodes;
    //private Arc[] arcs;
    private List<Layer> layers;

    public GraphPanel(){
        layers = new ArrayList<Layer>();
    }

    public void setGraph(Graph graph){
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);

        //g.fillRect(0, 0, 50, 50);

        for( Layer l : this.getLayers()){
            g.drawRect((int)l.getX(), (int)l.getY(), (int)l.getWidth(), (int)l.getHeight());
            for (Node n : l.getNodes()){
                n.setPosition();
                g.drawOval(
                        (int)n.getPosition().getX(),
                        (int)n.getPosition().getY(),
                        (int)n.getFigure().getWidth(),
                        (int)n.getFigure().getHeight());
            }
        }
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

}
