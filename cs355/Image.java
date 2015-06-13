package cs355;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by eric on 6/9/15.
 */
public class Image {
    private int height;
    private int width;
    private int[][] values;

    public Image(BufferedImage image) {
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.values = new int[width][height];

        final WritableRaster raster = image.getRaster();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                this.values[i][j] = raster.getSample(i, j, 0);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getValues() {
        return values;
    }

    public void setValues(int[][] values) {
        this.values = values;
    }

    public int get(int row, int col) {
        if(row < 0 || row > values.length - 1 ||
                col < 0 || col > values[0].length - 1) {
            return 0;
        }
        return values[row][col];
    }

    public void changeBrightness(int brightnessAmountNum) {
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int result = values[i][j] + brightnessAmountNum;
                values[i][j] = normalizeValue(result, 0, 255);
            }
        }
    }

    public void changeContrast(int contrastAmountNum) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                final double value = ((double) (values[i][j] - 128)) * Math.pow((contrastAmountNum + 100) / 100.0, 4) + 128;
                values[i][j] = normalizeValue(value, 0, 255);
            }
        }
    }

    public void blurUniform() {
        double oneNinth = 1 / 9.0;
        double[][] mask = {
                {oneNinth, oneNinth, oneNinth},
                {oneNinth, oneNinth, oneNinth},
                {oneNinth, oneNinth, oneNinth}
        };
        this.values = applyMask(mask, false);
    }

    public void blurMedian() {
        int[][] newValues = new int[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                newValues[i][j] = calculateMedian(i, j);
            }
        }
        this.values = newValues;
    }

    public void sharpen() {
        double[][] mask = {
                {0, -1 / 2.0, 0},
                {-1 / 2.0, 6 / 2.0, -1 / 2.0},
                {0, -1 / 2.0, 0}
        };
        this.values = applyMask(mask, false);
    }

    public void detectEdges() {
        int[][] xValues = calculateXDerivative();
        int[][] yValues = calculateYDerivative();

        int[][] newValues = new int[width][height];
        for(int i = 0; i < xValues.length; i++) {
            for(int j = 0; j < xValues[i].length; j++) {
                newValues[i][j] = normalizeValue(Math.round(Math.sqrt(Math.pow(xValues[i][j] / 8.0, 2) + Math.pow(yValues[i][j] / 8.0, 2))), 0, 255);
            }
        }
        this.values = newValues;
    }

    private int[][] calculateXDerivative() {
        double[][] xMask = {
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };
        return applyMask(xMask, true);
    }

    private int[][] calculateYDerivative() {
        double[][] yMask = {
                {-1, -2, -1},
                {0, 0, 0},
                {1, 2, 1}
        };
        return applyMask(yMask, true);
    }

    private int normalizeValue(double value, int min, int max) {
        return (int) Math.max(min, Math.min(max, value));
    }

    private int calculateMedian(int row, int col) {
        List<Integer> neighbors = new ArrayList<>();
        neighbors.add(get(row - 1, col - 1));
        neighbors.add(get(row - 1, col));
        neighbors.add(get(row - 1, col + 1));
        neighbors.add(get(row, col - 1));
        neighbors.add(get(row, col));
        neighbors.add(get(row, col + 1));
        neighbors.add(get(row + 1, col - 1));
        neighbors.add(get(row + 1, col));
        neighbors.add(get(row + 1, col + 1));
        Collections.sort(neighbors);
        return neighbors.get(4);
    }

    public int[][] applyMask(double[][] mask, boolean useNegatives) {
        int[][] newValues = new int[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                //Apply the mask
                if(useNegatives) {
                    newValues[i][j] = calculateAmountAfterMask(i, j, mask);
                } else {
                    newValues[i][j] = normalizeValue(calculateAmountAfterMask(i, j, mask), 0, 255);
                }
            }
        }
        return newValues;
    }

    private int calculateAmountAfterMask(int row, int col, double[][] mask) {
        double newValue = 0;

        for(int i = 0; i < mask.length; i++) {
            for(int j = 0; j < mask[i].length; j++) {
                newValue += (double) get(row - 1 + i, col - 1 + j) * mask[i][j];
            }
        }
        return (int) Math.round(newValue);
    }
}
