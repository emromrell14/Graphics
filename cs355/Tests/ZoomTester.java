package cs355.Tests;

import cs355.CS355Frame;
import cs355.Controller.ControllerImpl;
import cs355.Helper;
import cs355.Model.ModelImpl;
import cs355.View.ViewImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by eric on 5/14/15.
 */
public class ZoomTester {

    ViewImpl view = new ViewImpl();
    ModelImpl model = new ModelImpl(view);
    MyController controller = new MyController(model);


    @Before
    public void setup() {
        view.setModel(model);
        CS355Frame.createCS355Frame(controller, view, controller, controller);
    }

    @Test
    public void testZoomIn() {
        controller.hScrollbarChanged(0);
        controller.vScrollbarChanged(0);
        Helper.screenScale = 4;
        controller.zoomInButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 512);
        assertTrue(Helper.topLeftOffset.get(1) == 512);

        controller.zoomInButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 768);
        assertTrue(Helper.topLeftOffset.get(1) == 768);

        controller.zoomInButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 896);
        assertTrue(Helper.topLeftOffset.get(1) == 896);

        controller.zoomInButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 960);
        assertTrue(Helper.topLeftOffset.get(1) == 960);

        controller.zoomInButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 960);
        assertTrue(Helper.topLeftOffset.get(1) == 960);
    }

    @Test
    public void testZoomOut() {
        controller.hScrollbarChanged(960);
        controller.vScrollbarChanged(960);
        Helper.screenScale = .25;
        controller.zoomOutButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 896);
        assertTrue(Helper.topLeftOffset.get(1) == 896);

        controller.zoomOutButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 768);
        assertTrue(Helper.topLeftOffset.get(1) == 768);

        controller.zoomOutButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 512);
        assertTrue(Helper.topLeftOffset.get(1) == 512);

        controller.zoomOutButtonHit();
        assertTrue(Helper.topLeftOffset.get(0) == 0);
        assertTrue(Helper.topLeftOffset.get(1) == 0);

    }

    private class MyController extends ControllerImpl {

        public MyController(ModelImpl model) {
            super(model);
        }
    }
}
