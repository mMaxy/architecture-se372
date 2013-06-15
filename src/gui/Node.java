package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:33
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private int nodeID;
    private int nodeInLayerID;
    private Layer layer;
    private List<Arc> incomingArcs;
    private List<Arc> outgoingArcs;
    private Ellipse2D.Double view;
    private Dimension figure = new Dimension(20, 20);
    private Point position;

    public Node(){
        this.incomingArcs = new ArrayList<Arc>();
        this.outgoingArcs = new ArrayList<Arc>();
    }

    public Node(Layer layer, int nodeID) {
        this();
        this.nodeID = nodeID;
        this.layer = layer;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getNodeInLayerID() {
        return nodeInLayerID;
    }

    public void setNodeInLayerID(int nodeInLayerID) {
        this.nodeInLayerID = nodeInLayerID;
    }

    public void setNodeInLayerID() {
        this.nodeInLayerID = layer.getNodes().indexOf(this);
    }

    public void setPosition(Ellipse2D.Double view) {
        this.view = view;
    }

    public List<Arc> getIncomingArcs() {
        return incomingArcs;
    }

    public void setIncomingArcs(List<Arc> incomingArcs) {
        this.incomingArcs = incomingArcs;
    }

    public void setIncomingArcsByIndexes(List<Integer> incomingArcsNodeIndexes) {
        for (int i : incomingArcsNodeIndexes)
            this.incomingArcs.add(new Arc(this.getLayer().getGraph().getNodes().get(i), this));
    }

    public List<Arc> getOutgoingArcs() {
        return outgoingArcs;
    }

    public void setOutgoingArcs(List<Arc> outgoingArcs) {
        this.outgoingArcs = outgoingArcs;
    }

    public void setOutgoingArcsByIndexes(List<Integer> outgoingArcsNodeIndexes) {
            for (int i : outgoingArcsNodeIndexes)
                this.outgoingArcs.add(new Arc(this, this.getLayer().getGraph().getNodes().get(i)));
        }

    public Layer getLayer() {
        return this.layer;
    }

    public void setLayer(Layer layer){
        this.layer = layer;
    }

    public Point getPosition(){
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (nodeID != node.nodeID) return false;
        if (!layer.equals(node.layer)) return false;

        return true;
    }

    public void setPosition(){
        this.position = new Point(
            (int)this.layer.getX() + (int)this.layer.getWidth() / (this.layer.getNodes().size() + 1) * (this.nodeInLayerID + 1),
            (int)this.layer.getY() + 50);
    }

    public void setFigure(Dimension figure){
        this.figure = figure;
    }

    public Dimension getFigure(){
        return this.figure;
    }
}
