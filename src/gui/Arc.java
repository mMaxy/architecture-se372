package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:34
 */
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

public class Arc extends /*Line2D.Double {*/ QuadCurve2D.Double {

    private Node origin;
    private Node target;
    private Point control;
    private Polygon end;

    public Polygon getEnd() {
        return end;
    }

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
            origin.getPosition().getX() + origin.getFigure().getWidth() / 2,
            origin.getPosition().getY() + origin.getFigure().getHeight() / 2,
            Math.abs(origin.getPosition().getX() + target.getPosition().getX()) / 2,
            Math.abs(origin.getPosition().getY() + target.getPosition().getY()) / 2,
            target.getPosition().getX() + target.getFigure().getWidth() / 2,
            target.getPosition().getY() + target.getFigure().getHeight() / 2
            );
        this.control = new Point((int)this.getCtrlPt().getX(), (int)this.getCtrlPt().getY());
        this.origin = origin;
        this.target = target;
        double angle = angleBetween(target.getPosition(), control, new Point(0, (int)target.getPosition().getY()));
        this.end = new Polygon(
                new int[]{
                        (int) (this.getX2() - 10 * Math.abs(Math.cos(angle))),//target.getPosition().getX(),
                        //(int)target.getPosition().getX()
                        (int) (this.getX2() - 30 * Math.abs(Math.cos(angle)) + 3 * Math.abs(Math.sin(angle))),
                        (int) (this.getX2() - 30 * Math.abs(Math.cos(angle)) - 3 * Math.abs(Math.sin(angle)))
                },
                new int[]{
                        (int) (this.getY2() - 10 * Math.abs(Math.sin(angle))),
                        (int) (this.getY2() - 30 * Math.abs(Math.sin(angle)) + 3 * Math.abs(Math.cos(angle))),
                        (int) (this.getY2() - 30 * Math.abs(Math.sin(angle)) - 3 * Math.abs(Math.cos(angle)))
                },
                3);
    }

    private double angleBetween(Point center, Point current, Point previous) {

      return Math.toDegrees(Math.atan2(current.x - center.x,current.y - center.y) - Math.atan2(previous.x - center.x, previous.y - center.y));
    }
}
