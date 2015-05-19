package cs355.States;

import java.awt.event.MouseEvent;

/**
 * Created by eric on 4/29/15.
 */
public interface State {
    void mouseClicked(MouseEvent e);
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseEntered(MouseEvent e);
    void mouseExited(MouseEvent e);
    void mouseDragged(MouseEvent e);
    void mouseMoved(MouseEvent e);

}
