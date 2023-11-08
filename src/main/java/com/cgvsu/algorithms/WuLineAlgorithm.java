package com.cgvsu.algorithms;

import com.cgvsu.Line.Point;
import com.cgvsu.interfaces.PointsForLine;

import java.util.ArrayList;
import java.util.List;

public class WuLineAlgorithm implements PointsForLine {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    @Override
    public List<Point> countPoint(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        return solution();
    }

    private List<Point> solution() {
        List<Point> points = new ArrayList<>();
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
            if (steep) {
                points.add(new Point((int) intersectY, x));
                points.add(new Point((int) intersectY + 1, x));
            } else {
                points.add(new Point(x, (int) intersectY));
                points.add(new Point(x, (int) intersectY + 1));
            }
            intersectY += gradient;
        }
        return points;
    }
}