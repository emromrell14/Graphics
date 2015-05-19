package cs355;

import cs355.Shapes.*;
import cs355.Shapes.Shape;
import javafx.scene.transform.Affine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by eric on 5/1/15.
 */
public class Helper {
    public static Vector topLeftOffset = new Vector(0, 0);
    public static double screenScale = 1;
    public static int DEFAULT_SCREEN_SIZE = 512;

    public static double getDistance(Point2D a, Point2D b) {
        return Math.sqrt(Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getX() - b.getX(), 2));
    }

    public static double getDistance(Vector vector) {
        return Math.sqrt(Math.pow(vector.get(0), 2) + Math.pow(vector.get(1), 2));
    }

    public static double lawOfCosines(double a, double b, double c) {
        double numerator = Math.pow(a, 2) - Math.pow(b, 2) - Math.pow(c, 2);
        double denominator = - 2 * b * c;
        return Math.acos(numerator / denominator);
    }

    public static AffineTransform viewToWorld() {
        AffineTransform transform = new AffineTransform();
        //Scale
        final AffineTransform scale = new AffineTransform(screenScale, 0, 0, screenScale, 0, 0);
        //Translate
        final AffineTransform translate = new AffineTransform(1, 0, 0, 1, topLeftOffset.get(0), topLeftOffset.get(1));

        //Concatenate in reverse...
        transform.concatenate(translate);
        transform.concatenate(scale);
        return transform;
    }

    public static Point2D viewToWorld(Point2D point) {
        AffineTransform transform = viewToWorld();
        return transform.transform(point, null);
    }

    public static AffineTransform worldToObject(Shape shape) {
        final double rotation = shape.getRotationAngle();
        final AffineTransform transform = new AffineTransform();
        //Translate
        final AffineTransform translate = new AffineTransform(1, 0, 0, 1, -shape.getCenter().getX(), -shape.getCenter().getY());
        //Rotate
        final AffineTransform rotate = new AffineTransform(Math.cos(rotation), -Math.sin(rotation), Math.sin(rotation), Math.cos(rotation), 0, 0);

        //Concatenate in reverse...
        transform.concatenate(rotate);
        transform.concatenate(translate);
        return transform;
    }

    public static AffineTransform viewToObject(Shape shape) {
        final AffineTransform viewToObject = new AffineTransform();
        //View to World
        final AffineTransform viewToWorld = viewToWorld();
        //World to Object
        final AffineTransform worldToObject = worldToObject(shape);

        //Concatenate in reverse...
        viewToObject.concatenate(worldToObject);
        viewToObject.concatenate(viewToWorld);
        return viewToObject;
    }

    public static AffineTransform objectToWorld(Shape shape) {
        final double rotation = shape.getRotationAngle();
        final AffineTransform transform = new AffineTransform();
        //Rotate
        final AffineTransform rotate = new AffineTransform(Math.cos(rotation), Math.sin(rotation), -Math.sin(rotation), Math.cos(rotation), 0, 0);
        //Translate
        final AffineTransform translate = new AffineTransform(1, 0, 0, 1, shape.getCenter().getX(), shape.getCenter().getY());

        //Concatenate in reverse...
        transform.concatenate(translate);
        transform.concatenate(rotate);
        return transform;
    }

    public static AffineTransform worldToView() {
        AffineTransform transform = new AffineTransform();
        //Translate
        final AffineTransform translate = new AffineTransform(1, 0, 0, 1, -topLeftOffset.get(0), -topLeftOffset.get(1));
        //Scale
        final AffineTransform scale = new AffineTransform(1/screenScale, 0, 0, 1/screenScale, 0, 0);

        //Concatenate in reverse...
        transform.concatenate(scale);
        transform.concatenate(translate);
        return transform;
    }

    public static AffineTransform objectToView(Shape shape) {
        final AffineTransform objectToView = new AffineTransform();
        //Object to World
        final AffineTransform objectToWorld = objectToWorld(shape);
        //World to View
        final AffineTransform worldToView = worldToView();

        //Concatenate in reverse...
        objectToView.concatenate(worldToView);
        objectToView.concatenate(objectToWorld);
        return objectToView;
    }
}
