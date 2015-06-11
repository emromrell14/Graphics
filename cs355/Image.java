package cs355;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

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

    public double get(int row, int col) {
        if(row < 0 || row > values.length - 1 ||
                col < 0 || col > values[0].length - 1) {
            return 0;
        }
        return values[row][col];
    }

    public void detectEdges() {

    }

    public void sharpen() {
    }

    public void blurMedian() {

    }

    public void blurUniform() {
        double oneNinth = 1 / 9.0;
        double[][] mask = {
                {oneNinth, oneNinth, oneNinth},
                {oneNinth, oneNinth, oneNinth},
                {oneNinth, oneNinth, oneNinth}
        };
        applyMask(mask);
    }

    public void changeContrast(int contrastAmountNum) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                final double value = ((double) (values[i][j] - 128)) * Math.pow((contrastAmountNum + 100) / 100.0, 4) + 128;
                values[i][j] = normalizeValue(value, 0, 255);
            }
        }
    }

    public void changeBrightness(int brightnessAmountNum) {
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int result = values[i][j] + brightnessAmountNum;
                values[i][j] = normalizeValue(result, 0, 255);
            }
        }
    }

    private int normalizeValue(double value, int min, int max) {
        return (int) Math.max(min, Math.min(max, value));
    }

    private void applyMask(double[][] mask) {
        int[][] newValues = new int[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                //Apply the mask
                newValues[i][j] = calculateAmountAfterMask(i, j, mask);
            }
        }
        this.values = newValues;
    }

    private int calculateAmountAfterMask(int row, int col, double[][] mask) {
        double newValue = 0;

        for(int i = 0; i < mask.length; i++) {
            for(int j = 0; j < mask[0].length; j++) {
                newValue += get(row - 1 + i, col - 1 + j) * mask[i][j];
            }
        }
        return (int) Math.round(newValue);
    }
}
