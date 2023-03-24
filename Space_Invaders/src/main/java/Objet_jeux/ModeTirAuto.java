package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class ModeTirAuto implements IModeTir {

    public int tir_joueur(int n, int t, Group tirs_joueurs, String URL, Canon canon) {
        if (t == n) {
            Tir tirj = new Tir(canon.getRepresentation().getLayoutX() + 25d, canon.getRepresentation().getLayoutY(),
                    Color.GREEN, URL);
            tirs_joueurs.getChildren().add(tirj.getRepresentation());
            t = 0;
        } else t++;
        return(t);
    }
}
