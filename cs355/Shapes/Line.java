package cs355.Shapes;

import cs355.Helper;
import cs355.Vector2D;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Created by eric on 4/29/15.
 */
public class Line extends Shape {
    private Point2D point1;
    private Point2D point2;

    public Line(Color color, Point2D point1, Point2D point2) {
        super.setColor(color);
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point2D getPoint1() {
        return point1;
    }

    public void setPoint1(Point2D point1) {
        this.point1 = point1;
    }

    public Point2D getPoint2() {
        return point2;
    }

    public void setPoint2(Point2D point2) {
        this.point2 = point2;
    }

    @Override
    public Point2D calculateTopLeftCorner() {
        return null;
    }

    @Override
    public Vector2D getHandleDisplacement() {
        return null;
    }

    @Override
    public List<Square> getHandles() {
        final Square handle1 = new Square(null, point1, HANDLE_WIDTH());
        final Square handle2 = new Square(null, point2, HANDLE_WIDTH());
        return Arrays.asList(handle1, handle2);
    }

    @Override
    public boolean isClickInShape(Point2D click) {
        Point2D newClick = Helper.viewToWorld(click);

        double distance = getDistanceFromLine(newClick);
        return distance <= 4 * Helper.screenScale;
    }

    public Vector2D getNormal() {
        Vector2D vector = new Vector2D(point1, point2);
        double distance = Helper.getDistance(vector);
        return getPerpendicular(new Vector2D(vector.get(0) / distance, vector.get(1) / distance));
    }

    public Vector2D getPerpendicular(Vector2D vector) {
        return new Vector2D(-vector.get(1), vector.get(0));
    }

    private double getDistanceFromLine(Point2D point) {
        final Vector2D p = new Vector2D(point);
        final Vector2D normal = this.getNormal();
        return Math.abs(p.dot(normal) - new Vector2D(point1).dot(normal));
    }
}
