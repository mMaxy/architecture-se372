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
        this.origin = new Node(origin, new Point(
                (int)(origin.getPosition().getX() + origin.getFigure().getWidth() / 2),
                (int)(origin.getPosition().getY() + origin.getFigure().getHeight() / 2)
        ));
        this.target = new Node(target, new Point(
                (int) (target.getPosition().getX() + target.getFigure().getWidth() / 2),
                (int) (target.getPosition().getY() + target.getFigure().getHeight() / 2)
        ));
        this.control = new Point(
                (int)(origin.getPosition().getX() + target.getPosition().getX()) / 2,
                (int)(origin.getPosition().getY() + target.getPosition().getY()) / 2
        );

        for (Node n : this.getOrigin().getLayer().getGraph().getNodes())
                if (new Rectangle(n.getPosition(), n.getFigure()).contains(this.control)) {
                    if (this.origin.getLayer() == n.getLayer())
                        this.control.setLocation(this.control.x , this.control.y - 40);
                    else
                        this.control.setLocation(this.control.x - 40 , this.control.y);
                }

        this.setCurve(
        //this.setLine(
                this.origin.getPosition(),
                this.control,
                this.target.getPosition()
            );
        double angle = angleBetween(this.target.getPosition(), this.control, new Point(0, (int)this.target.getPosition().getY()));
        this.end = new Polygon(
                new int[]{
                        (int) (this.getX2() - 10 * Math.cos(angle)),
                        (int) (this.getX2() - 35 * Math.cos(angle) + 5 * Math.sin(angle)),
                        (int) (this.getX2() - 35 * Math.cos(angle) - 5 * Math.sin(angle))
                },
                new int[]{
                        (int) (this.getY2() + 10 * Math.sin(angle)),
                        (int) (this.getY2() + 35 * Math.sin(angle) + 5 * Math.cos(angle)),
                        (int) (this.getY2() + 35 * Math.sin(angle) - 5 * Math.cos(angle))
                },
                3);
        state = State.NORMAL;
    }

    private double angleBetween(Point center, Point current, Point previous) {
      return Math.atan2(current.x - center.x, current.y - center.y) -
              Math.atan2(previous.x - center.x, previous.y - center.y);
    }
}
