package Objet_jeux;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class objetTest extends ApplicationTest {

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
        final String tir = "file:src\\main\\resources\\Image_tir\\Image_tir_";
        final String Tir_down = "_d.png";
        final String Tir_up = "_u.png";
        String TirJoueurURL = tir + "4" + Tir_up;
        String TirAlienURL = tir + "2" + Tir_down;


        // Création de quelques aliens et tirs.
        Alien alien1 = new Alien(10d, 10d, AlienURL);
        Alien alien2 = new Alien(1000d, 1000d, AlienURL);
        Tir tirJoueu1 = new Tir(15d, 15d, Color.GREEN, TirJoueurURL);
        Tir tirJoueu2 = new Tir(1005d, 1005d, Color.GREEN, TirJoueurURL);
        Tir tirAlien1 = new Tir(15d, 15d, Color.RED, TirAlienURL);
        Tir tirAlien2 = new Tir(1000d, 1000d, Color.RED, TirAlienURL);
        // Alien proche d'un alien ?
        assertEquals(false, Tir.memeposition(alien1.getRepresentation(), alien2.getRepresentation(), -50, 10, -15, 5));
        // Alien proche d'un tir de joueur ?
        assertEquals(true, Tir.memeposition(alien1.getRepresentation(), tirJoueu1.getRepresentation(), -50, 10, -15, 5));
        assertEquals(false, Tir.memeposition(alien1.getRepresentation(), tirJoueu2.getRepresentation(), -50, 10, -15, 5));
        assertEquals(false, Tir.memeposition(alien2.getRepresentation(), tirJoueu1.getRepresentation(), -50, 10, -15, 5));
        assertEquals(true, Tir.memeposition(alien2.getRepresentation(), tirJoueu2.getRepresentation(), -50, 10, -15, 5));
        // Tir d'alien proche d'un tir de joueur ?
        assertEquals(true, Tir.memeposition(tirAlien1.getRepresentation(), tirJoueu1.getRepresentation(), -30, 10, -10, 1));
        assertEquals(false, Tir.memeposition(tirAlien1.getRepresentation(), tirJoueu2.getRepresentation(), -30, 10, -10, 1));
        assertEquals(false, Tir.memeposition(tirAlien2.getRepresentation(), tirJoueu1.getRepresentation(), -30, 10, -10, 1));
        assertEquals(true, Tir.memeposition(tirAlien2.getRepresentation(), tirJoueu2.getRepresentation(), -30, 10, -10, 1));
    }

    @Test
    @DisplayName("tir_joueur gère les tirs des joueurs")
    public void tir_joueur() {
        Canon Player1=new Canon(0d,0d,"NULL");
        Group tir1=new Group();
        Group tir2=new Group();
        Player1.tir_joueur(50,40,tir1,"NULL");
        Player1.tir_joueur(50,50,tir2,"NULL");
        assertEquals(0,tir1.getChildren().size());
        assertEquals(1,tir2.getChildren().size());
    }

    @Test
    @DisplayName("depalien effectue les déplacements coordonnés du ou des groupe(s) d'aliens durant la partie : Déplacement horizontal en temps normal, et vertical aux bords de l'écran.")
    public void depalien() {
        Group aliens = new Group();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                Alien alien = new Alien(10d + 50 * i, 10d + 35 * j, "NULL");
                //Alien alien = new Alien(10d + 50 * i, 10d + 35 * j, Color.LIMEGREEN, URL_alien, 1);
                aliens.getChildren().add(alien.getRepresentation());
            }
        }
        //objet_jeux_1J.init_aliens(aliens,"NULL");
        // Aliens en déplacement vers la droite.
        int[] ret1 = Alien.depalien(aliens, 42, 1, "DOWN", 5);
        assertTrue(42 < ret1[0]);
        assertEquals(1, ret1[1]);
        // Aliens en déplacement vers la gauche.
        int[] ret2 = Alien.depalien(aliens, 42, -1, "DOWN", 5);
        assertTrue(42 > ret2[0]);
        assertEquals(-1, ret2[1]);
        // Aliens hors de la limite gauche de l'écran ==> Doivent rebrousser chemin.
        int[] ret3 = Alien.depalien(aliens, -1, -1, "DOWN", 5);
        assertEquals(-1, ret3[0]);
        assertEquals(1, ret3[1]);
        // Aliens hors de la limite droite de l'écran ==> Doivent rebrousser chemin.
        int[] ret4 = Alien.depalien(aliens, 801, 1, "DOWN", 5);
        assertEquals(801, ret4[0]);
        assertEquals(-1, ret4[1]);
    }

    @Test
    @DisplayName("Tir déplace les tirs des joueurs et des aliens")
    public void Tir() {
        Group gr_tir1 = new Group();
        Group gr_tir2 = new Group();
        Tir tir1=new Tir(0d,0d,Color.LIMEGREEN,"NULL");
        Tir tir2=new Tir(0d,0d,Color.LIMEGREEN,"NULL");
        gr_tir1.getChildren().add(tir1.getRepresentation());
        gr_tir2.getChildren().add(tir2.getRepresentation());
        Tir.dep(gr_tir1,"UP",1);
        Tir.dep(gr_tir2,"DOWN",1);
        assertTrue(tir1.getRepresentation().getLayoutY()<0);
        assertTrue(tir2.getRepresentation().getLayoutY()>0);
    }

    @Test
    @DisplayName("Test_fin_alien vérifie si les aliens sortent de leur zone et envahissent")
    public void test_fin_alien() {
        Alien alien1=new Alien(50d,50d,"NULL");
        Alien alien2=new Alien(50d,500d,"NULL");
        Group gr1 = new Group();
        Group gr2 = new Group();
        gr1.getChildren().add(alien1.getRepresentation());
        assertFalse(Alien.test_fin_alien(gr1,400,"DOWN"));
        gr2.getChildren().add(alien2.getRepresentation());
        assertTrue(Alien.test_fin_alien(gr2,400,"DOWN"));
    }

    @Test
    @DisplayName("Collision traite les collisions entre 2 groupes")
    public void Collision() {
        Alien alien1 = new Alien(50d,50d,"NULL");
        Alien alien2 = new Alien(50d,500d,"NULL");
        Alien alien3 = new Alien(50d,50d,"NULL");
        Group gr1=new Group();
        Group gr2=new Group();
        gr1.getChildren().add(alien1.getRepresentation());
        gr1.getChildren().add(alien2.getRepresentation());
        gr2.getChildren().add(alien3.getRepresentation());
        Tir.Collision(gr1,gr2,-10,10,-10,10);
        assertEquals("0",alien1.getRepresentation().getAccessibleText());
        assertEquals("1",alien2.getRepresentation().getAccessibleText());
        assertEquals("0",alien3.getRepresentation().getAccessibleText());
    }

    @Test
    @DisplayName("Collision_joueur traite les collisions entre un joueur et un groupe")
    public void Collision_joueur() {
        Alien alien1 = new Alien(50d,50d,"NULL");
        Alien alien2 = new Alien(50d,500d,"NULL");
        Canon player = new Canon(50d,50d,"NULL");
        Group gr=new Group();
        gr.getChildren().add(alien1.getRepresentation());
        gr.getChildren().add(alien2.getRepresentation());
        Tir.Collision_joueur(player,gr,-10,10,-10,10);
        assertEquals("0",alien1.getRepresentation().getAccessibleText());
        assertEquals("1",alien2.getRepresentation().getAccessibleText());
        assertEquals("0",player.getRepresentation().getAccessibleText());
    }

    @Test
    @DisplayName("supp supprime les éléments sans vie d'un groupe")
    public void supp() {
        Alien alien1 = new Alien(50d,50d,"NULL",1);
        Alien alien2 = new Alien(50d,500d,"NULL",0);
        Alien alien3 = new Alien(50d,500d,"NULL",2);
        Group gr1=new Group();;
        gr1.getChildren().add(alien1.getRepresentation());
        gr1.getChildren().add(alien2.getRepresentation());
        gr1.getChildren().add(alien3.getRepresentation());
        assertEquals(3,gr1.getChildren().size());
        Group gr2=new Group();
        gr2.getChildren().add(alien1.getRepresentation());
        gr2.getChildren().add(alien2.getRepresentation());
        gr2.getChildren().add(alien3.getRepresentation());
        Tir.supp(gr2);
        assertEquals(2,gr2.getChildren().size());
    }

    @Test
    @DisplayName("dep_joueur déplace le joueur")
    public void dep_joueur() {
        Canon player1=new Canon(100,500,"NULL");
        Canon player2=new Canon(100,500,"NULL");
        player1.dep_joueur(-1,1);
        player2.dep_joueur(1,2);
        assertTrue(player1.getRepresentation().getLayoutX()<100);
        assertTrue(player2.getRepresentation().getLayoutX()>100);
    }
}