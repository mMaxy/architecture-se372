package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 23:34
 */

import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class Arc extends /*Line2D.Double {*/ QuadCurve2D.Double {

    private Node origin;
    private Node target;

    private Point originAnchor;
    private Point targetAnchor;
    private Point control;
    private Polygon end;
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State status) {
        this.state = status;
    }

    public Arc(){
        super();
        state = State.NORMAL;
    }

    public Arc(Node origin, Node target) {
        this();
        this.origin = origin;
        this.target = target;

        if (this.origin != this.target){
            this.originAnchor = origin.getClosestAnchor(target.getCenterOf() );
            this.targetAnchor = target.getClosestAnchor(origin.getCenterOf());

            this.control = new Point(
                (int)(originAnchor.getX() + targetAnchor.getX()) / 2,
                (int)(originAnchor.getY() + targetAnchor.getY()) / 2
            );
        } else {
            this.originAnchor = origin.getAnchors()[2];
            this.targetAnchor = target.getAnchors()[3];
            this.control = new Point((int)(originAnchor.getX()) + 40, (int)(originAnchor.getY()) + 40);
        }

        for (Node n : this.getOrigin().getLayer().getGraph().getNodes())
            //if (this.intersects(n.getView().getBounds())) {
            if ((n.getView().getBounds().contains(this.control))) {
                if (this.origin.getLayer() == n.getLayer())
                    this.control.setLocation(this.control.x , this.control.y - 40);
                else
                    this.control.setLocation(this.control.x - 40 , this.control.y);
            }

        this.setCurve(this.originAnchor, this.control, this.targetAnchor);
        double angle = angleBetween(this.targetAnchor, this.control, new Point(0, (int)this.targetAnchor.getY()));
        this.end = new Polygon(
                new int[]{
                        (int) this.getX2(),
                        (int) (this.getX2() - 20 * Math.cos(angle) + 3 * Math.sin(angle)),
                        (int) (this.getX2() - 20 * Math.cos(angle) - 3 * Math.sin(angle))
                },
                new int[]{
                        (int) this.getY2(),
                        (int) (this.getY2() + 20 * Math.sin(angle) + 3 * Math.cos(angle)),
                        (int) (this.getY2() + 20 * Math.sin(angle) - 3 * Math.cos(angle))
                },
                3);
        state = State.NORMAL;
    }

    private double angleBetween(Point center, Point current, Point previous) {
      return Math.atan2(current.x - center.x, current.y - center.y) -
              Math.atan2(previous.x - center.x, previous.y - center.y);
    }
}
