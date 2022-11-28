package Cannons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cannon extends Polygon {
    public enum Direction {
        UP,DOWN,LEFT,RIGHT
    }
    Direction dir=Direction.UP;
    public Cannon (double x, double y) {
        this.getPoints().addAll(0.0d,0.0d,20.0d,0.0d,20.0d,20.0d,
                40.0d, 20.0d,40.0d,0.0d,60.0d,0.0d,60.0d,100.0d,0.0d,100.0d);
        this.setFill(Color.LIMEGREEN);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}