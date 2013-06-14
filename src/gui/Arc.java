package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:34
 */
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class Arc extends Arc2D.Double {

    private Node origin;
    private Node target;

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public Node getOrigin() {
        return origin;
    }

    public void setOrigin(Node origin) {
        this.origin = origin;
    }

    public Arc(){
        super();
    }

    public Arc(Node origin, Node target) {
        super(
            origin.getPosition().getX(),
            origin.getPosition().getY(),
            (int) StrictMath.abs(origin.getPosition().getX() - target.getPosition().getX()),
            (int) StrictMath.abs(origin.getPosition().getY() - target.getPosition().getY()),
            0,
            0,
            PIE);
        this.origin = origin;
        this.target = target;
    }

}
