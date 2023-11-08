package com.cgvsu.rasterizationfxapp;

import com.cgvsu.Line.WuLine;
import com.cgvsu.interfaces.Drawble;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;


public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        List<Drawble> lines = new ArrayList<>();
        Drawble wuLine1 = new WuLine(100, 300, 200, 400, Color.BLUE, Color.PINK);
        lines.add(wuLine1);
        for (Drawble dr : lines) {
            dr.draw(canvas.getGraphicsContext2D());
        }
    }
}