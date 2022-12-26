package Game;

import Objet.Objet;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;




public class Game {
    public static int deplacement = 1;
    public static int t = 0;

    public static int pos_gr_alien = 1;







    public static void game_1_joueur(Stage stage,int numTirJoueur, int numTirAlien) throws IOException {

        double screen_width = 1200;
        double screen_height = 600;
        long temps_debut=System.currentTimeMillis();
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);
        Group tirs_joueurs = new Group();
        Group tirs_aliens = new Group();
        Group aliens = new Group();
        Group blocks = new Group();
        Group vie_blocks = new Group();

        Objet.init_aliens(aliens);
        Objet.init_blocks(blocks,vie_blocks);
        Objet Player1 = Objet.init_Player();

        //à compléter
        Text vie_joueur = new Text(Player1.getAccessibleText());
        vie_joueur.setFill(Color.WHITE);

        Text temps=new Text(Float.toString((System.currentTimeMillis()-temps_debut)/1000F));
        temps.setFont(Font.font ("Verdana", 20));
        temps.setFill(Color.WHITE);
        temps.setX(20);
        temps.setY(580);


        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                Objet.depjoueur(e, Player1);
            }
        };

        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //pattern de déplacement des aliens
                //pos_gr_alien : position sur l'écran, pour savoir quand faire demi-tour
                //deplacement : sens de déplacement des aliens
                int ret[];
                ret=Objet.depalien(aliens, pos_gr_alien,deplacement);
                pos_gr_alien=ret[0];
                deplacement=ret[1];

                //tir du joueur tous les 50 mouvements
                t=Objet.tir_joueur(50,Player1,tirs_joueurs, t, numTirJoueur);


                //tir des aliens
                Objet.tir_alien(aliens,tirs_aliens,numTirAlien);

                //déplacement des tirs
                Objet.Tirup(tirs_joueurs);
                Objet.Tirdown(tirs_aliens);
                //enlever les tirs en dehors
                tirs_joueurs.getChildren().removeIf(elem -> elem.getLayoutY() < 0);
                tirs_aliens.getChildren().removeIf(elem -> elem.getLayoutY() > 700);

                //gestion des collisions
                Objet.Collision(aliens, tirs_joueurs, -50, 10, -15, 5);
                Objet.Collision(tirs_aliens, tirs_joueurs, -30, 10, -10, 0);
                Objet.Collision(tirs_aliens, blocks, -10, 80, -10, 10);
                Objet.Collision(tirs_joueurs, blocks, -10, 80, -10, 10);
                //retirer si plus de vie
                Objet.supp(aliens);
                Objet.supp(tirs_joueurs);
                Objet.supp(tirs_aliens);
                Objet.supp(blocks);

                //affichage des vies du joueur (collision avec joueur encore à faire), contenu à changer (voir blocks)
                vie_joueur.setX(Player1.getLayoutX());
                vie_joueur.setY(Player1.getLayoutY());

                Objet.vie_blocks(blocks,vie_blocks);

                temps.setText(Float.toString((System.currentTimeMillis()-temps_debut)/1000F));

            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyListener);
        root.getChildren().addAll(aliens, Player1, tirs_joueurs, tirs_aliens, blocks, vie_joueur, vie_blocks,temps);
        loop.start();
        stage.setTitle("Space Invaders");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); // everything happens everywhere at once
    }
}