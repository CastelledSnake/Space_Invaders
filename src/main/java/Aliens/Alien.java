package Aliens;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Alien extends Polygon {
    public Alien (double x, double y) {
        this.getPoints().addAll(0.0d,0.0d,50.0d,0.0d,50.0d,50.0d,
                100.0d, 50.0d,100.0d,0.0d,150.0d,0.0d,150.0d,100.0d,0.0d,100.0d); //100.0d means that it's counted
        // as a decimal
        this.setFill(Color.LIMEGREEN);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
