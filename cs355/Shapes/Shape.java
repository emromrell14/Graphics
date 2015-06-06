package cs355.Shapes;

import cs355.Helper;
import cs355.Vector2D;

import java.awt.*;
import java.util.List;
import java.awt.geom.Point2D;

/**
 * Created by eric on 4/29/15.
 */
public abstract class Shape {
    public static int HANDLE_DISPLACEMENT() {
        return (int) (4 * Helper.screenScale);
    }
    public static int HANDLE_WIDTH() {
        return (int) (8 * Helper.screenScale);
    }

    private Color color;
    private Point2D center;
    private double rotationAngle = 0;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public void moveCenter(double deltaX, double deltaY) {
        if(this instanceof Line) {
            Line line = (Line) this;
            line.setPoint1(new Point2D.Double(line.getPoint1().getX() + deltaX, line.getPoint1().getY() + deltaY));
            line.setPoint2(new Point2D.Double(line.getPoint2().getX() + deltaX, line.getPoint2().getY() + deltaY));
        } else {
            center.setLocation(center.getX() + deltaX, center.getY() + deltaY);
        }
    }

    @Override
    public String toString() {
        return "Center: " + this.center + ", Rotation Angle: " + this.rotationAngle;
    }

    public abstract Point2D calculateTopLeftCorner();

    public abstract Vector2D getHandleDisplacement();

    public abstract List<Square> getHandles();

    public abstract boolean isClickInShape(Point2D click);
}
