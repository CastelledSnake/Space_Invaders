package Objet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.Collections;

public class Objet extends Polygon {

    public Objet (double x, double y, double[] forme, Color color,String ImageURL) {
        for (double point : forme) {
            this.getPoints().add(point);
        }
        if (ImageURL=="NULL") this.setFill(color);
        else {
            Image image = new Image(ImageURL);
            this.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        }
        this.setLayoutX(x);
        this.setLayoutY(y);

    }


}

