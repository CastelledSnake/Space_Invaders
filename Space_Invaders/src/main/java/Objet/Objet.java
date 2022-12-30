package Objet;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class Objet extends Polygon {


    //forme des différents objets
    static double[] formalien = {0.0d, 0.0d, 40.0d, 0.0d, 40.0d, 20.0d, 0.0d, 20.0d};
    static double[] formecanon = {0.0d, 0.0d, 60.0d, 0.0d, 60.0d, 80.0d, 0.0d, 80.0d};
    static double[] formetir = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
    static double[] formebloc = {0.0d, 0.0d, 80.0d, 0.0d, 80.0d, 20.0d, 0.0d, 20.0d};

    public static final String AlienURL = "file:src\\main\\resources\\Image_alien\\Image_alien.png";
    public static final String AlienURL_r = "file:src\\main\\resources\\Image_alien\\Image_alien_r.png";
    public static final String VaisseauURL = "file:src\\main\\resources\\Image_vaisseau\\Image_vaisseau.png";
    public static final String VaisseauURL_r = "file:src\\main\\resources\\Image_vaisseau\\Image_vaisseau_r.png";


    public static final String Tir="file:src\\main\\resources\\Image_tir\\Image_tir_";
    public static final String Tir_down="_d.png";
    public static final String Tir_up="_u.png";


    //initialise un objet
    public Objet (double x, double y, double[] forme, Color color,String ImageURL, int vie) {
        for (double point : forme) {
            this.getPoints().add(point);
        }

        if (ImageURL.equals("NULL")) this.setFill(color);
        else {
            Image image = new Image(ImageURL);
            if (image.isError()) this.setFill(color);
            else this.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        }
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setAccessibleText(Integer.toString(vie));
    }


    //----------------OUTILS MATHEMATIQUES-----------------

    //donne un nombre aléatoire entre min et max
    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        return (int) ((r.nextDouble() * (max - min)) + min);
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

    //voir classes filles (position)

    //-----------------------TIRS-------------------

    //crée un tir du joueur
    public static int tir_joueur(int n,Objet Player1, Group tirs_joueurs, int t, int numtir, String direction) {
        if (t == n) {
            if (direction.equals("UP")) {
                String TirJoueurURL = Tir + numtir + Tir_up;
                Objet tirj = new Objet(Player1.getLayoutX() + 25d, Player1.getLayoutY(), formetir, Color.GREEN, TirJoueurURL, 1);
                tirs_joueurs.getChildren().add(tirj);
            } else if (direction.equals("DOWN")) {
                String TirJoueurURL = Tir + numtir + Tir_down;
                Objet tirj = new Objet(Player1.getLayoutX() + 25d, Player1.getLayoutY(), formetir, Color.GREEN, TirJoueurURL, 1);
                tirs_joueurs.getChildren().add(tirj);
            }
            t = 0;
        } else t++;
        return(t);
    }

    //crée un tir d'alien
    public static void tir_alien(Group aliens, Group tirs_aliens, int numtir, String direction, int proba) {
        if (proba>0) {
            if (Objet.getRandomNumber(0, proba) == 0) {
                if (direction.equals("DOWN")) {
                    String TirAlienURL = Tir + numtir + Tir_down;
                    int a = Objet.getRandomNumber(0, aliens.getChildren().size());
                    Objet tira = new Objet(aliens.getChildren().get(a).getLayoutX(), aliens.getChildren().get(a).getLayoutY(), formetir, Color.RED, TirAlienURL, 1);
                    tirs_aliens.getChildren().add(tira);
                } else if (direction.equals("UP")) {
                    String TirAlienURL = Tir + numtir + Tir_up;
                    int a = Objet.getRandomNumber(0, aliens.getChildren().size());
                    Objet tira = new Objet(aliens.getChildren().get(a).getLayoutX(), aliens.getChildren().get(a).getLayoutY(), formetir, Color.RED, TirAlienURL, 1);
                    tirs_aliens.getChildren().add(tira);
                }
            }
        }
    }



    //-----------------DEPLACEMENTS---------------------

    //gère les déplacements du joueur
    public static void depjoueur(KeyEvent e, Objet Player, int niveau) {
        if ((e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.Q) && Player.getLayoutX() > 0) {
            Player.setLayoutX(Player.getLayoutX() - 6d - niveau);
        }
        if ((e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) && Player.getLayoutX() < 1140) {
            Player.setLayoutX(Player.getLayoutX() + 6d + niveau);
        }
    }

    //gère les déplacements coordonnés du groupe d'alien
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


    public static void Tir(Group tirs, String direction, int niveau) {
        int n = tirs.getChildren().size();
        if (direction.equals("UP")) {
            for (int i = 0; i < n; i++) {
                tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY() - 2d - niveau/2);
            }
        } else if (direction.equals("DOWN")) {
            for (int i = 0; i < n; i++) {
                tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY() + 2d + niveau/2);
            }

        }
    }


    //teste si les aliens sont dans le domaine du joueur -> fin de jeu
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

    public static void Collision_joueur(Objet Player, Group tir,int xmin, int xmax, int ymin, int ymax) {
        int n=tir.getChildren().size();

        for (int i=0; i<n; i++) {
            if (memeposition(Player,tir.getChildren().get(i),xmin,xmax,ymin,ymax)) {
                String vie_joueur = Player.getAccessibleText();
                String vie_tir = tir.getChildren().get(i).getAccessibleText();

                int int_j = Integer.parseInt(vie_joueur) - 1;
                int int_t = Integer.parseInt(vie_tir) - 1;

                Player.setAccessibleText(Integer.toString(int_j));
                tir.getChildren().get(i).setAccessibleText(Integer.toString(int_t));
            }
        }
    }

    //supprime les éléments en collision
    public static void supp(Group g) {
        ArrayList<Node> a_supp = new ArrayList<>();

        for (javafx.scene.Node elem : g.getChildren()) {
            if (Integer.valueOf(elem.getAccessibleText())<=0) a_supp.add(elem);
        }

        for (javafx.scene.Node elem : a_supp) g.getChildren().remove(elem);
    }
}

