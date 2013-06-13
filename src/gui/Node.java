package gui;

/**
 * Created with IntelliJ IDEA.
 * User: prohodil_mimo
 * Date: 13.06.13
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.List;

public class Node extends Point2D {
    private double x, y;
    private List<Arc> incomingArcs;
    private List<Arc> outgoingArcs;

    public Node(double x, double y, List<Arc> incomingArcs, List<Arc> outgoingArcs) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getY() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setLocation(double x, double y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }



}
