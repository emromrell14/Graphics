package cs355.Controller;

import cs355.GUIFunctions;
import cs355.Helper;
import cs355.Model.ModelImpl;
import cs355.Shapes.*;
import cs355.Shapes.Rectangle;
import cs355.Shapes.Shape;
import cs355.States.*;
import cs355.Vector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

/**
 * Created by eric on 4/29/15.
 */
public class ControllerImpl implements CS355Controller, MouseMotionListener, MouseListener {
    private State state = new BlankState();
    private ModelImpl model;
    private Color selectedColor;

    public ControllerImpl(ModelImpl model) {
        this.model = model;
        this.selectedColor = Color.WHITE;
    }

    @Override
    public void colorButtonHit(Color c) {
        GUIFunctions.changeSelectedColor(c);
        if(model.getSelectedShape() != null) {
            model.getSelectedShape().setColor(c);
            model.updateScreen();
        }
        this.selectedColor = c;
    }

    @Override
    public void lineButtonHit() {
        state = new LineState(this);
        model.selectShape(-1);
    }

    @Override
    public void squareButtonHit() {
        state = new SquareState(this);
        model.selectShape(-1);
    }

    @Override
    public void rectangleButtonHit() {
        state = new RectangleState(this);
        model.selectShape(-1);
    }

    @Override
    public void circleButtonHit() {
        state = new CircleState(this);
        model.selectShape(-1);
    }

    @Override
    public void ellipseButtonHit() {
        state = new EllipseState(this);
        model.selectShape(-1);
    }

    @Override
    public void triangleButtonHit() {
        state = new TriangleState(this);
        model.selectShape(-1);
    }

    @Override
    public void selectButtonHit() {
        state = new SelectState(this);
    }

    @Override
    public void zoomInButtonHit() {
        if(Helper.screenScale > .25) {
            zoom(.5);
        }
    }

    @Override
    public void zoomOutButtonHit() {
        if(Helper.screenScale < 4) {
            zoom(2);
        }
    }

    private void zoom(double zoomFactor) {
        Helper.screenScale *= zoomFactor;

        //Change the size of the bar
        GUIFunctions.setHScrollBarKnob((int) getBarSize());
        GUIFunctions.setVScrollBarKnob((int) getBarSize());

        //Change the position of the bar
        Vector deltaOffset = getNormalizedOffset(zoomFactor);
        GUIFunctions.setHScrollBarPosit((int) deltaOffset.get(0));
        GUIFunctions.setVScrollBarPosit((int) deltaOffset.get(1));

        //Change the size of the bar
        GUIFunctions.setHScrollBarKnob((int) getBarSize());
        GUIFunctions.setVScrollBarKnob((int) getBarSize());

        GUIFunctions.refresh();
    }

    private double getBarSize() {
        return Helper.screenScale * Helper.DEFAULT_SCREEN_SIZE;
    }

    private Vector getNormalizedOffset(double zoomFactor) {
        double x = Helper.topLeftOffset.get(0);
        double y = Helper.topLeftOffset.get(1);

        if(zoomFactor < 1) {
            x += getBarSize() / 2;
            y += getBarSize() / 2;
        } else {
            x -= getBarSize() / 4;
            y -= getBarSize() / 4;
        }

        x = restrictToBounds(x);
        y = restrictToBounds(y);

        return new Vector(x, y);
    }

    private double restrictToBounds(double value) {
        if(value < 0) {
            value = 0;
        } else if(value >= (Helper.DEFAULT_SCREEN_SIZE * 4 - getBarSize())) {
            value = Helper.DEFAULT_SCREEN_SIZE * 4 - getBarSize();
        }
        return value;
    }

    @Override
    public void hScrollbarChanged(int value) {
        Helper.topLeftOffset.set(0, value);
        model.updateScreen();
    }

    @Override
    public void vScrollbarChanged(int value) {
        Helper.topLeftOffset.set(1, value);
        model.updateScreen();
    }

