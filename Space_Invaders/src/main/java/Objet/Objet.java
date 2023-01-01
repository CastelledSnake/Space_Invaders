package Objet;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Répresente un élément (joueur, alien, tir, block)
 */
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

    /**
     * Permet de créer un objet
     * @param x coordonnées : abcisse
     * @param y coordonnées : ordonnées
     * @param forme polygone affiché si l'image liée à l'objet n'est pas disponible
     * @param color couleur du polygone du cas précédent
     * @param ImageURL adresse relative du fichier image de l'objet
     * @param vie nombre de vies de l'objet
     */
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

    /**
     * Donne un entier aléatoire compris entre min et max
     * @param min un nombre entier
     * @param max un nombre entier
     * @return un entier entre min et max
     */
    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        return (int) ((r.nextDouble() * (max - min)) + min);
    }

    /**
     * renvoie si deux éléments sont en collision
     * @param g1 Premier objet (impossible de le récupérer sous le type Objet)
     * @param g2 Deuxième objet (idem)
     * @param xmin marge négative autorisée dans les abcisses
     * @param xmax marge positive autorisée dans les abcisses
     * @param ymin marge négative autorisée dans les ordonnées
     * @param ymax marge négative autorisée dans les ordonnées
     * @return un booléen, true si les éléments sont en collision, false sinon
     */
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

    /**
     * Détermine si le joueur doit tirer : si oui, crée le tir
     * @param n détermine la fréquence de tir du joueur
     * @param t détermine l'état actuel de tir du joueur dans la fréquence n
     * @param Player1 joueur concerné
     * @param tirs_joueurs groupe dans lequel rajouter le nouveau tir
     * @param numtir apparence du tir
     * @param direction détermine si le tir doit se diriger vers le haut ou vers le bas
     * @return la nouvelle valeur de l'état actuel de tir du joueur
     */
    //crée un tir du joueur
    public static int tir_joueur(int n,int t,Objet Player1, Group tirs_joueurs, int numtir, String direction) {
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

    /**
     * //gère les tirs des aliens
     * @param aliens Groupe d'aliens
     * @param tirs_aliens Groupe dans lequel rajouter un éventuel tir
     * @param numtir Apparence du tirs
     * @param direction Direction du tir
     * @param proba Probabilité (1/proba) que les aliens tirent
     */
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


    /**
     * gère les déplacements coordonnés du groupe d'alien
     * @param aliens groupe d'aliens
     * @param pos_gr_alien représente la position du groupe d'aliens
     * @param deplacement détermine si le groupe se déplace de gauche -> droite ou droite -> gauche
     * @param direction détermine si les aliens se dirigent vers le haut ou vers le bas
     * @param difficulté indice de difficulté : plus haut -> aliens se déplacent plus vite
     * @return un array avec la nouvelle valeur de pos_gr_alien et deplacement
     */
    public static int[] depalien(Group aliens, int pos_gr_alien, int deplacement, String direction, int difficulté) {
        int n_alien = aliens.getChildren().size();
        if ((pos_gr_alien < 0 && deplacement == -1) || (pos_gr_alien > 800 && deplacement == 1)) {
            // Si les aliens sont au bord de l'écran, on les fait changer de direction.
            deplacement = -deplacement;
            if (direction.equals("DOWN")) {
                for (int i = 0; i < n_alien; i++) {
                    // Pour cette itération, leur vitesse sera verticale : ils se rapprochent du J1.
                    aliens.getChildren().get(i).setLayoutY(aliens.getChildren().get(i).getLayoutY() + 10d);
                }
            } else if (direction.equals("UP")) {
                for (int i = 0; i < n_alien; i++) {
                    // Idem vers le J2
                    aliens.getChildren().get(i).setLayoutY(aliens.getChildren().get(i).getLayoutY() - 10d);
                }
            }
        } else if (deplacement == 1) {
            // Cas normal : les aliens ne sont pas sur un bord.
            for (int i = 0; i < n_alien; i++) {
                // On les fait se déplacer vers la droite pour cette étape.
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() + 2d + difficulté/5.);
            }
            pos_gr_alien=pos_gr_alien+2+difficulté/5;
        } else if (deplacement == -1) {
            // Idem vers la gauche.
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() - 2d -difficulté/5.);
            }
            pos_gr_alien=pos_gr_alien-2-difficulté/5;
        }
        int ret[]=new int[2];
        ret[0]=pos_gr_alien;
        ret[1]=deplacement;
        return(ret);
    }


    /**
     * Gère le déplacements des tirs
     * @param tirs Groupe des tirs
     * @param direction Direction dans lequel doivent se diriger les tirs
     * @param difficulte indice de difficulté : plus haut -> tirs se déplacent plus vite
     */
    public static void Tir(Group tirs, String direction, int difficulte) {
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


    /**
     * teste si les aliens sont dans le domaine du joueur -> fin de jeu
     * @param aliens groupes d'aliens
     * @param limite valeur maximale autorisée
     * @param direction direction dans lequel ce groupe d'aliens se déplace (haut ou bas)
     * @return un booléan indiquant si les aliens ont dépassé la limite
     */
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

    /**
     * Met à jour l'affichage des vies des blocks
     * @param blocks groupe des blocks
     * @param vie_blocks groupe des Text des vies des blocks
     */
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

    /**
     * Gère les collisions entre deux groupes. Si collision, modifie les vies des Objets concernés
     * @param g1 Premier groupe
     * @param g2 Deuxième groupe
     * @param xmin marge négative autorisée dans les abcisses
     * @param xmax marge positive autorisée dans les abcisses
     * @param ymin marge négative autorisée dans les ordonnées
     * @param ymax marge négative autorisée dans les ordonnées
     */
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

    /**
     * Vérifie les collisions entre un joueur et un groupe. Si collision, modifie les vies des Objets concernés
     * @param Player Joueur
     * @param tir Groupe
     * @param xmin marge négative autorisée dans les abcisses
     * @param xmax marge positive autorisée dans les abcisses
     * @param ymin marge négative autorisée dans les ordonnées
     * @param ymax marge négative autorisée dans les ordonnées
     */
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

    /**
     * Supprime les éléments des groupes n'ayant plus de vie
     * @param g Groupe
     */
    public static void supp(Group g) {
        ArrayList<Node> a_supp = new ArrayList<>();

        for (javafx.scene.Node elem : g.getChildren()) {
            if (Integer.valueOf(elem.getAccessibleText())<=0) a_supp.add(elem);
        }

        for (javafx.scene.Node elem : a_supp) g.getChildren().remove(elem);
    }
}

