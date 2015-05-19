/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.Controller.ControllerImpl;
import cs355.GUIFunctions;
import cs355.Helper;
import cs355.Model.ModelImpl;
import cs355.View.ViewImpl;

import javax.naming.ldap.Control;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author [your name here]
 */
public class CS355 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	// Fill in the parameters below with your controller, view, 
    	//   mouse listener, and mouse motion listener
        ViewImpl view = new ViewImpl();
        ModelImpl model = new ModelImpl(view);
        view.setModel(model);
        ControllerImpl controller = new ControllerImpl(model);
        GUIFunctions.createCS355Frame(controller, view, controller, controller);
        GUIFunctions.setHScrollBarMax(Helper.DEFAULT_SCREEN_SIZE * 4);
        GUIFunctions.setVScrollBarMax(Helper.DEFAULT_SCREEN_SIZE * 4);
        GUIFunctions.setHScrollBarKnob(Helper.DEFAULT_SCREEN_SIZE);
        GUIFunctions.setVScrollBarKnob(Helper.DEFAULT_SCREEN_SIZE);
        GUIFunctions.setHScrollBarPosit(0); //ControllerImpl.DEFAULT_SCREEN_SIZE + ControllerImpl.DEFAULT_SCREEN_SIZE / 2);
        GUIFunctions.setVScrollBarPosit(0); //ControllerImpl.DEFAULT_SCREEN_SIZE + ControllerImpl.DEFAULT_SCREEN_SIZE / 2);

        GUIFunctions.refresh();        
    }
}