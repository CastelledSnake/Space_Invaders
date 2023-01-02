package Objet;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe Objet spécifiques au jeu 1 Joueur
 */
public class objet_1J extends objet {
    /**
     * Hérite directement de Objet
     * @param x
     * @param y
     * @param forme
     * @param color
     * @param ImageURL
     * @param vie
     */
    public objet_1J(double x, double y, double[] forme, Color color, String ImageURL, int vie) {
        super(x, y,forme,color,ImageURL, vie);
    }

    /**
     * Initialise un joueur dans le cas du jeu 1 Joueur
     * @param nbvies Nombre de vies données au joueur
     * @param URL adresse relative de l'image du vaisseau
     * @return le joueur
     */
    public static objet init_Player(int nbvies, String URL) {
        return(new objet_1J(600, 590, formecanon, Color.LIMEGREEN, URL, nbvies));
    }

    /**
     * Remplit d'aliens un groupe vide, avec les positions du jeu 1 Joueur
     * @param aliens Groupe vide dans lequel mettre les aliens
     * @param URL adresse relative de l'image du tir
     */
    public static void init_aliens(Group aliens, String URL) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                objet alien = new objet(10d + 50 * i, 10d + 35 * j, formalien, Color.LIMEGREEN, URL, 1);
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
            objet block = new objet(300 * i + 110d, 550, formebloc, Color.LIMEGREEN, "NULL", 10);
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
    public static void dep_1_joueur(objet Player1, int dep1, int difficulté) {
        if (((dep1==1)&&(Player1.getLayoutX()<1140))||(dep1==-1&&Player1.getLayoutX()>0)) {
            Player1.setLayoutX(Player1.getLayoutX() + dep1 * (2d+difficulté/5));
        }
    }
}
