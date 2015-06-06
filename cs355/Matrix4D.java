package cs355;

/**
 * Created by eric on 6/4/15.
 */
public class Matrix4D {
    //This is [ROW][COLUMN] format
    double[][] matrix = new double[4][4];

    public Matrix4D(double aa, double ab, double ac, double ad, double ba, double bb, double bc, double bd, double ca, double cb, double cc, double cd, double da, double db, double dc, double dd) {
        matrix[0][0] = aa;
        matrix[0][1] = ab;
        matrix[0][2] = ac;
        matrix[0][3] = ad;
        matrix[1][0] = ba;
        matrix[1][1] = bb;
        matrix[1][2] = bc;
        matrix[1][3] = bd;
        matrix[2][0] = ca;
        matrix[2][1] = cb;
        matrix[2][2] = cc;
        matrix[2][3] = cd;
        matrix[3][0] = da;
        matrix[3][1] = db;
        matrix[3][2] = dc;
        matrix[3][3] = dd;
    }

    public double get(int row, int col) {
        return matrix[row][col];
    }
}
