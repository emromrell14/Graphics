package cs355.Tests;

import cs355.Helper;
import cs355.Shapes.Square;
import cs355.Vector;
import junit.framework.TestCase;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.*;

/**
 * Created by eric on 5/6/15.
 */
public class ObjectToWorldTester {
    @Test
    public void testViewToWorld() {
        Helper.screenScale = .5;
        Helper.topLeftOffset = new Vector(5, 5);
        Point2D point = new Point(6, 12);
        AffineTransform transform = Helper.viewToWorld();
        transform.transform(point, point);
        assertTrue(point.getX() == 8);
        assertTrue(point.getY() == 11);

        Helper.screenScale = 1;
        Helper.topLeftOffset = new Vector(10, 2);
        point = new Point(0, 3);
        transform = Helper.viewToWorld();
        transform.transform(point, point);
        assertTrue(point.getX() == 10);
        assertTrue(point.getY() == 5);
    }

    @Test
    public void testWorldToObject() {
        Point2D point = new Point(8, 11);
        Square square = new Square(null, new Point(5, 5), 6);
        AffineTransform transform = Helper.worldToObject(square);
        transform.transform(point, point);
        assertTrue(point.getX() == 0);
        assertTrue(point.getY() == 3);

        point = new Point(10, 5);
        square = new Square(null, new Point(5, 5), 6);
        transform = Helper.worldToObject(square);
    }

    @Test
    public void testViewToObject() {
        Helper.screenScale = .5;
        Helper.topLeftOffset = new Vector(5, 5);
        Point2D point = new Point(6, 12);
        Square square = new Square(null, new Point(5, 5), 6);
        AffineTransform transform = Helper.viewToObject(square);
        transform.transform(point, point);
        assertTrue(point.getX() == 0);
        assertTrue(point.getY() == 3);
    }

    @Test
    public void testWorldToView() {
        Helper.screenScale = .5;
        Helper.topLeftOffset = new Vector(5, 5);
        Point2D point = new Point(8, 11);
        AffineTransform transform = Helper.worldToView();
        transform.transform(point, point);
        assertTrue(point.getX() == 6);
        assertTrue(point.getY() == 12);

        Helper.screenScale = 1;
        Helper.topLeftOffset = new Vector(10, 2);
        point = new Point(10, 5);
        transform = Helper.worldToView();
        transform.transform(point, point);
        assertTrue(point.getX() == 0);
        assertTrue(point.getY() == 3);
    }
}
