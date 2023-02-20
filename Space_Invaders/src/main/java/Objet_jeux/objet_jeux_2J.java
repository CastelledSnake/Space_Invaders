package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Classe Objet spécifiques au jeu 2 Joueurs
 */
public class objet_jeux_2J extends objet_jeux {

    /**
     * Hérite directement de Objet
     * @param x
     * @param y
     * @param forme
     * @param color
     * @param ImageURL
     * @param vie
     */
    public objet_jeux_2J(double x, double y, double[] forme, Color color, String ImageURL, int vie) {
        super(x, y,forme,color,ImageURL, vie);
    }

    /**
     * Initialise les joueur dans le cas du jeu 2 Joueurs
     * @param side donne le côté de l'écran du joueur à initialiser
     * @param vie nombre de vies du joueur à initialiser
     * @param URL adresse relative de l'image du vaisseau
     * @return le nouveau joueur
     */
    public static objet_jeux init_Player(String side, int vie, String URL) {
        if (side.equals("DOWN")) {
            return (new objet_jeux_2J(600, 620, formecanon, Color.LIMEGREEN, URL, vie));
        } else {
            return (new objet_jeux_2J(600, 15, formecanon, Color.LIMEGREEN, URL, vie));
        }
    }

    /**
     * Initialise 1 des 2 groupes d'aliens du jeu 2 Joueurs
     * @param aliens Groupe d'aliens à remplir
     * @param side Côté de l'écran des aliens
     * @param URL adresse relative de l'image du vaisseau
     */
    public static void init_aliens(Group aliens,String side, String URL) {
        double a;
        double b;
        if (side.equals("DOWN")) {
           a=355;
           b=35;
        }
        else {
            a=325;
            b=-35;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                objet_jeux alien = new objet_jeux(10d + 50 * i, a + b * j, formalien, Color.LIMEGREEN, URL, 1);
                aliens.getChildren().add(alien.getRepresentation());
            }
        }
    }

    /**
     * Initialise les blocks avec les positions du jeu 1 Joueur
     * @param blocks Groupe de blocks à remplir
     * @param vie_blocks Group de Text affichant les vies de ces blocks
     */
    public static void init_blocks(Group blocks, Group vie_blocks) {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                objet_jeux block1 = new objet_jeux(300 * i + 110d, 90+510*j, formebloc, Color.LIMEGREEN, "NULL", 10);
                blocks.getChildren().add(block1.getRepresentation());


                Text v1 = new Text(block1.getRepresentation().getAccessibleText());
                v1.setFill(Color.WHITE);
                vie_blocks.getChildren().add(v1);
                v1.setX(block1.getRepresentation().getLayoutX());
                v1.setY(block1.getRepresentation().getLayoutY());

            }
        }
    }

    /**
     * Déplace les deux joueurs du jeu 2 Joueurs
     * @param Player1 Premier joueur
     * @param Player2 Deuxième joueur
     * @param dep1 sens de déplacement du premier joueur
     * @param dep2 sens de déplacement du deuxième joueur
     * @param difficulté indice de difficulté : plus haut -> joueurs se déplacent plus vite
     */
    public static void dep_2_joueurs(objet_jeux Player1, objet_jeux Player2, int dep1, int dep2, int difficulté) {
        if (((dep1==1)&&(Player1.getRepresentation().getLayoutX()<1140))||(dep1==-1&&Player1.getRepresentation().getLayoutX()>0)) {
            Player1.getRepresentation().setLayoutX(Player1.getRepresentation().getLayoutX() + dep1 * (2d+difficulté/5));
        }
        if (((dep2==1)&&(Player2.getRepresentation().getLayoutX()<1140))||((dep2==-1)&&(Player2.getRepresentation().getLayoutX()>0))) {
            Player2.getRepresentation().setLayoutX(Player2.getRepresentation().getLayoutX() + dep2 * (2d+difficulté/5));
        }
    }


}
