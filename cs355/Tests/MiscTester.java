package cs355.Tests;

import cs355.Image;
import cs355.Matrix4D;
import cs355.Vector4D;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by eric on 5/6/15.
 */
public class MiscTester {
    @Test
    public void imageTransformTest() {
        final BufferedImage bufferedImage = new BufferedImage(2, 2, BufferedImage.TYPE_BYTE_GRAY);
        bufferedImage.getRaster().setSample(0, 0, 0, 255);
        bufferedImage.getRaster().setSample(0, 1, 0, 127);
        bufferedImage.getRaster().setSample(1, 0, 0, 63);
        bufferedImage.getRaster().setSample(1, 1, 0, 0);
        final Image image = new Image(bufferedImage);
        image.changeBrightness(10);
        assertTrue(image.get(0, 0) == 255);
        assertTrue(image.get(0, 1) == 137);
        assertTrue(image.get(1, 0) == 73);
        assertTrue(image.get(1, 1) == 10);

        image.changeBrightness(-15);
        assertTrue(image.get(0, 0) == 240);
        assertTrue(image.get(0, 1) == 122);
        assertTrue(image.get(1, 0) == 58);
        assertTrue(image.get(1, 1) == 0);

        image.changeContrast(100);
        assertTrue(image.get(0, 0) == 255);
        assertTrue(image.get(0, 1) == 244);
        assertTrue(image.get(1, 0) == 116);
        assertTrue(image.get(1, 1) == 0);
    }

    @Test
    public void imageMaskingTest() {
        final BufferedImage bufferedImage = new BufferedImage(3, 3, BufferedImage.TYPE_BYTE_GRAY);
        bufferedImage.getRaster().setSample(0, 0, 0, 2);
        bufferedImage.getRaster().setSample(0, 1, 0, 6);
        bufferedImage.getRaster().setSample(0, 2, 0, 5);
        bufferedImage.getRaster().setSample(1, 0, 0, 1);
        bufferedImage.getRaster().setSample(1, 1, 0, 4);
        bufferedImage.getRaster().setSample(1, 2, 0, 4);
        bufferedImage.getRaster().setSample(2, 0, 0, 7);
        bufferedImage.getRaster().setSample(2, 1, 0, 9);
        bufferedImage.getRaster().setSample(2, 2, 0, 11);
        final Image image = new Image(bufferedImage);

        image.blurUniform();
        assertTrue(image.get(0, 0) == 1);
        assertTrue(image.get(0, 1) == 2);
        assertTrue(image.get(0, 2) == 2);
        assertTrue(image.get(1, 0) == 3);
        assertTrue(image.get(1, 1) == 5);
        assertTrue(image.get(1, 2) == 4);
        assertTrue(image.get(2, 0) == 2);
        assertTrue(image.get(2, 1) == 4);
        assertTrue(image.get(2, 2) == 3);

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
