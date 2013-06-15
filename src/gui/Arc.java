package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:34
 */
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

public class Arc extends Line2D.Double { //QuadCurve2D.Double {

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
            target.getPosition().getX(),
            target.getPosition().getY()/*,
            Math.abs(origin.getPosition().getX() + target.getPosition().getX()) / 2,
            Math.abs(origin.getPosition().getY() + target.getPosition().getY()) / 2*/
            );
        this.origin = origin;
        this.target = target;
    }

}
