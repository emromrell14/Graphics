package cs355.States;

import cs355.Controller.ControllerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by eric on 4/29/15.
 */
public class TriangleState implements State {
    private ControllerImpl parent;
    private Point2D firstClick;
    private Point2D secondClick;

    public TriangleState(ControllerImpl parent) {
        this.parent = parent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(firstClick == null) {
            firstClick = e.getPoint();
        } else if(secondClick == null) {
            secondClick = e.getPoint();
        } else {
            parent.addTriangle(firstClick, secondClick, e.getPoint());
            firstClick = null;
            secondClick = null;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
