package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class Canon {

    static double[] formecanon = {0.0d, 0.0d, 60.0d, 0.0d, 60.0d, 80.0d, 0.0d, 80.0d};
    Color color = Color.LIMEGREEN;
    int vie = 3;
    private Polygon representation = new Polygon();

    public Canon(double x, double y, String ImageURL) {
        this.representation = representation;
        for (double point : formecanon) {
            this.representation.getPoints().add(point);
        }

        if (ImageURL.equals("NULL")) this.representation.setFill(color);
        else {
            Image image = new Image(ImageURL);
            if (image.isError()) this.representation.setFill(color);
            else this.representation.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        }
        this.representation.setLayoutX(x);
        this.representation.setLayoutY(y);
        this.representation.setAccessibleText(Integer.toString(vie));
    }

    public Polygon getRepresentation() {
        return representation;
    }

    public void dep_joueur(int dep1, int difficulte) {
        if (((dep1==1)&&(this.representation.getLayoutX()<1140))||(dep1==-1&&this.representation.getLayoutX()>0)) {
            this.representation.setLayoutX(this.representation.getLayoutX() + dep1 * (2d+difficulte/5));
        }
    }

    public int tir_joueur(int n, int t, Group tirs_joueurs, String URL) {
        if (t == n) {
            Tir tirj = new Tir(this.representation.getLayoutX() + 25d, this.representation.getLayoutY(), Color.GREEN, URL);
            tirs_joueurs.getChildren().add(tirj.getRepresentation());
            t = 0;
        } else t++;
        return(t);
    }
}
