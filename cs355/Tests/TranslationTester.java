package cs355.Tests;

import cs355.Controller.ControllerImpl;
import cs355.Model.ModelImpl;
import cs355.Shapes.*;
import cs355.Shapes.Shape;
import cs355.States.*;
import cs355.View.ViewImpl;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by eric on 4/30/15.
 */
public class TranslationTester {

    MyController controller;

    @Before
    public void setUp() {
        controller = new MyController(null);
    }

    @Test
    public void testPerfectSquares() {
        Point2D point1 = new Point(0, 0);
        Point2D point2 = new Point(200, 200);

        Point2D[] translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 200);
        assertTrue(translation[1].getY() == 200);

        point1 = new Point(0, 200);
        point2 = new Point(200, 0);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 200);
        assertTrue(translation[1].getY() == 200);

        point1 = new Point(200, 0);
        point2 = new Point(0, 200);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 200);
        assertTrue(translation[1].getY() == 200);

        point1 = new Point(200, 200);
        point2 = new Point(0, 0);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 200);
        assertTrue(translation[1].getY() == 200);
    }

    @Test
    public void testImperfectVerticalSquares() {
        Point2D point1 = new Point(0, 0);
        Point2D point2 = new Point(100, 200);

        Point2D[] translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 100);
        assertTrue(translation[1].getY() == 100);

        point1 = new Point(0, 200);
        point2 = new Point(100, 0);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 100);
        assertTrue(translation[1].getX() == 100);
        assertTrue(translation[1].getY() == 200);

        point1 = new Point(100, 0);
        point2 = new Point(0, 200);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 100);
        assertTrue(translation[1].getY() == 100);

        point1 = new Point(100, 200);
        point2 = new Point(0, 0);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 100);
        assertTrue(translation[1].getX() == 100);
        assertTrue(translation[1].getY() == 200);
    }

    @Test
    public void testImperfectHorizontalSquares() {
        Point2D point1 = new Point(0, 0);
        Point2D point2 = new Point(200, 100);

        Point2D[] translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 100);
        assertTrue(translation[1].getY() == 100);

        point1 = new Point(0, 100);
        point2 = new Point(200, 0);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 0);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 100);
        assertTrue(translation[1].getY() == 100);

        point1 = new Point(200, 0);
        point2 = new Point(0, 100);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 100);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 200);
        assertTrue(translation[1].getY() == 100);

        point1 = new Point(200, 100);
        point2 = new Point(0, 0);
        translation = controller.translatePoints(point1, point2, true);
        assertTrue(translation[0].getX() == 100);
        assertTrue(translation[0].getY() == 0);
        assertTrue(translation[1].getX() == 200);
        assertTrue(translation[1].getY() == 100);
    }


    private class MyController extends ControllerImpl {

        public MyController(ModelImpl model) {
            super(model);
        }

        @Override
        protected Point2D[] translatePoints(Point2D point1, Point2D point2, boolean keepRatio) {
            return super.translatePoints(point1, point2, keepRatio);
        }
    }
}