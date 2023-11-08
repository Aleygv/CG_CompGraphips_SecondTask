package com.cgvsu.algorithms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class WuLine {
    public static void draw(GraphicsContext gc, int x1, int y1, int x2, int y2, Color c1, Color c2) {
        PixelWriter pw = gc.getPixelWriter();
        // определите направление рисования
        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);
        // если линия более крутая, чем широкая, поменяйте местами координаты x и y
        if (steep) {
            int temp = x1;
            x1 = y1;
            y1 = temp;
            temp = x2;
            x2 = y2;
            y2 = temp;
        }
        // если линия идет справа налево, поменяйте местами начальную и конечную точки
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
            Color tempColor = c1;
            c1 = c2;
            c2 = tempColor;
        }
        // определите разницу между координатами x и y
        double dx = x2 - x1;
        double dy = y2 - y1;
        // определите угол наклона линии
        double gradient = dy / dx;
        if (dx == 0.0) {
            gradient = 1.0;
        }
        // нарисуйте начальную точку
        // и нарисуйте конечную точку
        int xpxl1 = x1;
        int xpxl2 = x2;
        // нарисуйте остальные точки
        double intersectY = y1;
        for (int x = xpxl1; x <= xpxl2; x++) {
            double frac = intersectY % 1;
            double alpha = 1 - frac;
            double beta = frac;
            Color color = c1.interpolate(c2, beta);
            if (steep) {
                pw.setColor((int) intersectY, x, color.deriveColor(0, 1, 1, alpha));
                pw.setColor((int) intersectY + 1, x, color.deriveColor(0, 1, 1, beta));
            } else {
                pw.setColor(x, (int) intersectY, color.deriveColor(0, 1, 1, alpha));
                pw.setColor(x, (int) intersectY + 1, color.deriveColor(0, 1, 1, beta));
            }
            intersectY += gradient;
        }
    }
}
