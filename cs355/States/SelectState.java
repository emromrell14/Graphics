package cs355.States;

import cs355.Controller.ControllerImpl;
import cs355.Helper;
import cs355.Shapes.Line;
import cs355.Shapes.Shape;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by eric on 5/1/15.
 */
public class SelectState implements State {
    private ControllerImpl parent;
    private Shape selectedShape;
    private Point2D selectedLocation;
    private boolean handleClicked;
    private int whichHandle = 0; //This is only used to track which handle on the line was clicked

    public SelectState(ControllerImpl parent) {
        this.parent = parent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(selectedShape instanceof Line) {
            whichHandle = parent.checkForLineHandle(selectedShape, e.getPoint());
            if(whichHandle != 0) {
                selectedLocation = null;
                handleClicked = true;
            } else {
                handleClicked = false;
                selectedShape = parent.checkForSelection(e.getPoint());
                selectedLocation = e.getPoint();
            }
        } else {
            if (parent.checkForHandle(e.getPoint())) {
                selectedLocation = null;
                handleClicked = true;
            } else {
                handleClicked = false;
                selectedShape = parent.checkForSelection(e.getPoint());
                selectedLocation = e.getPoint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectedShape != null) {
            if(handleClicked) {
                if(whichHandle != 0) {
                    parent.moveLineEndpoint(whichHandle, e.getPoint());
                } else {
                    parent.rotateShape(calculateRotationAngle(e.getPoint()));
                }
            } else {
                parent.moveShape(e.getX() - selectedLocation.getX(), e.getY() - selectedLocation.getY());
                selectedLocation = e.getPoint();
            }
        }
    }

    private double calculateRotationAngle(Point2D point) {
        Point2D newPoint = Helper.viewToWorld(point);

        //All this is doing is creating a point straight down from the center so that we can calculate the angle from it
        Point2D pointBelow = new Point2D.Double(selectedShape.getCenter().getX(), selectedShape.getCenter().getY() + 100);

        double a = Helper.getDistance(pointBelow, newPoint);
        double b = Helper.getDistance(pointBelow, selectedShape.getCenter());
        double c = Helper.getDistance(newPoint, selectedShape.getCenter());

        if(newPoint.getX() < selectedShape.getCenter().getX()) {
            return Helper.lawOfCosines(a, b, c);
        } else {
            return 2 * Math.PI - Helper.lawOfCosines(a, b, c);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
