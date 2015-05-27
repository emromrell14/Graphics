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
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

/**
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController {

    public static final int MOVEMENT = 2;
    public static final double ROTATION = Math.PI / 32;

    //This is a model of a house.
    //It has a single method that returns an iterator full of Line3Ds.
    //A "Line3D" is a wrapper class around two Point2Ds.
    //It should all be fairly intuitive if you look at those classes.
    //If not, I apologize.
    private WireFrame model = new HouseModel();
    private Point3D location = new Point3D(0, 0, 0);
    private double rotationAngle = 0;

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
            location.x -= MOVEMENT;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            System.out.println("I should move right");
            location.x += MOVEMENT;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            System.out.println("I should move forward");
            location.z += MOVEMENT;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            System.out.println("I should move backward");
            location.z -= MOVEMENT;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            System.out.println("I should turn left");
            rotationAngle += ROTATION;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            System.out.println("I should turn right");
            rotationAngle -= ROTATION;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            System.out.println("I should move up");
            location.y += MOVEMENT;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            System.out.println("I should move down");
            location.y -= MOVEMENT;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
            System.out.println("I should return to the original 'home' position and orientation");
            location.x = 0;
            location.y = 0;
            location.z = 0;
            rotationAngle = 0;
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
        glColor3f(1, 1, 1);
        glLoadIdentity();

    }

}
