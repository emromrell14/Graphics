package cs355.LWJGL;


//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.

import cs355.*;
import org.lwjgl.input.Keyboard;

import java.util.Iterator;

import static cs355.LWJGL.LWJGLSandbox.DISPLAY_HEIGHT;
import static cs355.LWJGL.LWJGLSandbox.DISPLAY_WIDTH;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * @author Brennan Smith
 */
public class ControllerImpl implements CS355LWJGLController {
    public static final double MOVEMENT = .25;
    public static final double ROTATION = 1;
    //This is a model of a house.
    //It has a single method that returns an iterator full of Line3Ds.
    //A "Line3D" is a wrapper class around two Point2Ds.
    //It should all be fairly intuitive if you look at those classes.
    //If not, I apologize.
    private WireFrame model = new HouseModel();
    private Point3D location = new Point3D(0, 0, -20);
    private double rotationAngle = 0;

    //This method is called to "resize" the viewport to match the screen.
    //When you first start, have it be in perspective mode.
    @Override
    public void resizeGL() {
        gluPerspective(90, DISPLAY_WIDTH / DISPLAY_HEIGHT, 1, 100);
        glViewport(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);

        glMatrixMode(GL_MODELVIEW);

        //Begin looking from 20 units away from the origin
        glTranslated(0, 0, -20);
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
        glMatrixMode(GL_MODELVIEW);
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            System.out.println("I should move left");
            double angleInRadians = rotationAngle * Math.PI / 180;
            double deltaX = Math.cos(angleInRadians) * MOVEMENT;
            double deltaZ = Math.sin(angleInRadians) * MOVEMENT;
            location.x += deltaX;
            location.z += deltaZ;
            glTranslated(deltaX, 0, deltaZ);
            System.out.println("Location: " + location);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            System.out.println("I should move right");
            double angleInRadians = rotationAngle * Math.PI / 180;
            double deltaX = Math.cos(angleInRadians) * MOVEMENT;
            double deltaZ = Math.sin(angleInRadians) * MOVEMENT;
            location.x -= deltaX;
            location.z -= deltaZ;
            glTranslated(-deltaX, 0, -deltaZ);
            System.out.println("Location: " + location);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            System.out.println("I should move forward");
            double angleInRadians = rotationAngle * Math.PI / 180;
            double deltaX = Math.sin(angleInRadians) * MOVEMENT;
            double deltaZ = Math.cos(angleInRadians) * MOVEMENT;
            location.x += deltaX;
            location.z += deltaZ;
            glTranslated(deltaX, 0, deltaZ);
            System.out.println("Location: " + location);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            System.out.println("I should move backward");
            double angleInRadians = rotationAngle * Math.PI / 180;
            double deltaX = Math.sin(angleInRadians) * MOVEMENT;
            double deltaZ = Math.cos(angleInRadians) * MOVEMENT;
            location.x -= deltaX;
            location.z -= deltaZ;
            glTranslated(-deltaX, 0, -deltaZ);
            System.out.println("Location: " + location);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
//            System.out.println("I should turn left");
            rotationAngle -= ROTATION;
            System.out.println("Angle: " + rotationAngle);
            glTranslated(-location.x, -location.y, -location.z);
            glRotated(-ROTATION, 0, 1, 0);
            glTranslated(location.x, location.y, location.z);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
//            System.out.println("I should turn right");
            rotationAngle += ROTATION;
            glTranslated(-location.x, -location.y, -location.z);
            glRotated(ROTATION, 0, 1, 0);
            glTranslated(location.x, location.y, location.z);
            System.out.println("Angle: " + rotationAngle);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            System.out.println("I should move up");
            location.y += MOVEMENT;
            glTranslated(0, MOVEMENT, 0);
            System.out.println("Location: " + location);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            System.out.println("I should move down");
            location.y -= MOVEMENT;
            glTranslated(0, -MOVEMENT, 0);
            System.out.println("Location: " + location);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
            System.out.println("I should return to the original 'home' position and orientation");
            glTranslated(-location.x, -location.y, -location.z);
            glRotated(-rotationAngle, 0, 1, 0);
            glTranslated(location.x, location.y, location.z);
            glTranslated(-location.x, -location.y, -location.z - 20);
            location.x = 0;
            location.y = 0;
            location.z = -20;
            rotationAngle = 0;
            System.out.println("Location: " + location);
        }

        glMatrixMode(GL_PROJECTION);
        if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
            System.out.println("I should switch to orthographic projection");
//            glOrtho(location.x - 20, location.x + 20, location.y - 20, location.y + 20, 1, 100);
//            glOrtho(0, 0, 0, 0, 0, 0);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
            System.out.println("I should switch to perspective projection");
            gluPerspective(0, 0, 0, 0);
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render() {
        //This clears the screen.
        glClear(GL_COLOR_BUFFER_BIT);

        //Do your drawing here.

        glColor3d(1, 1, 1);
        final Iterator<Line3D> lines = model.getLines();
        while(lines.hasNext()) {
            glBegin(GL_LINES);
            final Line3D line = lines.next();
            final Point3D start = line.start;
            final Point3D end = line.end;
            glVertex3d(start.x, start.y, start.z);
            glVertex3d(end.x, end.y, end.z);
            glEnd();
        }
    }
}
