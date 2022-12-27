package Game;

import Objet.Objet;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;




public class Game {
    public static int deplacement = 1;
    public static int t = 0;

    public static int pos_gr_alien = 1;

    public static Boolean pause=false;

    public static long tempause=0;
    public static long tpa=0;

    private static final String FondURL = "file:src\\main\\resources\\Image_fond\\Image_fond_1.jpg";
    private static final String MusiqueUrl="src\\main\\resources\\Musique\\Musique_1.mp3";

    public static int res;





    public static void game_1_joueur(Stage stage,int numTirJoueur, int numTirAlien) throws IOException {


        double screen_width = 1200;
        double screen_height = 800;
        long temps_debut=System.currentTimeMillis();
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);


        Media sound = new Media(new File(MusiqueUrl).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();

        Image image_fond= new Image(FondURL);
        scene.setFill(new ImagePattern(image_fond, 0, 0, 1, 1, true));

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
        temps.setY(780);

        Text text_pause=new Text("PAUSE");
        text_pause.setFont((Font.font ("Verdana", 80)));
        text_pause.setFill(Color.WHITE);
        text_pause.setX(480);
        text_pause.setY(400);

        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if ((e.getCode()==KeyCode.LEFT || e.getCode()==KeyCode.RIGHT) && !pause) {
                    Objet.depjoueur(e, Player1);
                } else if (e.getCode()==KeyCode.SPACE) {
                    if (pause==true) {
                        pause=false;
                        tempause=tempause+(System.currentTimeMillis()-tpa);
                        root.getChildren().remove(text_pause);
                        root.getChildren().addAll(aliens, Player1, tirs_joueurs, tirs_aliens, blocks, vie_joueur, vie_blocks,temps);
                    }
                    else if (pause==false) {
                        pause=true;
                        tpa=System.currentTimeMillis();
                        root.getChildren().clear();
                        root.getChildren().add(text_pause);
                    }
                }
            }
        };

        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!pause) {
                    //pattern de déplacement des aliens
                    //pos_gr_alien : position sur l'écran, pour savoir quand faire demi-tour
                    //deplacement : sens de déplacement des aliens
                    int ret[];
                    ret = Objet.depalien(aliens, pos_gr_alien, deplacement);
                    pos_gr_alien = ret[0];
                    deplacement = ret[1];

                    //tir du joueur tous les 50 mouvements
                    t = Objet.tir_joueur(50, Player1, tirs_joueurs, t, numTirJoueur);


                    //tir des aliens
                    Objet.tir_alien(aliens, tirs_aliens, numTirAlien);

                    //déplacement des tirs
                    Objet.Tirup(tirs_joueurs);
                    Objet.Tirdown(tirs_aliens);

                    //enlever les tirs en dehors
                    tirs_joueurs.getChildren().removeIf(elem -> elem.getLayoutY() < 0);
                    tirs_aliens.getChildren().removeIf(elem -> elem.getLayoutY() > 900);

                    //gestion des collisions
                    Objet.Collision(aliens, tirs_joueurs, -50, 10, -15, 5);
                    Objet.Collision(tirs_aliens, tirs_joueurs, -30, 10, -10, 0);
                    Objet.Collision(tirs_aliens, blocks, -10, 80, -10, 10);
                    Objet.Collision(tirs_joueurs, blocks, -10, 80, -10, 10);
                    Objet.Collision_joueur(Player1,tirs_aliens,-20,20,-20,20);
                    //retirer si plus de vie
                    Objet.supp(aliens);
                    Objet.supp(tirs_joueurs);
                    Objet.supp(tirs_aliens);
                    Objet.supp(blocks);

                    //affichage des vies du joueur (collision avec joueur encore à faire), contenu à changer (voir blocks)
                    vie_joueur.setX(Player1.getLayoutX());
                    vie_joueur.setY(Player1.getLayoutY());
                    vie_joueur.setText(Player1.getAccessibleText());

                    Objet.vie_blocks(blocks, vie_blocks);

                    temps.setText(Float.toString((System.currentTimeMillis() - temps_debut-tempause) / 1000F));



                    ////////////VINCENT CASTELAN////////////////
                    // créer des pages de fin sur le modèle des menus
                    // selon victoire ou défaite, différencier lignes suivantes et remplacer Platform.exit()
                    // par un appel aux fonctions correspondantes
                    // important : transmettre Stage stage
                    if (aliens.getChildren().size()==0
                            || Player1.getAccessibleText().equals("0")
                                || Objet.test_fin_alien(aliens,300)) {
                        Platform.exit();
                    }
                }

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