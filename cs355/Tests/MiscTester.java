package cs355.Tests;

import cs355.Matrix4D;
import cs355.Vector4D;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by eric on 5/6/15.
 */
public class MiscTester {
    @Test
    public void drawingTester() {

    }

    @Test
    public void matrixMultiplyTest() {
        final Vector4D vector4D = new Vector4D(-5, 0, -5, 1);
        final Matrix4D matrix4D = new Matrix4D(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
        Vector4D result = vector4D.matrixMultiply(matrix4D);
        assertNotNull(result);
        assertTrue(vector4D.equals(result));

        final Matrix4D clipMatrix = new Matrix4D(1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 101/99, -200/99,
                0, 0, 1, 0);
        result = vector4D.matrixMultiply(clipMatrix);
        assertNotNull(result);
        assertTrue(result.equals(new Vector4D(-5, 0, -235/33, -5)));
    }
}
