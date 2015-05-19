package cs355.Tests;

import cs355.Helper;
import cs355.Shapes.*;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by eric on 5/1/15.
 */
public class MathTester {

    @Test
    public void testDistance() {
        double distance = Helper.getDistance(new Point2D.Double(0, 0), new Point2D.Double(2, 2));
        assertTrue(distance == Math.sqrt(8));
    }
//
//    @Test
//    public void testLine() {
//        Line line = new Line(null, new Point2D.Double(0, 0), new Point2D.Double(10, 0));
//        assertTrue(line.isClickInShape(new Point2D.Double(5, 0)));
//        assertFalse(line.isClickInShape(new Point2D.Double(-4, -1)));
//        assertTrue(line.isClickInShape(new Point2D.Double(-4, 0)));
//    }
//
//    @Test
//    public void testRectangle() {
//        Rectangle rectangle = new Rectangle(null, new Point2D.Double(0, 0), 10, 10);
//        assertTrue(rectangle.isClickInShape(new Point2D.Double(5, 5)));
//        assertFalse(rectangle.isClickInShape(new Point2D.Double(0, -1)));
//        assertTrue(rectangle.isClickInShape(new Point2D.Double(0, 10)));
//    }
//
//    @Test
//    public void testCircle() {
//        Circle circle = new Circle(null, new Point(5, 5), 3);
//        assertTrue(circle.isClickInShape(new Point(5, 5)));
//        assertFalse(circle.isClickInShape(new Point(8, 6)));
//        assertTrue(circle.isClickInShape(new Point(8, 5)));
//    }
//
//    @Test
//    public void testEllipse() {
//        Ellipse ellipse = new Ellipse(null, new Point(5, 5), 3, 4);
//        assertTrue(ellipse.isClickInShape(new Point(5, 5)));
//        assertFalse(ellipse.isClickInShape(new Point(6, 1)));
//        assertTrue(ellipse.isClickInShape(new Point(5, 1)));
//    }

    @Test
    public void testRotationAngle() {
        Point2D center = new Point(0, 0);
        Point2D click1 = new Point(0, 1);
        Point2D click2 = new Point(1, 2);

        System.out.println(Helper.lawOfCosines(Helper.getDistance(click1, click2), Helper.getDistance(click2, center), Helper.getDistance(center, click1)));

    }
}
