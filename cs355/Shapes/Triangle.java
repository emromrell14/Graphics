package cs355.Shapes;

import cs355.Helper;
import cs355.Vector;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eric on 4/29/15.
 */
public class Triangle extends Shape {
    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    public Triangle(Color color, Point2D point1, Point2D point2, Point2D point3) {
        super.setColor(color);
        super.setCenter(new Point2D.Double((point1.getX() + point2.getX() + point3.getX()) / 3, (point1.getY() + point2.getY() + point3.getY()) / 3));
        this.point1 = new Point2D.Double(point1.getX() - this.getCenter().getX(), point1.getY() - this.getCenter().getY());
        this.point2 = new Point2D.Double(point2.getX() - this.getCenter().getX(), point2.getY() - this.getCenter().getY());
        this.point3 = new Point2D.Double(point3.getX() - this.getCenter().getX(), point3.getY() - this.getCenter().getY());
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

    public Point2D getPoint3() {
        return point3;
    }

    public void setPoint3(Point2D point3) {
        this.point3 = point3;
    }

    public double getHalfHeight() {
        return Math.max(point1.getY(),
                Math.max(Math.abs(point2.getY()),
                        Math.abs(point3.getY())
                )
        );
    }

    @Override
    public Point2D calculateTopLeftCorner() {
        return null;
    }

    @Override
    public Vector getHandleDisplacement() {
        return new Vector(- HANDLE_WIDTH() * Helper.screenScale / 2, this.getHalfHeight() + HANDLE_DISPLACEMENT() + HANDLE_WIDTH() * Helper.screenScale / 2);
    }

    @Override
    public List<Square> getHandles() {
        final Vector displacement = this.getHandleDisplacement();
        final Point2D origin = new Point2D.Double(this.getCenter().getX() + displacement.get(0), this.getCenter().getY() + displacement.get(1));
        final Square handle = new Square(null, origin, HANDLE_WIDTH() * Helper.screenScale);
        handle.setRotationAngle(this.getRotationAngle());
        return Arrays.asList(handle);
    }

    @Override
    public boolean isClickInShape(Point2D click) {
        AffineTransform transform = Helper.viewToObject(this);
        Point2D newClick = new Point2D.Double();
        transform.transform(click, newClick);

        double a = new Vector(newClick, point1).dot(new Vector(point2, point1).getPerpendicular());
        double b = new Vector(newClick, point2).dot(new Vector(point3, point2).getPerpendicular());
        double c = new Vector(newClick, point3).dot(new Vector(point1, point3).getPerpendicular());

        if((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
            return true;
        }
        return false;
    }
}
