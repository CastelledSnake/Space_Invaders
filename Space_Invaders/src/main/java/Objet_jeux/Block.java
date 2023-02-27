package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class Block {

    static double[] formebloc = {0.0d, 0.0d, 80.0d, 0.0d, 80.0d, 20.0d, 0.0d, 20.0d};
    Color color = Color.LIMEGREEN;
    String ImageURL = "NULL";
    int vie = 10;
    private Polygon representation = new Polygon();

    public Block(double x, double y) {
        this.representation = representation;
        for (double point : formebloc) {
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

    public static void vie_blocks(Group blocks, Group vie_blocks) {
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

    public Polygon getRepresentation() {
        return representation;
    }
}
