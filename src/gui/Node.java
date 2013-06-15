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
    private Point[] anchors;
    private Point position;
    private State state;

    public Node(){
        this.incomingArcs = new ArrayList<Arc>();
        this.outgoingArcs = new ArrayList<Arc>();
        state = State.NORMAL;
    }

    public Node(Node n, Point p){
        this.incomingArcs = n.getIncomingArcs();
        this.outgoingArcs = n.getOutgoingArcs();
        this.layer = n.getLayer();
        this.nodeID = n.getNodeID();
        this.nodeInLayerID = n.getNodeInLayerID();
        this.position = p;
        this.state = State.NORMAL;
    }

    public Node(Layer layer, int nodeID) {
        this();
        this.nodeID = nodeID;
        this.layer = layer;
        state = State.NORMAL;
    }

    private void loadAnchors() {
        int x = (int)this.position.getX();
        int y = (int)this.position.getY();
        int qr = (int)Math.sqrt(((int) this.getFigure().getWidth() / 2) ^ 2 + ((int) this.getFigure().getHeight() / 2) ^ 2);
        this.anchors = new Point[]{
                new Point(x + 10, y),
                new Point(x + 10 + qr, y + qr),
                new Point(x + 20, y + 10),
                new Point(x + 10 + qr, y + 10 + qr),
                new Point(x + 10, y + 20),
                new Point(x + qr, y + 10 + qr),
                new Point(x, y + 10),
                new Point(x + qr, y + qr)
        };
    }

    public Point[] getAnchors() {
        return this.anchors;
    }

    public Point getClosestAnchor(Point p) {

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public void setPosition(Point position){
        this.position = position;
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
