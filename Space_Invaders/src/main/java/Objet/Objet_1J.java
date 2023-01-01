package Objet;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe Objet spécifiques au jeu 1 Joueur
 */
public class Objet_1J extends Objet{
    /**
     * Hérite directement de Objet
     * @param x coordonnées : abcisse
     * @param y coordonnées : ordonnées
     * @param forme polygone affiché si l'image liée à l'objet n'est pas disponible
     * @param color couleur du polygone du cas précédent
     * @param ImageURL adresse relative du fichier image de l'objet
     * @param vie nombre de vies de l'objet
     */
    public Objet_1J(double x, double y, double[] forme, Color color, String ImageURL, int vie) {
        super(x, y,forme,color,ImageURL, vie);
    }

    /**
     * Initialise un joueur dans le cas du jeu 1 Joueur
     * @param nbvies Nombre de vies données au joueur
     * @return le joueur
     */
    public static Objet init_Player(int nbvies) {
        return(new Objet_1J(600, 590, formecanon, Color.LIMEGREEN, VaisseauURL, nbvies));
    }

    /**
     * Remplit d'aliens un groupe vide, avec les positions du jeu 1 Joueur
     * @param aliens Groupe vide dans lequel mettre les aliens
     */
    public static void init_aliens(Group aliens) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                Objet alien = new Objet(10d + 50 * i, 10d + 35 * j, formalien, Color.LIMEGREEN, AlienURL, 1);
                aliens.getChildren().add(alien);
            }
        }
    }

    /**
     * Initialise les blocks avec les positions du jeu 1 Joueur
     * @param blocks Groupe de blocks à remplir
     * @param vie_blocks Group de Text affichant les vies de ces blocks
     */
    public static void init_blocks(Group blocks, Group vie_blocks) {
        for (int i = 0; i < 4; i++) {
            Objet block = new Objet(300 * i + 110d, 550, formebloc, Color.LIMEGREEN, "NULL", 10);
            blocks.getChildren().add(block);
            Text v = new Text(block.getAccessibleText());
            v.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v);
            v.setX(block.getLayoutX());
            v.setY(block.getLayoutY());
        }
    }

    /**
     * Gère les déplacements du Joueur dans le cadre du jeu 1 Joueur
     * @param Player1 Joueur
     * @param dep1 sens de déplacement
     * @param difficulté indice de difficulté : plus haut -> joueur se déplace plus vite
     */
    public static void dep_1_joueur(Objet Player1, int dep1, int difficulté) {
        if (((dep1==1)&&(Player1.getLayoutX()<1140))||(dep1==-1&&Player1.getLayoutX()>0)) {
            Player1.setLayoutX(Player1.getLayoutX() + dep1 * (2d+difficulté/5));
        }
    }
}
