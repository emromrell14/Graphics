package cs355;

import cs355.LWJGL.Point3D;

/**
 * Created by eric on 6/4/15.
 */
public class Vector4D {
    private double[] vector = new double[4];

    public Vector4D(double a, double b, double c, double d) {
        vector[0] = a;
        vector[1] = b;
        vector[2] = c;
        vector[3] = d;
    }

    public Vector4D(Point3D point) {
        vector[0] = point.x;
        vector[1] = point.y;
        vector[2] = point.z;
        vector[3] = 1;
    }

    public Vector4D matrixMultiply(Matrix4D matrix) {
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;


        for(int i = 0; i < vector.length; i++) {
            a += matrix.get(0, i) * vector[i];
            b += matrix.get(1, i) * vector[i];
            c += matrix.get(2, i) * vector[i];
            d += matrix.get(3, i) * vector[i];
        }

        return new Vector4D(a, b, c, d);
    }

    public double get(int index) {
        return vector[index];
    }

    public boolean equals(Vector4D other) {
        if(vector[0] == other.vector[0] &&
                vector[1] == other.vector[1] &&
                vector[2] == other.vector[2] &&
                vector[3] == other.vector[3]) {
            return true;
        }
        return false;
    }

    public Point3D getPoint3D() {
        return new Point3D(vector[0], vector[1], vector[2]);
    }
}
