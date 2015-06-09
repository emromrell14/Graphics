package cs355.Model;

import cs355.*;
import cs355.Shapes.Line;
import cs355.Shapes.Shape;
import cs355.Shapes.Square;
import cs355.View.ViewImpl;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by eric on 4/29/15.
 */
public class ModelImpl extends Observable {
    public List<Shape> shapes = new ArrayList<Shape>();
    public int currentShape = 0;
    private Shape selectedShape = null;
    private Image image = null;

    public ModelImpl(ViewImpl view) {
        addObserver(view);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void incrementCurrentShape() {
        currentShape++;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public void addShape(Shape shape) {
        if(shapes.size() == currentShape) {
            shapes.add(shape);
        } else {
            shapes.set(currentShape, shape);
        }

        setChanged();
        notifyObservers();
    }

    public Shape selectShape(int index) {
        if(index == -1) {
            selectedShape = null;
        } else {
            selectedShape = shapes.get(index);
        }

        updateScreen();

        return selectedShape;
    }

    public void moveSelectedLineEndpoint(int whichHandle, Point2D point) {
        switch (whichHandle) {
            case 1:
                ((Line) selectedShape).setPoint1(point);
                break;
            case 2:
                ((Line) selectedShape).setPoint2(point);
                break;
            default:
                throw new IllegalArgumentException();
        }

        updateScreen();
    }

    public void updateScreen() {
        setChanged();
        notifyObservers();
    }
}
