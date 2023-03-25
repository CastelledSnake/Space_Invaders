package Game;

import End_of_game.End_of_game;
import Objet_jeux.*;
import javafx.animation.AnimationTimer;
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
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Gère les parties de jeu
 */
public class GameGUI implements PropertyChangeListener {
    // Adresses relatives du fond et de la musique
    private static final String fond_url = "file:Space_Invaders\\src\\main\\resources\\Image_fond\\Image_fond_1.jpg";
    private static final String MusiqueUrl = "Space_Invaders\\src\\main\\resources\\Musique\\Musique_1.mp3";

    static MediaPlayer player;
    private Game game;
    private Polygon player1 = new Polygon();
    private Polygon player2 = new Polygon();
    private Group aliens = new Group();
    private Group aliens_1 = new Group();
    private Group aliens_2 = new Group();

    public GameGUI(Game unGame) {
        game = unGame;
        game.getPropertyChangeSupport().addPropertyChangeListener(this);
    }

    /**
     * Crée la musique
     * @param URL adresse relative de la musique à jouer
     */
    public void music(String URL) {
        Media sound = new Media(Paths.get(URL).toUri().toString());
        player = new MediaPlayer(sound);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.getOnRepeat();
        player.play();
    }

    /**
     * Gère les parties à 1 Joueur
     * @param stage Stage
     */
    public void game_1_joueur(Stage stage) {

        // Initialisation du jeu

        double screen_width = 1200;
        double screen_height = 700;
        long temps_debut = System.currentTimeMillis();
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);


        // Initialisation des variables

        // Tente de mettre la musique de fond

        try {
            music(MusiqueUrl);
        }
        catch (Exception e) {
            System.out.println("Impossible de lancer la musique");
            // On choisit de ne pas utiliser de musique
        }


        // Tente de mettre le fond
        try {
            // Erreur capturé par JavaFX -> détection manuelle
            Image image_fond = new Image(fond_url);
            if (image_fond.isError()) throw new FileNotFoundException();
            scene.setFill(new ImagePattern(image_fond, 0, 0, 1, 1, true));
        }
        catch ( Exception e) {
            System.out.println("Impossible d'afficher le fond");
            // On n'affiche pas de fond
        }

        // Initialisation des différents Objets
        Group tirs_joueurs = new Group();
        Group tirs_aliens = new Group();
        //Group aliens = new Group();
        Group blocks = new Group();
        Group vie_blocks = new Group();

        // Remplissage de aliens
        game.initAliens_1J();
        for (int k = 0; k < game.getAliens1J().size(); k++) {
            aliens.getChildren().add(game.getAliens1J().get(k).getRepresentation());
        }

