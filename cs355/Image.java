package cs355;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Created by eric on 6/9/15.
 */
public class Image {
    private BufferedImage image;
    private int height;
    private int width;
    private int[][] values;

    public Image(BufferedImage image) {
        this.image = image;
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.values = new int[width][height];

        final WritableRaster raster = image.getRaster();
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                this.values[i][j] = raster.getSample(i, j, 1);
            }
        }
    }

    public BufferedImage getImage() {
        return image;
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

    public int get(int i, int j) {
        return values[i][j];
    }

    public void detectEdges() {

    }

    public void sharpen() {
    }

    public void blurMedian() {
    }

    public void blurUniform() {
    }

    public void changeContrast(int contrastAmountNum) {
    }

    public void changeBrightness(int brightnessAmountNum) {
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int newValue = Math.max(0, Math.min(255, values[i][j] + brightnessAmountNum));
                values[i][j] = newValue;
            }
        }
    }
}
