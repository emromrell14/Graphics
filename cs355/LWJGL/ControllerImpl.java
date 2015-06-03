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
import sun.org.mozilla.javascript.internal.ast.ForInLoop;

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
    public static final Point3D HOME_LOCATION = new Point3D(0, 0, -20);
    public static final int ORTHO_VIEW_AMOUNT = 20;
    public static final int Z_NEAR = 1;
    public static final int Z_FAR = 100;
    public static final int FIELD_OF_VIEW_ANGLE = 90;
    //This is a model of a house.
    //It has a single method that returns an iterator full of Line3Ds.
    //A "Line3D" is a wrapper class around two Point2Ds.
    //It should all be fairly intuitive if you look at those classes.
    //If not, I apologize.
    private WireFrame model = new HouseModel();
    private Point3D location = new Point3D(HOME_LOCATION);
    private Point3D displacement = new Point3D(HOME_LOCATION);
    private double rotationAngle = 0;
    public boolean goOrtho = true;
    public boolean goPerspective = true;

    //This method is called to "resize" the viewport to match the screen.
    //When you first start, have it be in perspective mode.
    @Override
    public void resizeGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(FIELD_OF_VIEW_ANGLE, DISPLAY_WIDTH / DISPLAY_HEIGHT, Z_NEAR, Z_FAR);
        glViewport(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);

        glMatrixMode(GL_MODELVIEW);
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
            moveX(false);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            moveX(true);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            moveY(true);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
            moveY(false);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            moveZ(false);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            moveZ(true);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            rotateX(true);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            rotateX(false);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
            goHome();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
            goToOrtho();
            goOrtho = false;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
            goToPerspective();
            goPerspective = false;
        }
    }

    private void move(double deltaX, double deltaY, double deltaZ) {
        location.x += deltaX;
        location.y += deltaY;
        location.z += deltaZ;

        displacement.x += deltaX;
        displacement.y += deltaY;
        displacement.z += deltaZ;
    }

    private void moveX(boolean movingLeft) {
        double angleInRadians = rotationAngle * Math.PI / 180;
        double deltaX = Math.cos(angleInRadians) * MOVEMENT;
        double deltaZ = Math.sin(angleInRadians) * MOVEMENT;

        if(movingLeft) {
            deltaX *= -1;
            deltaZ *= -1;
        }

        move(deltaX, 0, deltaZ);
    }

    private void moveY(boolean movingUp) {
        double deltaY = MOVEMENT;
        if(movingUp) {
            deltaY *= -1;
        }

        move(0, deltaY, 0);
    }

    private void moveZ(boolean movingOut) {
        double angleInRadians = rotationAngle * Math.PI / 180;
        double deltaX = Math.sin(-angleInRadians) * MOVEMENT;
        double deltaZ = Math.cos(-angleInRadians) * MOVEMENT;

        if(movingOut) {
            deltaX *= -1;
            deltaZ *= -1;
        }

        move(deltaX, 0, deltaZ);
    }

    private void rotateX(boolean rotatingLeft) {
        double deltaRotation = ROTATION;
        if(rotatingLeft) {
            deltaRotation *= -1;
        }

        rotationAngle += deltaRotation;
    }

    private void goHome() {
        location = new Point3D(HOME_LOCATION);
        rotationAngle = 0;
    }

    private void goToOrtho() {
        System.out.println("I should switch to orthographic projection");
        glMatrixMode(GL_PROJECTION);

        glLoadIdentity();
        glOrtho(-ORTHO_VIEW_AMOUNT, ORTHO_VIEW_AMOUNT, -ORTHO_VIEW_AMOUNT, ORTHO_VIEW_AMOUNT, Z_NEAR, Z_FAR);
    }

    private void goToPerspective() {
        System.out.println("I should switch to perspective projection");
        glMatrixMode(GL_PROJECTION);

        glLoadIdentity();
        gluPerspective(FIELD_OF_VIEW_ANGLE, DISPLAY_WIDTH / DISPLAY_HEIGHT, Z_NEAR, Z_FAR);
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render() {
        //This clears the screen.
        glClear(GL_COLOR_BUFFER_BIT);

        //Do your drawing here.
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glRotated(rotationAngle, 0, 1, 0);
        glTranslated(location.x, location.y, location.z);

        glColor3d(1, 1, 1);
        glBegin(GL_LINES);
        Iterator<Line3D> lines = model.getLines();
        while(lines.hasNext()) {
            final Line3D line = lines.next();
            final Point3D start = line.start;
            final Point3D end = line.end;
            glVertex3d(start.x, start.y, start.z);
            glVertex3d(end.x, end.y, end.z);
        }

        glColor3d(1, 0, 0);
        lines = model.getLines();
        while(lines.hasNext()) {
            final Line3D line = lines.next();
            final Point3D start = line.start;
            final Point3D end = line.end;
            glVertex3d(start.x + 15, start.y, start.z);
            glVertex3d(end.x + 15, end.y, end.z);
        }
        glEnd();
    }
}
