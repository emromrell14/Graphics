package cs355.LWJGL;


//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.

import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController {

    //This is a model of a house.
    //It has a single method that returns an iterator full of Line3Ds.
    //A "Line3D" is a wrapper class around two Point2Ds.
    //It should all be fairly intuitive if you look at those classes.
    //If not, I apologize.
    private WireFrame model = new HouseModel();

    //This method is called to "resize" the viewport to match the screen.
    //When you first start, have it be in perspective mode.
    @Override
    public void resizeGL() {

    }

    @Override
    public void update() {

    }

    //This is called every frame, and should be responsible for keyboard updates.
    //An example keyboard event is captured below.
    //The "Keyboard" static class should contain everything you need to finish
    // this up.
    @Override
    public void updateKeyboard() {
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            System.out.println("I should move left");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            System.out.println("I should move right");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            System.out.println("I should move forward");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            System.out.println("I should move backward");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            System.out.println("I should turn left");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            System.out.println("I should turn right");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            System.out.println("I should move up");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            System.out.println("I should move down");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
            System.out.println("I should return to the original 'home' position and orientation");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
            System.out.println("I should switch to orthographic projection");
        } else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
            System.out.println("I should switch to perspective projection");
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render() {
        //This clears the screen.
        glClear(GL_COLOR_BUFFER_BIT);

        //Do your drawing here.
    }

}
