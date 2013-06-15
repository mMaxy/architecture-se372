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

    public Ellipse2D.Double getView() {
        return view;
    }

    public Node(Layer layer, int nodeID) {
        this();
        this.nodeID = nodeID;
        this.layer = layer;
        state = State.NORMAL;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    private void loadAnchors() {
        int x = (int)view.x;

        int y = (int)view.y;
        this.anchors = new Point[]{
                new Point(x + 10, y),
                new Point(x + 17, y + 3),
                new Point(x + 20, y + 10),
                new Point(x + 17, y + 17),
                new Point(x + 10, y + 20),
                new Point(x + 3, y + 17),
                new Point(x, y + 10),
                new Point(x + 3, y + 3)
        };
    }

    public Point[] getAnchors() {
        return anchors;
    }

    public Point getClosestAnchor(Point p) {
        double d = anchors[0].distance(p.x, p.y);
        int result = 0;
        for (int i = 1; i < anchors.length; i++)
            if (anchors[i].distance(p.x, p.y) <= d) {
                d = anchors[i].distance(p.x, p.y);
                result = i;
            }
        return anchors[result];
    }

    public Point getCenterOf(){
      return new Point(
              (int)(this.view.x + this.view.width / 2),
              (int)(this.view.y + this.view.height / 2)
      );
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

    public void setIncomingArcs(List<Arc> incomingArcs) {
        this.incomingArcs = incomingArcs;
    }

    public List<Arc> getIncomingArcs() {
        return incomingArcs;
    }

    public void setIncomingArcsByIndexes(List<Integer> incomingArcsNodeIndexes) {
        for (int i : incomingArcsNodeIndexes)
            this.incomingArcs.add(new Arc(this.getLayer().getGraph().getNodes().get(i), this));
    }

    public List<Arc> getOutgoingArcs() {
        return outgoingArcs;
    }

    public void setOutgoingArcsByIndexes(List<Integer> outgoingArcsNodeIndexes) {
        for (int i : outgoingArcsNodeIndexes)
            this.outgoingArcs.add(new Arc(this, this.getLayer().getGraph().getNodes().get(i)));
    }

    public Layer getLayer() {
        return this.layer;
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

    public void setPosition(){
        this.position = new Point(
            (int)this.layer.getX() + (int)this.layer.getWidth() / (this.layer.getNodes().size() + 1) * (this.nodeInLayerID + 1),
            (int)this.layer.getY() + 50);
        this.view = new Ellipse2D.Double(position.x, position.y, figure.width, figure.height);
        this.loadAnchors();
    }
}
