package cs355.LWJGL;

/**
 * @author Brennan Smith
 */
public class Point3D {
    public double x;
    public double y;
    public double z;

    public Point3D(double newX, double newY, double newZ) {
        x = newX;
        y = newY;
        z = newZ;
    }

    public Point3D(Point3D otherPoint) {
        x = otherPoint.x;
        y = otherPoint.y;
        z = otherPoint.z;
    }

    double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y + ", Z:" + z;
    }

    void Normalize() {
        Double denominator = Math.sqrt(x * x + y * y + z * z);
        x /= denominator;
        y /= denominator;
        z /= denominator;
    }


}
