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
        final String Tir = "file:src\\main\\resources\\Image_tir\\Image_tir_";
        final String Tir_down = "_d.png";
        final String Tir_up = "_u.png";
        String TirJoueurURL = Tir + "4" + Tir_up;
        String TirAlienURL = Tir + "2" + Tir_down;


        // Création de quelques aliens et tirs.
        objet_jeux alien1 = new objet_jeux(10d, 10d, formalien, Color.LIMEGREEN, AlienURL, 1);
        objet_jeux alien2 = new objet_jeux(1000d, 1000d, formalien, Color.LIMEGREEN, AlienURL, 1);
        objet_jeux tirJoueu1 = new objet_jeux(15d, 15d, formetir, Color.GREEN, TirJoueurURL, 1);
        objet_jeux tirJoueu2 = new objet_jeux(1005d, 1005d, formetir, Color.GREEN, TirJoueurURL, 1);
        objet_jeux tirAlien1 = new objet_jeux(15d, 15d, formetir, Color.RED, TirAlienURL, 1);
        objet_jeux tirAlien2 = new objet_jeux(1000d, 1000d, formetir, Color.RED, TirAlienURL, 1);
        // Alien proche d'un alien ?
        assertEquals(false, objet_jeux.memeposition(alien1.getRepresentation(), alien2.getRepresentation(), -50, 10, -15, 5));
        // Alien proche d'un tir de joueur ?
        assertEquals(true, objet_jeux.memeposition(alien1.getRepresentation(), tirJoueu1.getRepresentation(), -50, 10, -15, 5));
        assertEquals(false, objet_jeux.memeposition(alien1.getRepresentation(), tirJoueu2.getRepresentation(), -50, 10, -15, 5));
        assertEquals(false, objet_jeux.memeposition(alien2.getRepresentation(), tirJoueu1.getRepresentation(), -50, 10, -15, 5));
        assertEquals(true, objet_jeux.memeposition(alien2.getRepresentation(), tirJoueu2.getRepresentation(), -50, 10, -15, 5));
        // Tir d'alien proche d'un tir de joueur ?
        assertEquals(true, objet_jeux.memeposition(tirAlien1.getRepresentation(), tirJoueu1.getRepresentation(), -30, 10, -10, 1));
        assertEquals(false, objet_jeux.memeposition(tirAlien1.getRepresentation(), tirJoueu2.getRepresentation(), -30, 10, -10, 1));
        assertEquals(false, objet_jeux.memeposition(tirAlien2.getRepresentation(), tirJoueu1.getRepresentation(), -30, 10, -10, 1));
        assertEquals(true, objet_jeux.memeposition(tirAlien2.getRepresentation(), tirJoueu2.getRepresentation(), -30, 10, -10, 1));
    }

    @Test
    @DisplayName("tir_joueur gère les tirs des joueurs")
    public void tir_joueur() {
        double[] formecanon = {0.0d, 0.0d, 60.0d, 0.0d, 60.0d, 80.0d, 0.0d, 80.0d};
        objet_jeux Player1=new objet_jeux(0d,0d,formecanon,Color.LIMEGREEN,"NULL",1);
        Group tir1=new Group();
        Group tir2=new Group();
        objet_jeux.tir_joueur(50,40,Player1,tir1,"NULL");
        objet_jeux.tir_joueur(50,50,Player1,tir2,"NULL");
        assertEquals(0,tir1.getChildren().size());
        assertEquals(1,tir2.getChildren().size());
    }

    @Test
    @DisplayName("depalien effectue les déplacements coordonnés du ou des groupe(s) d'aliens durant la partie : Déplacement horizontal en temps normal, et vertical aux bords de l'écran.")
    public void depalien() {
        Group aliens = new Group();
        objet_jeux_1J.init_aliens(aliens,"NULL");
        // Aliens en déplacement vers la droite.
        int[] ret1 = objet_jeux.depalien(aliens, 42, 1, "DOWN", 5);
        assertTrue(42 < ret1[0]);
        assertEquals(1, ret1[1]);
        // Aliens en déplacement vers la gauche.
        int[] ret2 = objet_jeux.depalien(aliens, 42, -1, "DOWN", 5);
        assertTrue(42 > ret2[0]);
        assertEquals(-1, ret2[1]);
        // Aliens hors de la limite gauche de l'écran ==> Doivent rebrousser chemin.
        int[] ret3 = objet_jeux.depalien(aliens, -1, -1, "DOWN", 5);
        assertEquals(-1, ret3[0]);
        assertEquals(1, ret3[1]);
        // Aliens hors de la limite droite de l'écran ==> Doivent rebrousser chemin.
        int[] ret4 = objet_jeux.depalien(aliens, 801, 1, "DOWN", 5);
        assertEquals(801, ret4[0]);
        assertEquals(-1, ret4[1]);
    }

    @Test
    @DisplayName("Tir déplace les tirs des joueurs et des aliens")
    public void Tir() {
        double[] formetir = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        Group gr_tir1 = new Group();
        Group gr_tir2 = new Group();
        objet_jeux tir1=new objet_jeux(0d,0d,formetir,Color.LIMEGREEN,"NULL",1);
        objet_jeux tir2=new objet_jeux(0d,0d,formetir,Color.LIMEGREEN,"NULL",1);
        gr_tir1.getChildren().add(tir1.getRepresentation());
        gr_tir2.getChildren().add(tir2.getRepresentation());
        objet_jeux.Tir(gr_tir1,"UP",1);
        objet_jeux.Tir(gr_tir2,"DOWN",1);
        assertTrue(tir1.getRepresentation().getLayoutY()<0);
        assertTrue(tir2.getRepresentation().getLayoutY()>0);
    }

    @Test
    @DisplayName("Test_fin_alien vérifie si les aliens sortent de leur zone et envahissent")
    public void test_fin_alien() {
        double[] formealien = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        objet_jeux alien1=new objet_jeux(50d,50d,formealien,Color.LIMEGREEN,"NULL",1);
        objet_jeux alien2=new objet_jeux(50d,500d,formealien,Color.LIMEGREEN,"NULL",1);
        Group gr1 = new Group();
        Group gr2 = new Group();
        gr1.getChildren().add(alien1.getRepresentation());
        assertFalse(objet_jeux.test_fin_alien(gr1,400,"DOWN"));
        gr2.getChildren().add(alien2.getRepresentation());
        assertTrue(objet_jeux.test_fin_alien(gr2,400,"DOWN"));
    }

    @Test
    @DisplayName("Collision traite les collisions entre 2 groupes")
    public void Collision() {
        double[] formealien = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        objet_jeux alien1=new objet_jeux(50d,50d,formealien,Color.LIMEGREEN,"NULL",1);
        objet_jeux alien2=new objet_jeux(50d,500d,formealien,Color.LIMEGREEN,"NULL",1);
        objet_jeux alien3=new objet_jeux(50d,50d,formealien,Color.LIMEGREEN,"NULL",1);
        Group gr1=new Group();
        Group gr2=new Group();
        gr1.getChildren().add(alien1.getRepresentation());
        gr1.getChildren().add(alien2.getRepresentation());
        gr2.getChildren().add(alien3.getRepresentation());
        objet_jeux.Collision(gr1,gr2,-10,10,-10,10);
        assertEquals("0",alien1.getRepresentation().getAccessibleText());
        assertEquals("1",alien2.getRepresentation().getAccessibleText());
        assertEquals("0",alien3.getRepresentation().getAccessibleText());
    }

    @Test
    @DisplayName("Collision_joueur traite les collisions entre un joueur et un groupe")
    public void Collision_joueur() {
        double[] formealien = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        objet_jeux alien1=new objet_jeux(50d,50d,formealien,Color.LIMEGREEN,"NULL",1);
        objet_jeux alien2=new objet_jeux(50d,500d,formealien,Color.LIMEGREEN,"NULL",1);
        objet_jeux player=new objet_jeux(50d,50d,formealien,Color.LIMEGREEN,"NULL",1);
        Group gr=new Group();
        gr.getChildren().add(alien1.getRepresentation());
        gr.getChildren().add(alien2.getRepresentation());
        objet_jeux.Collision_joueur(player,gr,-10,10,-10,10);
        assertEquals("0",alien1.getRepresentation().getAccessibleText());
        assertEquals("1",alien2.getRepresentation().getAccessibleText());
        assertEquals("0",player.getRepresentation().getAccessibleText());
    }

    @Test
    @DisplayName("supp supprime les éléments sans vie d'un groupe")
    public void supp() {
        double[] formealien = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        objet_jeux alien1=new objet_jeux(50d,50d,formealien,Color.LIMEGREEN,"NULL",1);
        objet_jeux alien2=new objet_jeux(50d,500d,formealien,Color.LIMEGREEN,"NULL",0);
        objet_jeux alien3=new objet_jeux(50d,500d,formealien,Color.LIMEGREEN,"NULL",2);
        Group gr1=new Group();;
        gr1.getChildren().add(alien1.getRepresentation());
        gr1.getChildren().add(alien2.getRepresentation());
        gr1.getChildren().add(alien3.getRepresentation());
        assertEquals(3,gr1.getChildren().size());
        Group gr2=new Group();
        gr2.getChildren().add(alien1.getRepresentation());
        gr2.getChildren().add(alien2.getRepresentation());
        gr2.getChildren().add(alien3.getRepresentation());
        objet_jeux.supp(gr2);
        assertEquals(2,gr2.getChildren().size());
    }

    @Test
    @DisplayName("init_aliens du jeu 1 joueur initialise les aliens")
    public void init_aliens() {
        Group aliens=new Group();
        objet_jeux_1J.init_aliens(aliens,"NULL");
        assertTrue(!aliens.getChildren().isEmpty());
    }

    @Test
    @DisplayName("init_blocks du jeu 1 joueur initialise les blocks")
    public void init_blocks() {
        Group blocks=new Group();
        Group vie_blocks=new Group();
        objet_jeux_1J.init_blocks(blocks,vie_blocks);
        assertTrue(!blocks.getChildren().isEmpty());
        assertEquals(blocks.getChildren().size(),vie_blocks.getChildren().size());
    }

    @Test
    @DisplayName("dep_1J déplace le joueur dans le cas du jeu 1 joueur")
    public void dep_1_joueur() {
        double[] forme = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        objet_jeux player1=new objet_jeux(100,500,forme,Color.LIMEGREEN,"NULL",1);
        objet_jeux player2=new objet_jeux(100,500,forme,Color.LIMEGREEN,"NULL",1);
        objet_jeux_1J.dep_1_joueur(player1,-1,1);
        objet_jeux_1J.dep_1_joueur(player2,1,2);
        assertTrue(player1.getRepresentation().getLayoutX()<100);
        assertTrue(player2.getRepresentation().getLayoutX()>100);
    }

    @Test
    @DisplayName("init_aliens du jeu 2 joueurs initialise les aliens")
    public void init_aliens2() {
        Group aliens=new Group();
        objet_jeux_2J.init_aliens(aliens,"UP","NULL");
        assertTrue(!aliens.getChildren().isEmpty());
        assertTrue(aliens.getChildren().get(0).getLayoutY()<400);
    }

    @Test
    @DisplayName("init_blocks du jeu 2 joueurs initialise les blocks")
    public void init_blocks2() {
        Group blocks=new Group();
        Group vie_blocks=new Group();
        objet_jeux_2J.init_blocks(blocks,vie_blocks);
        assertTrue(!blocks.getChildren().isEmpty());
        assertEquals(blocks.getChildren().size(),vie_blocks.getChildren().size());
    }

    @Test
    @DisplayName("dep_2J déplace les joueurs dans le cas du jeu 2 joueurs")
    public void dep_2_joueur() {
        double[] forme = {0.0d, 0.0d, 20.0d, 0.0d, 20.0d, 40.0d, 0.0d, 40.0d};
        objet_jeux player1=new objet_jeux(100,500,forme,Color.LIMEGREEN,"NULL",1);
        objet_jeux player2=new objet_jeux(100,500,forme,Color.LIMEGREEN,"NULL",1);
        objet_jeux_2J.dep_2_joueurs(player1,player2,-1,1,2);
        assertTrue(player1.getRepresentation().getLayoutX()<100);
        assertTrue(player2.getRepresentation().getLayoutX()>100);
    }
}