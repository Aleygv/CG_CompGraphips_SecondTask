package com.cgvsu.Line;

import com.cgvsu.algorithms.WuLineAlgorithm;
import com.cgvsu.interfaces.Drawble;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import java.util.List;

public class WuLine implements Drawble {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    Color c1;
    Color c2;

    public WuLine(int x1, int x2, int y1, int y2, Color c1, Color c2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.c1 = c1;
        this.c2 = c2;
    }

    public double coefficientForInterpolation(int x, int y) {
        double length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double currentLength = Math.sqrt(Math.pow(x1 - x, 2) + Math.pow(y1 - y, 2));
        return currentLength / length;
    }

    @Override
    public void draw(GraphicsContext gc) {
        PixelWriter pw = gc.getPixelWriter();
        WuLineAlgorithm wuLineAlgorithm = new WuLineAlgorithm();
        List<Point> targets = wuLineAlgorithm.countPoint(x1, x2, y1, y2);
        for (Point p : targets) {
            if (c1 == null && c2 == null) {
                pw.setColor(p.getX(), p.getY(), Color.BLACK);
            }
            else {
                pw.setColor(p.getX(), p.getY(), c1.interpolate(c2, coefficientForInterpolation(p.getX(), p.getY())));
            }
        }
    }
}