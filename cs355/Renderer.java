package cs355;

import cs355.LWJGL.HouseModel;
import cs355.LWJGL.Line3D;
import cs355.LWJGL.Point3D;
import cs355.Shapes.Line;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Iterator;

/**
 * Created by eric on 6/4/15.
 */
public class Renderer {
    private HouseModel houseModel = new HouseModel();
    private int NEAR_PLANE = 1;
    private int FAR_PLANE = 100;

    public Renderer() {

    }

    public void render(Graphics2D g2d) {
        final Iterator<Line3D> lines = houseModel.getLines();

        while(lines.hasNext()) {
            Line3D world = lines.next();

            Line3D camera = worldToCamera(world);

            final Vector4D[] clippedPoints = clip(camera);

            final boolean inFrustum = cull(clippedPoints[0], clippedPoints[1]);
            if (inFrustum) {


                Point2D cameraPoint1 = new Point2D.Double(clippedPoints[0].get(0) / clippedPoints[0].get(3),
                        clippedPoints[0].get(1) / clippedPoints[0].get(3));
                Point2D cameraPoint2 = new Point2D.Double(clippedPoints[1].get(0) / clippedPoints[1].get(3),
                        clippedPoints[1].get(1) / clippedPoints[1].get(3));

                final Point2D screenPoint1 = cameraToScreen(cameraPoint1);
                final Point2D screenPoint2 = cameraToScreen(cameraPoint2);

                final Line line = new Line(Color.WHITE, screenPoint1, screenPoint2);
                drawLine(line, g2d);
            }
        }
    }

    private Point2D cameraToScreen(Point2D point) {
        final double newX = point.getX() * 2048 / 2 + 2048 / 2;
        final double newY = point.getY() * -2048 / 2 + 2048 / 2;
        return new Point2D.Double(newX, newY);
    }

    private Line3D worldToCamera(Line3D line3D) {
        final Vector4D start = new Vector4D(line3D.start);
        final Vector4D end = new Vector4D(line3D.end);
        final Vector4D cameraLocation = new Vector4D(Helper.cameraLocation);
        final Matrix4D translateMatrix = new Matrix4D(
                1, 0, 0, -cameraLocation.get(0),
                0, 1, 0, -cameraLocation.get(1),
                0, 0, 1, -cameraLocation.get(2),
                0, 0, 0, 1);

        final Matrix4D rotationMatrix = new Matrix4D(
                Math.cos(Helper.rotationAngle), 0, Math.sin(Helper.rotationAngle), 0,
                0, 1, 0, 0,
                -Math.sin(Helper.rotationAngle), 0, Math.cos(Helper.rotationAngle), 0,
                0, 0, 0, 0
        );
        final Point3D translatedStart = start.matrixMultiply(translateMatrix).matrixMultiply(rotationMatrix).getPoint3D();
        final Point3D translatedEnd = end.matrixMultiply(translateMatrix).matrixMultiply(rotationMatrix).getPoint3D();

        return new Line3D(translatedStart, translatedEnd);
    }

    private Vector4D[] clip(Line3D line) {
        //Convert
        final Vector4D start = new Vector4D(line.start);
        final Vector4D end = new Vector4D(line.end);
        final Matrix4D clipMatrix = new Matrix4D(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, (FAR_PLANE + NEAR_PLANE) / (FAR_PLANE - NEAR_PLANE), (-2 * (FAR_PLANE * NEAR_PLANE)) / (FAR_PLANE - NEAR_PLANE),
                0, 0, 1, 0
        );

        final Vector4D clippedStart = start.matrixMultiply(clipMatrix);
        final Vector4D clippedEnd = end.matrixMultiply(clipMatrix);

        return new Vector4D[] {clippedStart, clippedEnd};
    }

    private boolean cull(Vector4D start, Vector4D end) {
        //If either point misses the near plane, FAIL
        if (start.get(2) < -start.get(3) || end.get(2) < -end.get(3)) {
            return false;
        }

        if(start.get(2) > start.get(3) && end.get(2) > end.get(3)) {
            return false;
        }

        //If both endpoints fail the same test, FAIL
        for (int i = 0; i < 3; i++) {
            if (start.get(i) > start.get(3) && end.get(i) > end.get(3)) {
                return false;
            }
            if (start.get(i) < -start.get(3) && end.get(i) < -end.get(3)) {
                return false;
            }
        }

        return true;
    }

    private void drawLine(Line line, Graphics2D g2d) {
        g2d.setColor(line.getColor());
        g2d.setTransform(Helper.worldToView());
        g2d.drawLine((int) line.getPoint1().getX(),
                (int) line.getPoint1().getY(),
                (int) line.getPoint2().getX(),
                (int) line.getPoint2().getY());
    }

}
