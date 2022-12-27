package Objet;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Objet_2J extends Objet{

    public Objet_2J(double x, double y, double[] forme, Color color, String ImageURL, int vie) {
        super(x, y,forme,color,ImageURL, vie);
    }
    public static Objet init_Player(String side) {
        if (side.equals("DOWN")) {
            return (new Objet_2J(600, 620, formecanon, Color.LIMEGREEN, VaisseauURL, 2));
        } else {
            return (new Objet_2J(600, 15, formecanon, Color.LIMEGREEN, VaisseauURL_r, 2));
        }
    }

    public static void init_aliens(Group aliens,String side) {
        double a;
        double b;
        String url;
        if (side.equals("DOWN")) {
           a=355;
           b=35;
           url=AlienURL;
        }
        else {
            a=325;
            b=-35;
            url=AlienURL_r;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                Objet alien = new Objet(10d + 50 * i, a + b * j, formalien, Color.LIMEGREEN, url, 1);
                aliens.getChildren().add(alien);
            }
        }
    }

    public static void init_blocks(Group blocks, Group vie_blocks) {
        for (int i = 0; i < 4; i++) {
            Objet block1 = new Objet(300 * i + 110d, 90, formebloc, Color.LIMEGREEN, "NULL", 10);
            blocks.getChildren().add(block1);
            Objet block2 = new Objet(300 * i + 110d, 600, formebloc, Color.LIMEGREEN, "NULL", 10);
            blocks.getChildren().add(block2);

            Text v1 = new Text(block1.getAccessibleText());
            v1.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v1);
            v1.setX(block1.getLayoutX());
            v1.setY(block1.getLayoutY());

            Text v2 = new Text(block2.getAccessibleText());
            v2.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v2);
            v2.setX(block1.getLayoutX());
            v2.setY(block1.getLayoutY());
        }
    }

    public static void dep_2_joueurs(Objet Player1, Objet Player2, int dep1, int dep2) {
        if (((dep1==1)&&(Player1.getLayoutX()<1140))||(dep1==-1&&Player1.getLayoutX()>0)) {
            Player1.setLayoutX(Player1.getLayoutX() + dep1 * 2d);
        }
        if (((dep2==1)&&(Player2.getLayoutX()<1140))||((dep2==-1)&&(Player2.getLayoutX()>0))) {
            Player2.setLayoutX(Player2.getLayoutX() + dep2 * 2d);
        }
    }


}
