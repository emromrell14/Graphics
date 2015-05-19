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
public class Rectangle extends Shape {
    private double height;
    private double width;

    public Rectangle(Color color, Point2D origin, double height, double width) {
        super.setColor(color);
        super.setCenter(new Point2D.Double(origin.getX() + width / 2, origin.getY() + height / 2));
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public Point2D calculateTopLeftCorner() {
        return new Point2D.Double(super.getCenter().getX() - this.width/2, super.getCenter().getY() - this.height/2);
    }

    @Override
    public Vector getHandleDisplacement() {
        return new Vector(- HANDLE_WIDTH / 2, this.height / 2 + HANDLE_DISPLACEMENT + HANDLE_WIDTH / 2);
    }

    @Override
    public List<Square> getHandles() {
        final Vector displacement = this.getHandleDisplacement();
        final Point2D origin = new Point2D.Double(this.getCenter().getX() + displacement.get(0), this.getCenter().getY() + displacement.get(1));
        final Square handle = new Square(null, origin, HANDLE_WIDTH);
        handle.setRotationAngle(this.getRotationAngle());
        return Arrays.asList(handle);
    }

    @Override
    public boolean isClickInShape(Point2D click) {
        AffineTransform transform = Helper.viewToObject(this);
        Point2D newClick = new Point2D.Double();
        transform.transform(click, newClick);

        if(Math.abs(newClick.getX()) <= width / 2 && Math.abs(newClick.getY()) <= height / 2) {
            return true;
        }
        return false;
    }
}
