package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

import java.util.Random;

public class Alien {

    public static double[] formalien = {0.0d, 0.0d, 40.0d, 0.0d, 40.0d, 20.0d, 0.0d, 20.0d};
    Color color = Color.LIMEGREEN;
    int vie = 1;
    private Polygon representation = new Polygon();

    public static final String AlienURL = "file:src\\main\\resources\\Image_alien\\Image_alien.png";
    public static final String AlienURL_r = "file:src\\main\\resources\\Image_alien\\Image_alien_r.png";

    public static final String Tir="file:src\\main\\resources\\Image_tir\\Image_tir_";
    public static final String Tir_down="_d.png";

    public Alien (double x, double y, String ImageURL) {
        this.representation = representation;
        for (double point : formalien) {
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

    public Alien (double x, double y, String ImageURL, int vie) {
        this.representation = representation;
        for (double point : formalien) {
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

    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        return (int) ((r.nextDouble() * (max - min)) + min);
    }
    public static int[] depalien(Group aliens, int pos_gr_alien, int deplacement, String direction, int difficulté) {
        int n_alien = aliens.getChildren().size();
        if ((pos_gr_alien < 0 && deplacement == -1) || (pos_gr_alien > 800 && deplacement == 1)) {
            deplacement = -deplacement;
            if (direction.equals("DOWN")) {
                for (int i = 0; i < n_alien; i++) {
                    aliens.getChildren().get(i).setLayoutY(aliens.getChildren().get(i).getLayoutY() + 10d);
                }
            } else if (direction.equals("UP")) {
                for (int i = 0; i < n_alien; i++) {
                    aliens.getChildren().get(i).setLayoutY(aliens.getChildren().get(i).getLayoutY() - 10d);
                }
            }
        } else if (deplacement == 1) {
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() + 2d + difficulté/5);
            }
            pos_gr_alien=pos_gr_alien+2+difficulté/5;
        } else if (deplacement == -1) {
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() - 2d -difficulté/5);
            }
            pos_gr_alien=pos_gr_alien-2-difficulté/5;
        }
        int ret[]=new int[2];
        ret[0]=pos_gr_alien;
        ret[1]=deplacement;
        return(ret);
    }

    public static void tir_alien(Group aliens, Group tirs_aliens, String URL, int proba) {
        if (proba>0) {
            if (getRandomNumber(0, proba) == 0) {
                int a = getRandomNumber(0, aliens.getChildren().size());
                Tir tira = new Tir(aliens.getChildren().get(a).getLayoutX(), aliens.getChildren().get(a).getLayoutY(), Color.RED, URL);
                tirs_aliens.getChildren().add(tira.getRepresentation());
            }
        }
    }

    public static boolean test_fin_alien(Group aliens,int limite,String direction) {
        int n=aliens.getChildren().size();
        if (n==0) return(false);
        double min=aliens.getChildren().get(0).getLayoutY();
        double max=aliens.getChildren().get(0).getLayoutY();
        for (int i=0; i<n;i++) {
            if (aliens.getChildren().get(i).getLayoutY()>min) min=aliens.getChildren().get(i).getLayoutY();
            if (aliens.getChildren().get(i).getLayoutY()<max) max=aliens.getChildren().get(i).getLayoutY();
        }
        if ((direction.equals("DOWN") && max > limite)||(direction.equals("UP")&& min < limite)) {
            return (true);
        }
        return(false);
    }
}
