package Objet;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Objet_1J extends Objet{
    public Objet_1J(double x, double y, double[] forme, Color color, String ImageURL, int vie) {
        super(x, y,forme,color,ImageURL, vie);
    }

    public static Objet init_Player(int nbvies) {
        return(new Objet_1J(600, 590, formecanon, Color.LIMEGREEN, VaisseauURL, nbvies));
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
            Objet block = new Objet(300 * i + 110d, 550, formebloc, Color.LIMEGREEN, "NULL", 10);
            blocks.getChildren().add(block);
            Text v = new Text(block.getAccessibleText());
            v.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v);
            v.setX(block.getLayoutX());
            v.setY(block.getLayoutY());
        }
    }
}
