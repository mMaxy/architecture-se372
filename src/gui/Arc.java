package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:34
 */
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Arc extends Arc2D {

    private Node origin, target;

    public Arc(Node origin, Node target) {
        this.origin = origin;
        this.target = target;
    }

    @Override
    public double getAngleStart() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getAngleExtent() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setArc(double x, double y, double w, double h, double angSt, double angExt, int closure) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAngleStart(double angSt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAngleExtent(double angExt) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected Rectangle2D makeBounds(double x, double y, double w, double h) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
    public double getWidth() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getHeight() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEmpty() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
