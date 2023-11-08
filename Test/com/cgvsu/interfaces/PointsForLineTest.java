package com.cgvsu.interfaces;

import com.cgvsu.Line.Point;
import com.cgvsu.algorithms.WuLineAlgorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointsForLineTest {

    Point[] testPoints;

    @Test
    public void countPoint() {
        WuLineAlgorithm wuLineAlgorithm = new WuLineAlgorithm();
        List<Point> targets = wuLineAlgorithm.countPoint(0, 5, 0, 5);

        testPoints = new Point[] {
                new Point(0, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(1, 2),
                new Point(2, 2),
                new Point(2, 3),
                new Point(3, 3),
                new Point(3, 4),
                new Point(4, 4),
                new Point(4, 5),
                new Point(5, 5),
                new Point(5, 6),
        };

        int index = 0;
        for (Point point : targets) {
            Assertions.assertEquals(point.getX(), testPoints[index].getX());
            Assertions.assertEquals(point.getY(), testPoints[index].getY());
            index++;
        }
    }
}