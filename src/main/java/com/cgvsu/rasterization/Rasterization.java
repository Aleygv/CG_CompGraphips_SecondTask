package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {

    public static void drawRectangle(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color)
    {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        for (int row = y; row < y + height; ++row)
            for (int col = x; col < x + width; ++col)
                pixelWriter.setColor(col, row, color);
    }

    public static void draw(
            GraphicsContext gc,
            double x1, double y1,
            double x2, double y2,
            Color c1, Color c2)
    {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double gradient = 0;
        if (dx != 0) {
            gradient = dy / dx;
        }

        double xend = Math.round(x1);
        double yend = y1 + gradient * (xend - x1);
        double xgap = 1 - (x1 + 0.5 - Math.floor(x1 + 0.5));
        int xpxl1 = (int) xend;
        int ypxl1 = (int) yend;
        double r1 = c1.getRed();
        double g1 = c1.getGreen();
        double b1 = c1.getBlue();
        double a1 = c1.getOpacity();

        int alpha = (int) (a1 * xgap * 255);
        int red = (int) (r1 * xgap * 255);
        int green = (int) (g1 * xgap * 255);
        int blue = (int) (b1 * xgap * 255);
        gc.setFill(Color.rgb(red, green, blue, alpha));
        gc.fillRect(xpxl1, ypxl1, 1, 1);

        xend = Math.round(x2);
        yend = y2 + gradient * (xend - x2);
        xgap = x2 + 0.5 - Math.floor(x2 + 0.5);
        int xpxl2 = (int) xend;
        int ypxl2 = (int) yend;
        double r2 = c2.getRed();
        double g2 = c2.getGreen();
        double b2 = c2.getBlue();
        double a2 = c2.getOpacity();

        alpha = (int) (a2 * xgap * 255);
        red = (int) (r2 * xgap * 255);
        green = (int) (g2 * xgap * 255);
        blue = (int) (b2 * xgap * 255);
        gc.setFill(Color.rgb(red, green, blue, alpha));
        gc.fillRect(xpxl2, ypxl2, 1, 1);

        for (int x = xpxl1 + 1; x < xpxl2; x++) {
            xgap = 1 - (x + 0.5 - Math.floor(x + 0.5));
            alpha = (int) ((a1 * xgap + a2 * (1 - xgap)) * 255);
            red = (int) ((r1 * xgap + r2 * (1 - xgap)) * 255);
            green = (int) ((g1 * xgap + g2 * (1 - xgap)) * 255);
            blue = (int) ((b1 * xgap + b2 * (1 - xgap)) * 255);
            gc.setFill(Color.rgb(red, green, blue, alpha));
            int y = (int) ((yend - ypxl1) * ((double) (x - xpxl1) / (double) (xpxl2 - xpxl1)) + ypxl1);
            gc.fillRect(x, y, 1, 1);
            gc.setFill(Color.rgb(red, green, blue, (int) ((1 - xgap) * a1 * 255)));
            gc.fillRect(x, y + 1, 1, 1);
        }
    }
}
