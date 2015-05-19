package cs355.States;

import cs355.Controller.ControllerImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by eric on 4/29/15.
 */
public class EllipseState implements State {
    private ControllerImpl parent;
    private Point2D firstClick;

    public EllipseState(ControllerImpl parent) {
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
        parent.addEllipse(firstClick, e.getPoint(), true);
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
            parent.addEllipse(firstClick, e.getPoint(), false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
