package Objet;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;

public class Objet extends Polygon {


    //forme des différents objets
    static double[] formalien = {0.0d, 0.0d, 40.0d, 0.0d, 40.0d, 20.0d, 0.0d, 20.0d};
    static double[] formecanon = {0.0d, 0.0d, 60.0d, 0.0d, 60.0d, 80.0d, 0.0d, 80.0d};
    static double[] formetir = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
    static double[] formebloc = {0.0d, 0.0d, 80.0d, 0.0d, 80.0d, 20.0d, 0.0d, 20.0d};

    private static final String AlienURL = "file:src\\main\\resources\\Image_alien\\Image_alien.png";
    private static final String VaisseauURL = "file:src\\main\\resources\\Image_vaisseau\\Image_vaisseau.png";


    private static final String Tir="file:src\\main\\resources\\Image_tir\\Image_tir_";
    private static final String Tir_down="_d.png";
    private static final String Tir_up="_u.png";


    //initialise un objet
    public Objet (double x, double y, double[] forme, Color color,String ImageURL, int vie) {
        for (double point : forme) {
            this.getPoints().add(point);
        }
        if (ImageURL.equals("NULL")) this.setFill(color);
        else {
            Image image = new Image(ImageURL);
            this.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        }
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setAccessibleText(Integer.toString(vie));
    }


    //----------------OUTILS MATHEMATIQUES-----------------

    //donne un nombre aléatoire entre min et max
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //renvoie si deux éléments sont en collision
    public static Boolean memeposition(javafx.scene.Node g1, javafx.scene.Node g2, int xmin, int xmax, int ymin, int ymax) {
        double x1 = g1.getLayoutX();
        double y1 = g1.getLayoutY();
        double x2 = g2.getLayoutX();
        double y2 = g2.getLayoutY();
        if (x1 - x2 > xmin && x1 - x2 < xmax && y1 - y2 > ymin && y1 - y2 < ymax) return Boolean.TRUE;
        else return Boolean.FALSE;
    }
    //-----------------INITIALISATION-------------------

    public static Objet init_Player() {
        return(new Objet(600, 490, formecanon, Color.LIMEGREEN, VaisseauURL, 2));
    }

    public static void init_aliens(Group aliens) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                Objet alien = new Objet(10d + 50 * i, 10d + 35 * j, formalien, Color.LIMEGREEN, AlienURL, 1);
                aliens.getChildren().add(alien);
            }
        }
    }

    public static void init_blocks(Group blocks, Group vie_blocks) {
        for (int i = 0; i < 4; i++) {
            Objet block = new Objet(300 * i + 110d, 400, formebloc, Color.LIMEGREEN, "NULL", 10);
            blocks.getChildren().add(block);
            Text v = new Text(block.getAccessibleText());
            v.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v);
            v.setX(block.getLayoutX());
            v.setY(block.getLayoutY());
        }
    }

    //-----------------------TIRS

    //crée un tir du joueur
    public static int tir_joueur(int n,Objet Player1, Group tirs_joueurs, int t, int numtir) {
        if (t == n) {
            String TirJoueurURL=Tir+numtir+Tir_up;
            Objet tirj = new Objet(Player1.getLayoutX() + 25d, Player1.getLayoutY(), formetir, Color.GREEN, TirJoueurURL, 1);
            tirs_joueurs.getChildren().add(tirj);
            t = 0;
        } else t++;
        return(t);
    }

    //crée un tir d'alien
    public static void tir_alien(Group aliens, Group tirs_aliens, int numtir) {
        if (Objet.getRandomNumber(0, 60) == 0) {
            String TirAlienURL=Tir+numtir+Tir_down;
            int a = Objet.getRandomNumber(0, aliens.getChildren().size());
            Objet tira = new Objet(aliens.getChildren().get(a).getLayoutX(), aliens.getChildren().get(a).getLayoutY(), formetir, Color.RED, TirAlienURL, 1);
            tirs_aliens.getChildren().add(tira);
        }
    }



    //-----------------DEPLACEMENTS---------------------

    //gère les déplacements du joueur
    public static void depjoueur(KeyEvent e, Objet Player) {
        if (e.getCode() == KeyCode.LEFT && Player.getLayoutX() > 0) {
            Player.setLayoutX(Player.getLayoutX() - 6d);
        }
        if (e.getCode() == KeyCode.RIGHT && Player.getLayoutX() < 1140) {
            Player.setLayoutX(Player.getLayoutX() + 6d);
        }
    }

    //gère les déplacements coordonnés du groupe d'alien
    public static int[] depalien(Group aliens, int pos_gr_alien, int deplacement) {
        int n_alien = aliens.getChildren().size();
        if ((pos_gr_alien == 0 && deplacement == -1) || (pos_gr_alien == 800 && deplacement == 1)) {
            deplacement = -deplacement;
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutY(aliens.getChildren().get(i).getLayoutY() + 10d);
            }
        } else if (deplacement == 1) {
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() + 1d);
            }
            pos_gr_alien++;
        } else if (deplacement == -1) {
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() - 1d);
            }
            pos_gr_alien--;
        }
        int ret[]=new int[2];
        ret[0]=pos_gr_alien;
        ret[1]=deplacement;
        return(ret);
    }

    //fait descendre les éléments  d'un groupe de tirs
    public static void Tirup(Group tirs) {
        int n = tirs.getChildren().size();
        for (int i = 0; i < n; i++) {
            tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY() - 3d);
        }
    }

    //fait monter les éléments d'un groupe de tirs
    public static void Tirdown(Group tirs) {
        int n = tirs.getChildren().size();
        for (int i = 0; i < n; i++) {
            tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY() + 3d);
        }
    }

    //----------------------GESTION DES VIES

    public static void vie_blocks(Group blocks,Group vie_blocks) {
        //affichage des vies des blocks
        vie_blocks.getChildren().clear();
        for (int i = 0; i < blocks.getChildren().size(); i++) {
            Text v = new Text(blocks.getChildren().get(i).getAccessibleText());
            v.setFill(Color.WHITE);
            v.setX(blocks.getChildren().get(i).getLayoutX());
            v.setY(blocks.getChildren().get(i).getLayoutY());
            vie_blocks.getChildren().add(v);
        }
    }

    //------------------COLLISIONS------------

    //gère les collisions entre deux groupes
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

    //supprime les éléments en collision
    public static void supp(Group g) {
        ArrayList<Node> a_supp = new ArrayList<>();

        for (javafx.scene.Node elem : g.getChildren()) {
            if (elem.getAccessibleText().equals("0")) a_supp.add(elem);
        }

        for (javafx.scene.Node elem : a_supp) g.getChildren().remove(elem);
    }
}

