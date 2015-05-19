package cs355.States;

import cs355.Controller.ControllerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by eric on 4/29/15.
 */
public class SquareState implements State {
    private ControllerImpl parent;
    private Point2D firstClick;

    public SquareState(ControllerImpl parent) {
        this.parent = parent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        firstClick = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        parent.addSquare(firstClick, e.getPoint(), true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(firstClick != null) {
            parent.addSquare(firstClick, e.getPoint(), false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
