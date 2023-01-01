package Objet;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class ObjetTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, 10, 10, Color.BLACK);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }


    @Test
    @DisplayName("memeposition() renvoie True si les 2 objets considérés ont la même position + ou - un intervalle correspondant à leur taille, False sinon.")
    public void memeposition() {
        // Initialisation des variables nécessaires pour créer des objets à faire se rencontrer.
        double[] formalien = {0.0d, 0.0d, 40.0d, 0.0d, 40.0d, 20.0d, 0.0d, 20.0d};
        double[] formetir = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        final String AlienURL = "file:src\\main\\resources\\Image_alien\\Image_alien.png";
        final String Tir = "file:src\\main\\resources\\Image_tir\\Image_tir_";
        final String Tir_down = "_d.png";
        final String Tir_up = "_u.png";
        String TirJoueurURL = Tir + "4" + Tir_up;
        String TirAlienURL = Tir + "2" + Tir_down;


        // Création de quelques aliens et tirs.
        Objet alien1 = new Objet(10d, 10d, formalien, Color.LIMEGREEN, AlienURL, 1);
        Objet alien2 = new Objet(1000d, 1000d, formalien, Color.LIMEGREEN, AlienURL, 1);
        Objet tirJoueu1 = new Objet(15d, 15d, formetir, Color.GREEN, TirJoueurURL, 1);
        Objet tirJoueu2 = new Objet(1005d, 1005d, formetir, Color.GREEN, TirJoueurURL, 1);
        Objet tirAlien1 = new Objet(15d, 15d, formetir, Color.RED, TirAlienURL, 1);
        Objet tirAlien2 = new Objet(1000d, 1000d, formetir, Color.RED, TirAlienURL, 1);
        // Alien proche d'un alien ?
        assertEquals(false, Objet.memeposition(alien1, alien2, -50, 10, -15, 5));
        // Alien proche d'un tir de joueur ?
        assertEquals(true, Objet.memeposition(alien1, tirJoueu1, -50, 10, -15, 5));
        assertEquals(false, Objet.memeposition(alien1, tirJoueu2, -50, 10, -15, 5));
        assertEquals(false, Objet.memeposition(alien2, tirJoueu1, -50, 10, -15, 5));
        assertEquals(true, Objet.memeposition(alien2, tirJoueu2, -50, 10, -15, 5));
        // Tir d'alien proche d'un tir de joueur ?
        assertEquals(true, Objet.memeposition(tirAlien1, tirJoueu1, -30, 10, -10, 1));
        assertEquals(false, Objet.memeposition(tirAlien1, tirJoueu2, -30, 10, -10, 1));
        assertEquals(false, Objet.memeposition(tirAlien2, tirJoueu1, -30, 10, -10, 1));
        assertEquals(true, Objet.memeposition(tirAlien2, tirJoueu2, -30, 10, -10, 1));
    }

    @Test
    @DisplayName("depalien effectue les déplacements coordonnés du ou des groupe(s) d'aliens durant la partie : Déplacement horizontal en temps normal, et vertical aux bords de l'écran.")
    public void depalien() {
        Group aliens = new Group();
        Objet_1J.init_aliens(aliens);
        // Aliens en déplacement vers la droite.
        int[] ret1 = Objet.depalien(aliens, 42, 1, "DOWN", 5);
        assertEquals(42 + 2 + 1, ret1[0]);
        assertEquals(1, ret1[1]);
        // Aliens hors de la limite gauche de l'écran ==> Doivent rebrousser chemin.
        int[] ret2 = Objet.depalien(aliens, -1, -1, "DOWN", 5);
        assertEquals(-1, ret2[0]);
        assertEquals(1, ret2[1]);
        // Aliens hors de la limite droite de l'écran ==> Doivent rebrousser chemin.
        int[] ret3 = Objet.depalien(aliens, 801, 1, "DOWN", 5);
        assertEquals(801, ret3[0]);
        assertEquals(-1, ret3[1]);
    }
}