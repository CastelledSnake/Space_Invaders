package Objet;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.*;
class ObjetTest {

    @Test
    @DisplayName("Renvoie True si les 2 objets considérés ont la même position + ou - un intervalle correspondant à leur taille, False sinon.")
    void memeposition() {
        // Initialisation des variables nécessaires pour créer des objets à faire se rencontrer.
        double[] formalien = {0.0d, 0.0d, 40.0d, 0.0d, 40.0d, 20.0d, 0.0d, 20.0d};
        double[] formetir = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        final String AlienURL = "file:src\\main\\resources\\Image_alien\\Image_alien.png";
        final String Tir="file:src\\main\\resources\\Image_tir\\Image_tir_";
        final String Tir_down="_d.png";
        final String Tir_up="_u.png";
        String TirJoueurURL = Tir + "4" + Tir_up;
        String TirAlienURL = Tir + "2" + Tir_down;



        // Création de quelques aliens et tirs.
        //Bloque à la ligne suivante :
        Objet alien1 = new Objet(10d, 10d, formalien, Color.LIMEGREEN, AlienURL, 1);
        Objet alien2 = new Objet(1000d, 1000d, formalien, Color.LIMEGREEN, AlienURL, 1);
        Objet tirJoueu1 = new Objet(15d, 15d, formetir, Color.GREEN, TirJoueurURL, 1);
        Objet tirJoueu2 = new Objet(1005d, 1005d, formetir, Color.GREEN, TirJoueurURL, 1);
        Objet tirAlien1 = new Objet(20d, 20d, formetir, Color.RED, TirAlienURL, 1);
        Objet tirAlien2 = new Objet(1010d, 1010d, formetir, Color.RED, TirAlienURL, 1);
        assertAll(
                // Alien proche d'un Alien ?
                () -> assertEquals(false, Objet.memeposition(alien1, alien2, -50, 10, -15, 5)),
                // Alien proche d'un tir de joueur ?
                () -> assertEquals(true, Objet.memeposition(alien1, tirJoueu1, -50, 10, -15, 5)),
                () -> assertEquals(false, Objet.memeposition(alien1, tirJoueu2, -50, 10, -15, 5)),
                () -> assertEquals(false, Objet.memeposition(alien2, tirJoueu1, -50, 10, -15, 5)),
                () -> assertEquals(true, Objet.memeposition(alien2, tirJoueu2, -50, 10, -15, 5)),
                // Tir d'alien proche d'un tir de joueur ?
                () -> assertEquals(true, Objet.memeposition(tirAlien1, tirJoueu1, -30, 10, -10, 0)),
                () -> assertEquals(false, Objet.memeposition(tirAlien1, tirJoueu2, -30, 10, -10, 0)),
                () -> assertEquals(false, Objet.memeposition(tirAlien2, tirJoueu1, -30, 10, -10, 0)),
                () -> assertEquals(true, Objet.memeposition(tirAlien2, tirJoueu2, -30, 10, -10, 0))
        );
        assertEquals(true, true);   // Pour être sûr de renvoyer qqch durant le déboguage.
    }

    @Test
    void depalien() {
    }

    @Test
    void collision() {
    }

    @Test
    void collision_joueur() {
    }
}