        // Remplissage de blocks
        game.initBlocks_1J();
        for (int k = 0; k < game.getBlocks1J().size(); k++) {
            blocks.getChildren().add(game.getBlocks1J().get(k).getRepresentation());
            Text v = new Text(game.getBlocks1J().get(k).getRepresentation().getAccessibleText());
            v.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v);
            v.setX(game.getBlocks1J().get(k).getRepresentation().getLayoutX());
            v.setY(game.getBlocks1J().get(k).getRepresentation().getLayoutY());
        }

        // Initialisation Player1
        game.initPlayer_1J();
        player1=game.getPlayer1().getRepresentation();

        // Vie du joueur, affiché sur lui même
        Text vie_joueur = new Text(player1.getAccessibleText());
        vie_joueur.setFill(Color.WHITE);

        // Chrono
        Text temps = new Text(Float.toString((System.currentTimeMillis() - temps_debut) / 1000F));
        temps.setFont(Font.font("Verdana", 20));
        temps.setFill(Color.WHITE);
        temps.setX(20);
        temps.setY(680);

        // Texte de pause
        Text text_pause = new Text("PAUSE");
        text_pause.setFont((Font.font("Verdana", 80)));
        text_pause.setFill(Color.WHITE);
        text_pause.setX(480);
        text_pause.setY(350);

        // Texte de niveau
        //Text niveau = new Text("Niveau " +Integer.toString(difficulte));
        Text niveau = new Text("Niveau " +Integer.toString(game.getDifficulte()));
        niveau.setFont(Font.font("Verdana", 20));
        niveau.setFill(Color.WHITE);
        niveau.setX(1080);
        niveau.setY(680);

        // Action dans le cas d'une touche pressée
        EventHandler<KeyEvent> keyListenerPressed = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                //Mettre à jour le mode de tir des joueurs
                if ((e.getCode() == KeyCode.DOWN)) {
                    if (game.getPlayer1().getModeTir() instanceof ModeTirAuto) {
                        game.setT(0);
                        game.getPlayer1().setModeTir(new ModeTirManuel());
                        System.out.println("Mode de tir manuel");
                    }
                    else {
                        game.setT(0);
                        game.getPlayer1().setModeTir(new ModeTirAuto());
                        System.out.println("Mode de tir automatique");
                    }
                }
                if ((e.getCode() == KeyCode.UP)) {
                    game.getPlayer1().setFire(true);
                }
                //changer la direction du joueur
                if ((e.getCode() == KeyCode.LEFT)) {
                    //dir_p1 = -1;
                    game.setDir_p1(-1);
                }
                else if (e.getCode() == KeyCode.RIGHT) {
                    //dir_p1=1;
                    game.setDir_p1(1);
                }
                    //activer/désactiver l'écran de pause
                else if (e.getCode() == KeyCode.SPACE) {
                    if (game.getPause()) {
                        //pause = false;
                        game.setPause(false);
                        //tempause = tempause + (System.currentTimeMillis() - tpa);
                        game.setTempause(game.getTempause()+(System.currentTimeMillis() - game.getTpa()));
                        root.getChildren().remove(text_pause);
                        root.getChildren().addAll(aliens, player1, tirs_joueurs, tirs_aliens, blocks, vie_joueur, vie_blocks, temps, niveau);
                    } else if (!game.getPause()) {
                        //pause = true;
                        game.setPause(true);
                        //tpa = System.currentTimeMillis();
                        game.setTpa(System.currentTimeMillis());
                        root.getChildren().clear();
                        root.getChildren().add(text_pause);
                    }
                }
            }
        };
        //
        // Action dans le cas d'une touche lâchée
        EventHandler<KeyEvent> keyListenerReleased = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                // mettre à jour le sens de déplacement du joueur
                if ((e.getCode() == KeyCode.LEFT)) {
                    //dir_p1 = 0;
                    game.setDir_p1(0);
                }
                else if (e.getCode() == KeyCode.RIGHT) {
                    //dir_p1=0;
                    game.setDir_p1(0);
                }
            }
        };

        // Action à réaliser périodiquement dans le jeu
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!game.getPause()) {

                    // Déplacement du joueur
                    game.depPlayer1();

                    // Pattern de déplacement des aliens
                    // pos_gr_alien : position sur l'écran, pour savoir quand faire demi-tour
                    // deplacement : sens de déplacement des aliens
                    int ret[];
                    ret = Alien.depalien(aliens, game.getPos_gr_alien(), game.getDeplacement(), "DOWN", game.getDifficulte());
                    game.setPos_gr_alien(ret[0]);
                    game.setDeplacement(ret[1]);
                    //game.depAliens_1J();

                    // Tir du joueur tous les max(30,100-5*difficulté) mouvements
                    //t = Player1.tir_joueur(Math.max(30,100-5*difficulte), t, tirs_joueurs, URL_tir_vaisseau);
                    //game.setT(game.getPlayer1().tir_joueur(Math.max(30,100-5*game.getDifficulte()), game.getT(),
                            //tirs_joueurs, game.getURL_tir_vaisseau()));
                    game.setT(game.getPlayer1().getModeTir().tir_joueur(Math.max(30,100-5*game.getDifficulte()), game.getT(),
                            tirs_joueurs, game.getURL_tir_vaisseau(), game.getPlayer1()));

                    // Tir des aliens
                    //Alien.tir_alien(aliens, tirs_aliens, URL_tir_alien,Math.max(10,50-5*difficulte));
                    Alien.tir_alien(aliens, tirs_aliens, game.getURL_tir_alien(),Math.max(10,50-5*game.getDifficulte()));

                    // Déplacement des tirs
                    //Tir.dep(tirs_joueurs, "UP",difficulte);
                    Tir.dep(tirs_joueurs, "UP",game.getDifficulte());
                    //Tir.dep(tirs_aliens, "DOWN",difficulte);
                    Tir.dep(tirs_aliens, "DOWN",game.getDifficulte());

                    // Enlever les tirs en dehors
                    tirs_joueurs.getChildren().removeIf(elem -> elem.getLayoutY() < 0);
                    tirs_aliens.getChildren().removeIf(elem -> elem.getLayoutY() > 900);


                    // Gestion des collisions
                    Tir.Collision(aliens, tirs_joueurs, -50, 10, -15, 5);
                    Tir.Collision(tirs_aliens, tirs_joueurs, -30, 10, -10, 0);
                    Tir.Collision(tirs_aliens, blocks, -10, 80, -10, 10);
                    Tir.Collision(tirs_joueurs, blocks, -10, 80, -10, 10);
                    Tir.Collision_joueur(game.getPlayer1(), tirs_aliens, -20, 20, -20, 20);
                    Tir.supp(aliens);
                    Tir.supp(tirs_joueurs);
                    Tir.supp(tirs_aliens);
                    Tir.supp(blocks);

                    // Affichage des vies du joueur
                    vie_joueur.setX(player1.getLayoutX());
                    vie_joueur.setY(player1.getLayoutY());
                    vie_joueur.setText(player1.getAccessibleText());

                    // MAJ de la vie des blocks
                    Block.vie_blocks(blocks, vie_blocks);

                    // MAJ du chrono
                    //temps.setText(Float.toString((System.currentTimeMillis() - temps_debut - tempause) / 1000F));
                    temps.setText(Float.toString((System.currentTimeMillis() - temps_debut - game.getTempause()) / 1000F));

                    // S'il n'y a plus d'aliens -> Victoire
                    if (aliens.getChildren().isEmpty()) {   // GAGNE
                        //if (true) {
                        End_of_game.endOfGame_1_joueur(stage, 0,
                                (System.currentTimeMillis() - temps_debut-game.getTempause()) / 1000F,
                                0,
                                player,
                                game.getDifficulte(),
                                game.getURL_vaisseau(),
                                game.getURL_alien(),
                                game.getURL_tir_vaisseau(),
                                game.getURL_tir_alien());
                        stop();
                    }
                    else if (Integer.valueOf(player1.getAccessibleText())<=0) {  // PERDU : le joueur est mort.
                        End_of_game.endOfGame_1_joueur(stage, 1,
                                (System.currentTimeMillis() - temps_debut-game.getTempause()) / 1000F,
                                aliens.getChildren().size(),
                                player,
                                game.getDifficulte(),
                                game.getURL_vaisseau(),
                                game.getURL_alien(),
                                game.getURL_tir_vaisseau(),
                                game.getURL_tir_alien());
                        stop();
                    }
                    else if (Alien.test_fin_alien(aliens,500, "DOWN")) {    // PERDU : les aliens ont atteint la Terre.
                        End_of_game.endOfGame_1_joueur(stage, 3,
                                (System.currentTimeMillis() - temps_debut-game.getTempause()) / 1000F,
                                aliens.getChildren().size(),
                                player,
                                game.getDifficulte(),
                                game.getURL_vaisseau(),
                                game.getURL_alien(),
                                game.getURL_tir_vaisseau(),
                                game.getURL_tir_alien());
                        stop();
                    }
                }

            }
        };

        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyListenerPressed);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyListenerReleased);
        root.getChildren().addAll(aliens, player1, tirs_joueurs, tirs_aliens, blocks, vie_joueur, vie_blocks, temps, niveau);
        loop.start();
        stage.setTitle("Space Invaders");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); // everything happens everywhere at once
    }

    //-------------------------------------------------------------------------------------------------------------------------//

    /**
     * Gère les parties à 2 joueurs
     * @param stage Stage
     */
    public void game_2_joueurs(Stage stage) {

        //Initialisation du jeu

        double screen_width = 1200;
        double screen_height = 700;
        long temps_debut = System.currentTimeMillis();
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);

        //tente de mettre la musique de fond
        try {
            music(MusiqueUrl);
        }
        catch (Exception e) {
            System.out.println("Impossible de lancer la musique");
            //On choisit de ne pas utiliser de musique
        }


        //tente de mettre le fond
        try {
            //erreur capturée par JavaFX -> détection manuelle
            Image image_fond = new Image(fond_url);
            if (image_fond.isError()) throw new FileNotFoundException();
            scene.setFill(new ImagePattern(image_fond, 0, 0, 1, 1, true));
        }
        catch ( Exception e) {
            System.out.println("Impossible d'afficher le fond");
            //On n'affiche pas de fond
        }

        Group tirs_joueurs_1 = new Group();
        Group tirs_joueurs_2 = new Group();
        Group tirs_aliens_1 = new Group();
        Group tirs_aliens_2 = new Group();
        //les aliens_1 vont vers le bas
        //Group aliens_1 = new Group();
        //les aliens_2 vont vers le haut
        //Group aliens_2 = new Group();
        Group blocks = new Group();
        Group vie_blocks = new Group();

        // remplissage de aliens_1 et aliens_2
        game.initAliens_2J();
        for (int k = 0; k < (game.getAliens2J_1().size()); k++) {
            aliens_1.getChildren().add(game.getAliens2J_1().get(k).getRepresentation());
            aliens_2.getChildren().add(game.getAliens2J_2().get(k).getRepresentation());
        }

        // remplissage de blocks
        game.initBlocks_2J();
        for (int k = 0; k < game.getBlocks2J().size(); k++) {
            blocks.getChildren().add(game.getBlocks2J().get(k).getRepresentation());
            Text v = new Text(game.getBlocks2J().get(k).getRepresentation().getAccessibleText());
            v.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v);
            v.setX(game.getBlocks2J().get(k).getRepresentation().getLayoutX());
            v.setY(game.getBlocks2J().get(k).getRepresentation().getLayoutY());
        }

        //initialisation player1 et player2
        //player1 est en haut et tire vers le bas
        game.initPlayer_2J_1();
        player1=game.getPlayer1().getRepresentation();

        //player2 est en bas et tire vers le haut
        game.initPlayer_2J_2();
        player2=game.getPlayer2().getRepresentation();

        //représente la vie des joueurs
        Text vie_joueur_1 = new Text(player1.getAccessibleText());
        vie_joueur_1.setFill(Color.WHITE);
        Text vie_joueur_2 = new Text(player1.getAccessibleText());
        vie_joueur_2.setFill(Color.WHITE);

        //Chrono
        Text temps = new Text(Float.toString((System.currentTimeMillis() - temps_debut) / 1000F));
        temps.setFont(Font.font("Verdana", 20));
        temps.setFill(Color.WHITE);
        temps.setX(20);
        temps.setY(680);

        //Ecran de pause
        Text text_pause = new Text("PAUSE");
        text_pause.setFont((Font.font("Verdana", 80)));
        text_pause.setFill(Color.WHITE);
        text_pause.setX(480);
        text_pause.setY(350);

        //Niveau
        //Text niveau = new Text("Niveau " +Integer.toString(difficulte));
        Text niveau = new Text("Niveau " +Integer.toString(game.getDifficulte()));
        niveau.setFont(Font.font("Verdana", 20));
        niveau.setFill(Color.WHITE);
        niveau.setX(1080);
        niveau.setY(680);

        //Si une touche est pressée
        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                //Mettre à jour le mode de tir des joueurs
                if ((e.getCode() == KeyCode.DOWN)) {
                    if (game.getPlayer1().getModeTir() instanceof ModeTirAuto) {
                        game.setT(0);
                        game.getPlayer1().setModeTir(new ModeTirManuel());
                        System.out.println("Player1: Mode de tir manuel");
                    }
                    else {
                        game.setT(0);
                        game.getPlayer1().setModeTir(new ModeTirAuto());
                        System.out.println("Player1: Mode de tir automatique");
                    }
                }
                if ((e.getCode() == KeyCode.UP)) {
                    game.getPlayer1().setFire(true);
                }
                if ((e.getCode() == KeyCode.S)) {
                    if (game.getPlayer2().getModeTir() instanceof ModeTirAuto) {
                        game.setT2(0);
                        game.getPlayer2().setModeTir(new ModeTirManuel());
                        System.out.println("Player2: Mode de tir manuel");
                    }
                    else {
                        game.setT2(0);
                        game.getPlayer2().setModeTir(new ModeTirAuto());
                        System.out.println("Player2: Mode de tir automatique");
                    }
                }
                if ((e.getCode() == KeyCode.Z)) {
                    game.getPlayer2().setFire(true);
                }
                //Mettre à jour le déplacement des joueurs
                if ((e.getCode() == KeyCode.LEFT)) {
                    game.setDir_p1(-1);
                    if (game.getNetwork())
                        game.getMonClientTCP().transmettreChaine("LEFT");
                }
                else if (e.getCode() == KeyCode.RIGHT) {
                    game.setDir_p1(1);
                    if (game.getNetwork())
                        game.getMonClientTCP().transmettreChaine("RIGHT");
                }
                else if (e.getCode() == KeyCode.Q) {
                    //dir_p2=-1;
                    game.setDir_p2(-1);
                }
                else if (e.getCode() == KeyCode.D) {
                    //dir_p2=1;
                    game.setDir_p2(1);
                }
                    //Activer / désactiver l'écran Pause
                else if (e.getCode() == KeyCode.SPACE) {
                    if (game.getPause()) {
                        //pause = false;
                        game.setPause(false);
                        //tempause = tempause + (System.currentTimeMillis() - tpa);
                        game.setTempause(game.getTempause() + (System.currentTimeMillis() - game.getTpa()));
                        root.getChildren().remove(text_pause);
                        root.getChildren().addAll(player1, player2,
                                tirs_joueurs_1, tirs_joueurs_2, tirs_aliens_1, tirs_aliens_2, aliens_1, aliens_2,
                                blocks, vie_blocks,temps,vie_joueur_1,vie_joueur_2, niveau);
                    } else if (!game.getPause()) {
                        //pause = true;
                        game.setPause(true);
                        //tpa = System.currentTimeMillis();
                        game.setTpa(System.currentTimeMillis());
                        root.getChildren().clear();
                        root.getChildren().add(text_pause);
                    }
                }
            }
        };
        //Si une touche est relâchée
        EventHandler<KeyEvent> keyListener2 = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                //Mise à jour du sens de déplacement
                if ((e.getCode() == KeyCode.LEFT)) {
                    //dir_p1 = 0;
                    game.setDir_p1(0);
                }
                else if (e.getCode() == KeyCode.RIGHT) {
                    //dir_p1=0;
                    game.setDir_p1(0);
                }
                else if (e.getCode() == KeyCode.Q) {
                    //dir_p2=0;
                    game.setDir_p2(0);
                }
                else if (e.getCode() == KeyCode.D) {
                    //dir_p2=0;
                    game.setDir_p2(0);
                }
            }
        };

        //Action à réaliser périodiquement
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!game.getPause()) {
                    //pattern de déplacement des aliens
                    //pos_gr_alien : position sur l'écran, pour savoir quand faire demi-tour
                    //déplacement : sens de déplacement des aliens
                    int ret[];
                    int ret2[];
                    if (!aliens_1.getChildren().isEmpty()) {
                        ret = Alien.depalien(aliens_1, game.getPos_gr_alien(), game.getDeplacement(), "DOWN", game.getDifficulte());
                        game.setPos_gr_alien(ret[0]);
                        game.setDeplacement(ret[1]);
                    }
                    if (!aliens_2.getChildren().isEmpty()) {
                        ret2 = Alien.depalien(aliens_2, game.getPos_gr_alien2(), game.getDeplacement2(), "UP", game.getDifficulte());
                        game.setPos_gr_alien2(ret2[0]);
                        game.setDeplacement2(ret2[1]);
                    }

                    //tir du joueur tous les max(30,100-5*difficulté) mouvements
                    game.setT(game.getPlayer1().getModeTir().tir_joueur(Math.max(20,60-5*game.getDifficulte()), game.getT(),
                            tirs_joueurs_1, game.getURL_tir2(), game.getPlayer1()));
                    game.setT2(game.getPlayer2().getModeTir().tir_joueur(Math.max(20,60-5*game.getDifficulte()), game.getT2(),
                            tirs_joueurs_2, game.getURL_tir1(), game.getPlayer2()));



                    //tir des aliens
                    if (!aliens_1.getChildren().isEmpty()) {
                        Alien.tir_alien(aliens_1, tirs_aliens_1, game.getURL_tir_alien_down(), Math.max(10,50-5*game.getDifficulte()));
                    }
                    if (!aliens_2.getChildren().isEmpty()) {
                        Alien.tir_alien(aliens_2, tirs_aliens_2, game.getURL_tir_alien_up(), Math.max(10,50-5*game.getDifficulte()));
                    }

                    //déplacement des tirs
                    //Tir.dep(tirs_joueurs_1, "DOWN", difficulte);
                    Tir.dep(tirs_joueurs_1, "DOWN", game.getDifficulte());
                    //Tir.dep(tirs_joueurs_2, "UP", difficulte);
                    Tir.dep(tirs_joueurs_2, "UP", game.getDifficulte());
                    //Tir.dep(tirs_aliens_1, "DOWN", difficulte);
                    Tir.dep(tirs_aliens_1, "DOWN", game.getDifficulte());
                    //Tir.dep(tirs_aliens_2, "UP", difficulte);
                    Tir.dep(tirs_aliens_2, "UP", game.getDifficulte());


                    //enlever les tirs en dehors
                    tirs_joueurs_1.getChildren().removeIf(elem -> elem.getLayoutY() > 900);
                    tirs_joueurs_2.getChildren().removeIf(elem -> elem.getLayoutY() < 0);
                    tirs_aliens_1.getChildren().removeIf(elem -> elem.getLayoutY() > 900);
                    tirs_aliens_2.getChildren().removeIf(elem -> elem.getLayoutY() < 0);

                    //Déplacer les joueurs
                    //player1.dep_joueur(dir_p1, difficulte);
                    //game.getPlayer1().dep_joueur(game.getDir_p1(), game.getDifficulte());
                    game.depPlayer1();
                    //player2.dep_joueur(dir_p2, difficulte);
                    //game.getPlayer2().dep_joueur(game.getDir_p2(), game.getDifficulte());
                    game.depPlayer2();

                    //gestion des collisions
                    Tir.Collision(aliens_1, tirs_joueurs_2, -50, 10, -15, 5);
                    Tir.Collision(aliens_1, tirs_joueurs_1, -30, 30, 0, 20);
                    Tir.Collision(aliens_2, tirs_joueurs_2, -30, 30, 0, 20);
                    Tir.Collision(aliens_2, tirs_joueurs_1, -30, 30, 0, 20);
                    Tir.Collision(tirs_aliens_2, tirs_joueurs_1, -30, 30, -10, 10);
                    Tir.Collision(tirs_aliens_1, tirs_joueurs_2, -30, 30, -10, 10);
                    Tir.Collision(tirs_aliens_1, blocks, -10, 80, -10, 10);
                    Tir.Collision(tirs_aliens_2, blocks, -10, 80, -10, 10);
                    Tir.Collision(tirs_joueurs_1, blocks, -10, 80, -10, 10);
                    Tir.Collision(tirs_joueurs_2, blocks, -10, 80, -10, 10);
                    Tir.Collision(tirs_joueurs_1, tirs_joueurs_2, -20, 20, -20, 20);
                    Tir.Collision_joueur(game.getPlayer2(), tirs_aliens_1, -20, 20, -20, 20);
                    Tir.Collision_joueur(game.getPlayer1(), tirs_aliens_2, -20, 20, -20, 20);
                    Tir.Collision_joueur(game.getPlayer1(), tirs_joueurs_2, -20, 20, -20, 20);
                    Tir.Collision_joueur(game.getPlayer2(), tirs_joueurs_1, -20, 20, -20, 20);

                    //retirer si plus de vie
                    if (!aliens_1.getChildren().isEmpty()) {
                        Tir.supp(aliens_1);
                    }
                    if (!aliens_2.getChildren().isEmpty()) {
                        Tir.supp(aliens_2);
                    }
                    Tir.supp(tirs_joueurs_1);
                    Tir.supp(tirs_joueurs_2);
                    Tir.supp(tirs_aliens_1);
                    Tir.supp(tirs_aliens_2);
                    Tir.supp(blocks);

                    //affichage des vies du joueur et des blocks
                    vie_joueur_1.setX(player1.getLayoutX());
                    vie_joueur_1.setY(player1.getLayoutY());
                    vie_joueur_1.setText(player1.getAccessibleText());
                    vie_joueur_2.setX(player2.getLayoutX());
                    vie_joueur_2.setY(player2.getLayoutY());
                    vie_joueur_2.setText(player2.getAccessibleText());
                    Block.vie_blocks(blocks, vie_blocks);

                    // Affichage du chrono
                    //temps.setText(Float.toString((System.currentTimeMillis() - temps_debut - tempause) / 1000F));
                    temps.setText(Float.toString((System.currentTimeMillis() - temps_debut - game.getTempause()) / 1000F));

                    // Conditions de victoire
                    if (aliens_1.getChildren().isEmpty() && aliens_2.getChildren().isEmpty()) {   // GAGNE : Il n'y a plus d'aliens
                        End_of_game.endOfGame_2_joueurs(stage, 0,
                                (System.currentTimeMillis() - temps_debut - game.getTempause()) / 1000F,
                                0,
                                player,
                                game.getDifficulte(),
                                game.getURL_vaisseau(),
                                game.getURL_vaisseau_rev(),
                                game.getURL_alien(),
                                game.getURL_alien_r(),
                                game.getURL_tir1(),
                                game.getURL_tir2(),
                                game.getURL_tir_alien_up(),
                                game.getURL_tir_alien_down(),
                                game.getNetwork(),
                                game.getMonClientTCP(),
                                game.getMonServeur());
                        stop();
                    } else if (Integer.valueOf(player1.getAccessibleText()) <= 0) {  // PERDU : le joueur 1 est mort.
                        End_of_game.endOfGame_2_joueurs(stage, 1,
                                (System.currentTimeMillis() - temps_debut - game.getTempause()) / 1000F,
                                aliens_1.getChildren().size() + aliens_2.getChildren().size(),
                                player,
                                game.getDifficulte(),
                                game.getURL_vaisseau(),
                                game.getURL_vaisseau_rev(),
                                game.getURL_alien(),
                                game.getURL_alien_r(),
                                game.getURL_tir1(),
                                game.getURL_tir2(),
                                game.getURL_tir_alien_up(),
                                game.getURL_tir_alien_down(),
                                game.getNetwork(),
                                game.getMonClientTCP(),
                                game.getMonServeur());
                        stop();
                    } else if (Integer.valueOf(player2.getAccessibleText()) <= 0) {  // PERDU : le joueur 2 est mort.
                        End_of_game.endOfGame_2_joueurs(stage, 2,
                                (System.currentTimeMillis() - temps_debut - game.getTempause()) / 1000F,
                                aliens_1.getChildren().size() + aliens_2.getChildren().size(),
                                player,
                                game.getDifficulte(),
                                game.getURL_vaisseau(),
                                game.getURL_vaisseau_rev(),
                                game.getURL_alien(),
                                game.getURL_alien_r(),
                                game.getURL_tir1(),
                                game.getURL_tir2(),
                                game.getURL_tir_alien_up(),
                                game.getURL_tir_alien_down(),
                                game.getNetwork(),
                                game.getMonClientTCP(),
                                game.getMonServeur());
                        stop();
                    }
                    if (!aliens_1.getChildren().isEmpty()) {
                        if (Alien.test_fin_alien(aliens_1, 500, "DOWN")) {    // PERDU : les aliens du J1 ont atteint la Terre.
                            End_of_game.endOfGame_2_joueurs(stage, 3,
                                    (System.currentTimeMillis() - temps_debut - game.getTempause()) / 1000F,
                                    aliens_1.getChildren().size()+aliens_2.getChildren().size(),
                                    player,
                                    game.getDifficulte(),
                                    game.getURL_vaisseau(),
                                    game.getURL_vaisseau_rev(),
                                    game.getURL_alien(),
                                    game.getURL_alien_r(),
                                    game.getURL_tir1(),
                                    game.getURL_tir2(),
                                    game.getURL_tir_alien_up(),
                                    game.getURL_tir_alien_down(),
                                    game.getNetwork(),
                                    game.getMonClientTCP(),
                                    game.getMonServeur());

                            stop();
                        }
                    }
                    if (!aliens_2.getChildren().isEmpty()) {
                        if (Alien.test_fin_alien(aliens_2, 180, "UP")) {    // PERDU : les aliens du J2 ont atteint la Terre.
                            End_of_game.endOfGame_2_joueurs(stage, 4,
                                    (System.currentTimeMillis() - temps_debut - game.getTempause()) / 1000F,
                                    aliens_1.getChildren().size()+aliens_2.getChildren().size(),
                                    player,
                                    game.getDifficulte(),
                                    game.getURL_vaisseau(),
                                    game.getURL_vaisseau_rev(),
                                    game.getURL_alien(),
                                    game.getURL_alien_r(),
                                    game.getURL_tir1(),
                                    game.getURL_tir2(),
                                    game.getURL_tir_alien_up(),
                                    game.getURL_tir_alien_down(),
                                    game.getNetwork(),
                                    game.getMonClientTCP(),
                                    game.getMonServeur());
                            stop();
                        }
                    }
                }
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyListener);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyListener2);
        root.getChildren().addAll(player1, player2, tirs_joueurs_1, tirs_joueurs_2, tirs_aliens_1, tirs_aliens_2, aliens_1, aliens_2, blocks, vie_blocks,temps,vie_joueur_1,vie_joueur_2, niveau);
        loop.start();
        stage.setTitle("Space Invaders");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); // everything happens everywhere at once
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        player1.setLayoutX(game.getPlayer1_x() + game.getDir_p1() * (2d+game.getDifficulte()/5));
        player2.setLayoutX(game.getPlayer2_x() + game.getDir_p1() * (2d+game.getDifficulte()/5));
        /*
        for (int i = 0; i < aliens.getChildren().size(); i++) {
            aliens.getChildren().get(i).setLayoutX(game.getAliens1J_x().get(i));
            aliens.getChildren().get(i).setLayoutY(game.getAliens1J_y().get(i));
        }*/
    }
}
