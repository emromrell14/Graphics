package cs355;

import java.awt.geom.Point2D;

/**
 * Created by eric on 5/7/15.
 */
public class Vector2D {
    private double[] vector = new double[2];

    public Vector2D(double[] vector) {
        this.vector = vector;
    }

    public Vector2D(double value1, double value2) {
        this.vector[0] = value1;
        this.vector[1] = value2;
    }

    public Vector2D(Point2D point) {
        this.vector[0] = point.getX();
        this.vector[1] = point.getY();
    }

    public Vector2D(Point2D point1, Point2D point2) {
        this.vector[0] = point1.getX() - point2.getX();
        this.vector[1] = point1.getY() - point2.getY();
    }

    public double[] getVector() {
        return vector;
    }

    public double get(int i) {
        return vector[i];
    }

    public void set(int index, double value) {
        vector[index] = value;
    }

    public int size() {
        return vector.length;
    }

    public Vector2D getPerpendicular() {
        return new Vector2D(-this.vector[1], this.vector[0]);
    }

    public double dot(Vector2D other) {
        double result = 0;
        for(int i = 0; i < this.size(); i++) {
            result += vector[i] * other.get(i);
        }
        return result;
    }

    public Point2D getPoint() {
        return new Point2D.Double(vector[0], vector[1]);
    }

    @Override
    public String toString() {
        return "[" + this.vector[0] + ", " + this.vector[1] + "]";
    }
}
