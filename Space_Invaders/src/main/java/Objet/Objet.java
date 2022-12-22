package Objet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Collections;

public class Objet extends Polygon {
    public Objet (double x, double y, double[] forme, Color color) {
        for (double point : forme) {
            this.getPoints().add(point);
        }
        this.setFill(color);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }


}

