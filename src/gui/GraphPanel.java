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
    private List<Layer> layers;
    private Graph graph;

    @Override
    public boolean equals(Object o){
        return true;
    }

    public GraphPanel(){
        layers = new ArrayList<Layer>();
    }

    public List<Node> getNodes(){
        List<Node> retVal = new ArrayList<Node>();
        for (Layer l : layers)
            for (Node n : l.getNodes())
                retVal.add(n);

        return retVal;
    }

    public void setGraph(Graph graph){
        this.graph = graph;
        for (int i = 1; i <= graph.getLayers() + 2; i++)
            this.getLayers().add(new Layer(this, i));

        Graph.Node vx;
        Layer cl;

        //выдергивание из теминого графа сначала
        for (int i = 0; i < graph.getVertexes().length; i++) {
            vx = graph.getVertexes()[i];
            this.getLayers().get(vx.getLayer()).getNodes().add(new Node(this.getLayers().get(vx.getLayer()), i));
        }
        for( Layer l : this.getLayers())
            for (Node n : l.getNodes()){
                n.setNodeInLayerID();
                n.setPosition();
            }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for( Layer l : this.getLayers()){
            g.setColor(Color.darkGray);
            g.drawRect((int)l.getX(), (int)l.getY(), (int)l.getWidth(), (int)l.getHeight());
            for (Node n : l.getNodes()){
                n.setIncomingArcsByIndexes(graph.getVertexes()[n.getNodeID()].getUsedBy());
                n.setOutgoingArcsByIndexes(graph.getVertexes()[n.getNodeID()].getUsing());
                g.setColor(Color.black);
                g.fillOval(
                    (int) n.getPosition().getX(),
                    (int) n.getPosition().getY(),
                    (int) n.getFigure().getWidth(),
                    (int) n.getFigure().getHeight()
                );
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