    @Override
    public void toggle3DModelDisplay() {

    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {

    }

    @Override
    public void doEdgeDetection() {

    }

    @Override
    public void doSharpen() {

    }

    @Override
    public void doMedianBlur() {

    }

    @Override
    public void doUniformBlur() {

    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {

    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {

    }

    @Override
    public void doLoadImage(BufferedImage openImage) {

    }

    @Override
    public void toggleBackgroundDisplay() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        state.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        state.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        state.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        state.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        state.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        state.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        state.mouseMoved(e);
    }

    public void addLine(final Point2D point1, final Point2D point2, boolean finished) {
        Point2D newPoint1 = Helper.viewToWorld(point1);
        Point2D newPoint2 = Helper.viewToWorld(point2);

        Line line = new Line(this.selectedColor, newPoint1, newPoint2);
        model.addShape(line);

        if(finished) {
            model.incrementCurrentShape();
        }
    }

    public void addSquare(final Point2D point1, final Point2D point2, boolean finished) {
        Point2D newPoint1 = Helper.viewToWorld(point1);
        Point2D newPoint2 = Helper.viewToWorld(point2);

        //Normalize the points so that we're always drawing from the top left corner to the bottom right corner
        Point2D[] translation = translatePoints(newPoint1, newPoint2, true);
        newPoint1 = translation[0];
        newPoint2 = translation[1];

        Square square = new Square(this.selectedColor, newPoint1, Math.min(newPoint2.getX() - newPoint1.getX(), newPoint2.getY() - newPoint1.getY()));
        model.addShape(square);

        if(finished) {
            model.incrementCurrentShape();
        }
    }

    public void addRectangle(final Point2D point1, final Point2D point2, boolean finished) {
        Point2D newPoint1 = Helper.viewToWorld(point1);
        Point2D newPoint2 = Helper.viewToWorld(point2);

        //Normalize the points so that we're always drawing from the top left corner to the bottom right corner
        Point2D[] translation = translatePoints(newPoint1, newPoint2, false);
        newPoint1 = translation[0];
        newPoint2 = translation[1];

        Rectangle rectangle = new Rectangle(this.selectedColor, newPoint1, newPoint2.getY() - newPoint1.getY(), newPoint2.getX() - newPoint1.getX());
        model.addShape(rectangle);

        if(finished) {
            model.incrementCurrentShape();
        }
    }

    public void addCircle(final Point2D point1, final Point2D point2, boolean finished) {
        Point2D newPoint1 = Helper.viewToWorld(point1);
        Point2D newPoint2 = Helper.viewToWorld(point2);

        //Normalize the points so that we're always drawing from the top left corner to the bottom right corner
        Point2D[] translation = translatePoints(newPoint1, newPoint2, true);
        newPoint1 = translation[0];
        newPoint2 = translation[1];

        double radius = Math.min(newPoint2.getY() - newPoint1.getY(), newPoint2.getX() - newPoint1.getX()) / 2;
        Point2D origin = new Point2D.Double(newPoint1.getX() + radius, newPoint1.getY() + radius);
        Circle circle = new Circle(this.selectedColor, origin, radius);
        model.addShape(circle);

        if(finished) {
            model.incrementCurrentShape();
        }
    }

    public void addEllipse(final Point2D point1, final Point2D point2, boolean finished) {
        Point2D newPoint1 = Helper.viewToWorld(point1);
        Point2D newPoint2 = Helper.viewToWorld(point2);

        //Normalize the points so that we're always drawing from the top left corner to the bottom right corner
        Point2D[] translation = translatePoints(newPoint1, newPoint2, false);
        newPoint1 = translation[0];
        newPoint2 = translation[1];

        double height = newPoint2.getY() - newPoint1.getY();
        double width = newPoint2.getX() - newPoint1.getX();

        Point2D origin = new Point2D.Double(newPoint1.getX() + width/2, newPoint1.getY() + height/2);
        Ellipse ellipse = new Ellipse(this.selectedColor, origin, height, width);
        model.addShape(ellipse);

        if(finished) {
            model.incrementCurrentShape();
        }
    }

    public void addTriangle(final Point2D point1, final Point2D point2, Point2D point3) {
        Point2D newPoint1 = Helper.viewToWorld(point1);
        Point2D newPoint2 = Helper.viewToWorld(point2);
        Point2D newPoint3 = Helper.viewToWorld(point3);

        model.addShape(new Triangle(this.selectedColor, newPoint1, newPoint2, newPoint3));
        model.incrementCurrentShape();
    }

    public boolean checkForHandle(final Point2D point) {
        final Shape selectedShape = model.getSelectedShape();
        if(selectedShape != null) {
            for (Square handle : selectedShape.getHandles()) {
                if(handle.isClickInHandle(selectedShape, point)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int checkForLineHandle(Shape line, final Point2D point) {
        final List<Square> handles = line.getHandles();
        for(int i = 0; i < handles.size(); i++) {
            if(handles.get(i).isClickInHandle(line, point)) {
                return i + 1;
            }
        }
        return 0;
    }

    public Shape checkForSelection(final Point2D point) {
        int indexOfSelectedShape = -1;

        for (int i=0; i < model.getShapes().size(); i++) {
            if(model.getShapes().get(i).isClickInShape(point)) {
                indexOfSelectedShape = i;
            }
        }

        return model.selectShape(indexOfSelectedShape);
    }

    public void moveShape(double deltaX, double deltaY) {
        deltaX *= Helper.screenScale;
        deltaY *= Helper.screenScale;

        model.getSelectedShape().moveCenter(deltaX, deltaY);
        model.updateScreen();
    }

    public void rotateShape(double radians) {
        model.getSelectedShape().setRotationAngle(radians);
        model.updateScreen();
    }

    protected Point2D[] translatePoints(final Point2D point1, final Point2D point2, boolean keepRatio) {
        Point2D[] results = new Point2D.Double[2];
        double min = Math.min(Math.abs(point2.getX() - point1.getX()), Math.abs(point2.getY() - point1.getY()));
        if(min % 2 != 0) {
            min += 1;
        }
        if(point1.getX() < point2.getX()) {
            if(point1.getY() < point2.getY()) {
                //Top left to bottom right. Normal case
                results[0] = new Point2D.Double(point1.getX(), point1.getY());
                if(keepRatio) {
                    results[1] = new Point2D.Double(point1.getX() + min, point1.getY() + min);
                } else {
                    results[1] = new Point2D.Double(point2.getX(), point2.getY());
                }
            } else {
                //Bottom left to top right. Switch y values
                if(keepRatio) {
                    results[0] = new Point2D.Double(point1.getX(), point1.getY() - min);
                    results[1] = new Point2D.Double(point1.getX() + min, point1.getY());
                } else {
                    results[0] = new Point2D.Double(point1.getX(), point2.getY());
                    results[1] = new Point2D.Double(point2.getX(), point1.getY());
                }
            }
        } else {
            if(point1.getY() < point2.getY()) {
                //Top right to bottom left. Switch x values
                if(keepRatio) {
                    results[0] = new Point2D.Double(point1.getX() - min, point1.getY());
                    results[1] = new Point2D.Double(point1.getX(), point1.getY() + min);
                } else {
                    results[0] = new Point2D.Double(point2.getX(), point1.getY());
                    results[1] = new Point2D.Double(point1.getX(), point2.getY());
                }
            } else {
                //Bottom right to top left. Switch points
                if(keepRatio) {
                    results[0] = new Point2D.Double(point1.getX() - min, point1.getY() - min);
                } else {
                    results[0] = new Point2D.Double(point2.getX(), point2.getY());
                }
                results[1] = new Point2D.Double(point1.getX(), point1.getY());
            }
        }
        return results;
    }

    public void moveLineEndpoint(int whichHandle, final Point point) {
        model.moveSelectedLineEndpoint(whichHandle, point);
    }
}
