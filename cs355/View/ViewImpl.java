package cs355.View;

import cs355.GUIFunctions;
import cs355.Helper;
import cs355.Model.ModelImpl;
import cs355.Shapes.*;
import cs355.Shapes.Shape;
import cs355.Vector;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by eric on 4/29/15.
 */
public class ViewImpl implements ViewRefresher, Observer {
    private ModelImpl model;



    public void setModel(ModelImpl model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        GUIFunctions.refresh();
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        //Draw all shapes
        for (Shape shape : model.getShapes()) {
            drawShape(g2d, shape, false);
        }

        if(model.getSelectedShape() != null) {
            drawShape(g2d, model.getSelectedShape(), true);
        }
    }

    protected void drawShape(Graphics2D g2d, Shape shape, boolean outline) {
        //Set the color
        if (outline) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(shape.getColor());
        }

        //Draw the shape
        if (shape instanceof Line) {
            drawLine(g2d, (Line) shape);
        } else if (shape instanceof Rectangle) {
            drawRectangle(g2d, (Rectangle) shape, outline);
        } else if (shape instanceof Square) {
            drawSquare(g2d, (Square) shape, outline);
        } else if (shape instanceof Circle) {
            drawCircle(g2d, (Circle) shape, outline);
        } else if (shape instanceof Ellipse) {
            drawEllipse(g2d, (Ellipse) shape, outline);
        } else if (shape instanceof Triangle) {
            drawTriangle(g2d, (Triangle) shape, outline);
        } else {
            System.out.println("I don't know what to do with a " + shape.getClass().getName());
        }
    }

    protected void drawLine(Graphics2D g2d, Line line) {
        //Erase any transforms that have been applied
        g2d.setTransform(Helper.worldToView());

        //Draw the line
        g2d.drawLine((int) line.getPoint1().getX(), (int) line.getPoint1().getY(), (int) line.getPoint2().getX(), (int) line.getPoint2().getY());

        if(model.getSelectedShape() == line) {
            for (Square handle : line.getHandles()) {
                g2d.drawRect((int) handle.calculateTopLeftCorner().getX() - Shape.HANDLE_WIDTH() / 2, (int) handle.calculateTopLeftCorner().getY() - Shape.HANDLE_WIDTH() / 2, Shape.HANDLE_WIDTH(), Shape.HANDLE_WIDTH());
            }
        }
    }

    protected void drawSquare(Graphics2D g2d, Square square, boolean outline) {
        //Calculate all of the transforms
        AffineTransform transform = Helper.objectToView(square);
        g2d.setTransform(transform);

        int halfWidth = (int) (square.getWidth() / 2);

        //Draw the square
        if(outline) {
            g2d.drawRect(-halfWidth, -halfWidth, (int) square.getWidth(), (int) square.getWidth());

            //Draw the handle
            final Vector displacement = square.getHandleDisplacement();
            transform.translate(displacement.get(0), displacement.get(1));
            g2d.setTransform(transform);
            g2d.drawRect(0, 0, Shape.HANDLE_WIDTH(), Shape.HANDLE_WIDTH());
        } else {
            g2d.fillRect(-halfWidth, -halfWidth, (int) square.getWidth(), (int) square.getWidth());
        }
    }

    protected void drawRectangle(Graphics2D g2d, Rectangle rectangle, boolean outline) {
        //Calculate all of the transforms
        AffineTransform transform = Helper.objectToView(rectangle);
        g2d.setTransform(transform);

        int halfWidth = (int) (rectangle.getWidth() / 2);
        int halfHeight = (int) (rectangle.getHeight() / 2);

        //Draw the rectangle
        if(outline) {
            g2d.drawRect(-halfWidth, -halfHeight, (int) rectangle.getWidth(), (int) rectangle.getHeight());

            //Draw the handle
            final Vector displacement = rectangle.getHandleDisplacement();
            transform.translate(displacement.get(0), displacement.get(1));
            g2d.setTransform(transform);
            g2d.drawRect(0, 0, (int) (Shape.HANDLE_WIDTH() * Helper.screenScale), (int) (Shape.HANDLE_WIDTH() * Helper.screenScale));
        } else {
            g2d.fillRect(-halfWidth, -halfHeight, (int) rectangle.getWidth(), (int) rectangle.getHeight());
        }
    }

    protected void drawCircle(Graphics2D g2d, Circle circle, boolean outline) {
        //Apply the transformations
        AffineTransform transform = Helper.objectToView(circle);
        g2d.setTransform(transform);

        //Draw the circle
        if(outline) {
            g2d.drawOval((int) -circle.getRadius(), (int) -circle.getRadius(), (int) circle.getRadius() * 2, (int) circle.getRadius() * 2);
        } else {
            g2d.fillOval((int) -circle.getRadius(), (int) -circle.getRadius(), (int) circle.getRadius() * 2, (int) circle.getRadius() * 2);
        }
    }

    protected void drawEllipse(Graphics2D g2d, Ellipse ellipse, boolean outline) {
        //Apply the transformations
        AffineTransform transform = Helper.objectToView(ellipse);
        g2d.setTransform(transform);

        int halfWidth = (int) (ellipse.getWidth() / 2);
        int halfHeight = (int) (ellipse.getHeight() / 2);

        //Draw the ellipse
        if(outline) {
            g2d.drawOval(-halfWidth, -halfHeight, (int) ellipse.getWidth(), (int) ellipse.getHeight());

            //Draw the handle
            final Vector displacement = ellipse.getHandleDisplacement();
            transform.translate(displacement.get(0), displacement.get(1));
            g2d.setTransform(transform);
            g2d.drawRect(0, 0, Shape.HANDLE_WIDTH(), Shape.HANDLE_WIDTH());
        } else {
            g2d.fillOval(-halfWidth, -halfHeight, (int) ellipse.getWidth(), (int) ellipse.getHeight());
        }
    }

    protected void drawTriangle(Graphics2D g2d, Triangle triangle, boolean outline) {
        //Apply the transformations
        AffineTransform transform = Helper.objectToView(triangle);
        g2d.setTransform(transform);

        //Draw the triangle
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        xPoints[0] = (int) triangle.getPoint1().getX();
        xPoints[1] = (int) triangle.getPoint2().getX();
        xPoints[2] = (int) triangle.getPoint3().getX();
        yPoints[0] = (int) triangle.getPoint1().getY();
        yPoints[1] = (int) triangle.getPoint2().getY();
        yPoints[2] = (int) triangle.getPoint3().getY();

        if(outline) {
            g2d.drawPolygon(xPoints, yPoints, 3);

            //Draw the handle
            final Vector displacement = triangle.getHandleDisplacement();
            transform.translate(displacement.get(0), displacement.get(1));
            g2d.setTransform(transform);
            g2d.drawRect(0, 0, Shape.HANDLE_WIDTH(), Shape.HANDLE_WIDTH());
        } else {
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }
}
