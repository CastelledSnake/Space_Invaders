package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public class Tir {

    static double[] formetir = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
    int vie = 1;
    private Polygon representation = new Polygon();

    public Tir(double x, double y, Color color, String ImageURL) {
        this.representation = representation;
        for (double point : formetir) {
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

    public static void dep(Group tirs, String direction, int difficulte) {
        int n = tirs.getChildren().size();
        if (direction.equals("UP")) {
            for (int i = 0; i < n; i++) {
                tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY() - 2d - difficulte/2);
            }
        } else if (direction.equals("DOWN")) {
            for (int i = 0; i < n; i++) {
                tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY() + 2d + difficulte/2);
            }

        }
    }

    public static Boolean memeposition(javafx.scene.Node g1, javafx.scene.Node g2, int xmin, int xmax, int ymin, int ymax) {
        double x1 = g1.getLayoutX();
        double y1 = g1.getLayoutY();
        double x2 = g2.getLayoutX();
        double y2 = g2.getLayoutY();
        if (x1 - x2 > xmin && x1 - x2 < xmax && y1 - y2 > ymin && y1 - y2 < ymax) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    public static void Collision(Group g1, Group g2, int xmin, int xmax, int ymin, int ymax) {
        int na = g1.getChildren().size();
        int nt = g2.getChildren().size();

        for (int a = 0; a < na; a++) {
            for (int t = 0; t < nt; t++) {
                if (memeposition(g1.getChildren().get(a), g2.getChildren().get(t), xmin, xmax, ymin, ymax)) {

                    String vie1 = g1.getChildren().get(a).getAccessibleText();
                    String vie2 = g2.getChildren().get(t).getAccessibleText();

                    int nvie1 = Integer.parseInt(vie1) - 1;
                    int nvie2 = Integer.parseInt(vie2) - 1;

                    g1.getChildren().get(a).setAccessibleText(Integer.toString(nvie1));
                    g2.getChildren().get(t).setAccessibleText(Integer.toString(nvie2));

                }
            }
        }
    }

    public static void Collision_joueur(Canon Player, Group tir, int xmin, int xmax, int ymin, int ymax) {
        int n=tir.getChildren().size();

        for (int i=0; i<n; i++) {
            if (memeposition(Player.getRepresentation(),tir.getChildren().get(i),xmin,xmax,ymin,ymax)) {
                String vie_joueur = Player.getRepresentation().getAccessibleText();
                String vie_tir = tir.getChildren().get(i).getAccessibleText();

                int int_j = Integer.parseInt(vie_joueur) - 1;
                int int_t = Integer.parseInt(vie_tir) - 1;

                Player.getRepresentation().setAccessibleText(Integer.toString(int_j));
                tir.getChildren().get(i).setAccessibleText(Integer.toString(int_t));
            }
        }
    }

    public static void supp(Group g) {
        ArrayList<Node> a_supp = new ArrayList<>();

        for (javafx.scene.Node elem : g.getChildren()) {
            if (Integer.valueOf(elem.getAccessibleText())<=0) a_supp.add(elem);
        }

        for (javafx.scene.Node elem : a_supp) g.getChildren().remove(elem);
    }

    public Polygon getRepresentation() {
        return representation;
    }
}
