package cs355.Tests;

import cs355.Shapes.Rectangle;
import cs355.Shapes.Shape;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by eric on 5/6/15.
 */
public class SelectionTester {
    @Before
    public void setup() {

    }

    @Test
    public void test1() {
        Shape rectangle = new Rectangle(null, new Point(0,0), 100, 100);

        assertTrue(rectangle.isClickInShape(new Point(0, 0)));

    }
}
