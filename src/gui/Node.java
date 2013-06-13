package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:33
 */
import javax.swing.*;
import java.awt.Point;
import java.util.List;

public class Node {
    private int nodeID;
    private int nodeInLayerID;
    private Layer layer;
    private List<Arc> incomingArcs;
    private List<Arc> outgoingArcs;
    private Point position;

    public List<Arc> getIncomingArcs() {
        return incomingArcs;
    }

    public void setIncomingArcs(List<Arc> incomingArcs) {
        this.incomingArcs = incomingArcs;
    }

    public void setIncomingArcsByIndexes(List<Integer> incomingArcsNodeIndexes) {
        for (int i : incomingArcsNodeIndexes)
            this.incomingArcs.add(new Arc(this.getLayer().getNodes().get(i), this));
    }

    public List<Arc> getOutgoingArcs() {
        return outgoingArcs;
    }

    public void setOutgoingArcs(List<Arc> outgoingArcs) {
        this.outgoingArcs = outgoingArcs;
    }

    public void setOutgoingArcsByIndexes(List<Integer> outgoingArcsNodeIndexes) {
            for (int i : outgoingArcsNodeIndexes)
                this.outgoingArcs.add(new Arc(this, this.getLayer().getNodes().get(i)));
        }

    public Layer getLayer() {
        return this.layer;
    }

    public void setLayer(Layer layer){
        this.layer = layer;
    }

    public Node(Layer layer, int nodeID) {
        this.nodeInLayerID = layer.getNodes().indexOf(this);
        this.nodeID = nodeID;
        this.layer = layer;
    }

    public void setPosition(){
        int buf = this.layer.getNodes().size()/this.nodeInLayerID;
        this.position = new Point(buf % 30, this.layer.y + buf / 30 );
    }

}
