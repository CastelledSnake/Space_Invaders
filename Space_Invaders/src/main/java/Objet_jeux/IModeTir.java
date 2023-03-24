package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public interface IModeTir {
    public int tir_joueur(int n, int t, Group tirs_joueurs, String URL, Canon canon);
}
