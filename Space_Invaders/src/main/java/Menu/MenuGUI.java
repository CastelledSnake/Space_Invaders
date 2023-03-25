package Menu;

import Game.GameGUI;
import Game.Game;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère le menu d'entrée, et l'entrée en partie avec les "skins" adaptés
 */
public class MenuGUI implements PropertyChangeListener {

    private final String MainBackgroundURL="file:Space_Invaders/src/main/resources/Image_menu/Image_menu_1.jpg";
    private double screen_height=700;
    private double screen_width=1200;
    private Menu menu;
    private Stage stage;

    private String disp_alien_shot_URL_1J;
    private Image disp_alien_shot_img_1J;
    private ImageView disp_alien_shot_1J;

    private String disp_player_shot_URL_1J;
    private Image disp_player_shot_img_1J;
    private ImageView disp_player_shot_1J;

    private String disp_aliens_URL_1J;
    private Image disp_aliens_img_1J;
    private ImageView disp_aliens_1J;

    private String disp_ship_URL_1J;
    private Image disp_ship_img_1J;
    private ImageView disp_ship_1J;

    private String disp_alien_shot_URL_2J;
    private Image disp_alien_shot_img_2J;
    private ImageView disp_alien_shot_2J;

    private String disp_player_shot_URL_2J_1;
    private Image disp_player_shot_img_2J_1;
    private ImageView disp_player_shot_2J_1;

    private String disp_player_shot_URL_2J_2;
    private Image disp_player_shot_img_2J_2;
    private ImageView disp_player_shot_2J_2;

    private String disp_aliens_URL_2J;
    private Image disp_aliens_img_2J;
    private ImageView disp_aliens_2J;

    private String disp_ship_URL_2J_1;
    private Image disp_ship_img_2J_1;
    private ImageView disp_ship_2J_1;

    private String disp_ship_URL_2J_2;
    private Image disp_ship_img_2J_2;
    private ImageView disp_ship_2J_2;

    public MenuGUI(Menu unMenu, Stage unstage) {
        menu = unMenu;
        stage = unstage;
        menu.getPropertyChangeSupport().addPropertyChangeListener(this);

    }
    public Text text_func(double screen_width, double screen_height, String contenu, String police, FontWeight fontWeight, int taille, Color color, boolean alignement, int deplacement_x, int deplacement_y) {
        Text text = new Text(contenu);
        text.setLayoutX(deplacement_x+screen_width/2);
        text.setLayoutY(deplacement_y+screen_height/2);
        text.setFont(Font.font(police, fontWeight,taille));
        text.setFill(color);
        if (alignement) text.setTextAlignment(TextAlignment.CENTER);
        return text;
    }

    /**
     * Affiche une polygone en forme de flèche
     * @param screen_width largeur de l'écran
     * @param screen_height hauteur de l'écran
     * @param decalage_x abcisse du polygone
     * @param decalage_y ordonnée du polygone
     * @param sens sens de la flèche
     * @return le polygone
     */
    public Polygon Arrow(double screen_width, double screen_height, int decalage_x, int decalage_y, String sens) {
        Polygon triangle = new Polygon();
        if (sens.equals("R")) {
            triangle.getPoints().setAll(0d,0d,0d,20d,20d,10d);
        }
        else {
            triangle.getPoints().setAll(0d,0d,0d,20d,-20d,10d);
        }
        triangle.setFill(Color.LIMEGREEN);
        triangle.setLayoutX(decalage_x+(screen_width/2));
        triangle.setLayoutY(decalage_y+(screen_height/2));
        return triangle;
    }

    /**
     * Permet de modifier à l'écran les skins des différents objets possibles
     * @param e MouseEvent
     * @param nb_images Nombre d'images du type considéré
     * @param gauche Localisation où cliquer pour aller à l'image précédente
     * @param droite Localisation où cliquer pour aller à l'image suivante
     * @return le nouveau entier représentant le numéro de l'image choisie
     */
    public void Affichage_choix(MouseEvent e, int nb_images,
                                      ArrayList<Integer> gauche, ArrayList<Integer> droite, String identity) {
        if (e.getSceneX()>gauche.get(0)+(screen_width/2) && e.getSceneX()<gauche.get(1)+(screen_width/2)
                && e.getSceneY()>gauche.get(2)+screen_height/2 && e.getSceneY()<gauche.get(3)+screen_height/2) {
            if (identity.equals("alien_shot_1J")) {
                menu.lowerAlienTirSelector_1J(nb_images);
            } else if (identity.equals("player_shot_1J")) {
                menu.lowerPlayerTirSelector_1J(nb_images);
            } else if (identity.equals("alien_1J")) {
                menu.lowerAlienSelector_1J(nb_images);
            } else if (identity.equals("ship_1J")) {
                menu.lowerShipSelector_1J(nb_images);
            } else if (identity.equals("alien_shot_2J")) {
                menu.lowerAlienTirSelector_2J(nb_images);
            } else if (identity.equals("player_shot_2J_1")) {
                menu.lowerPlayerTirSelector_2J_1(nb_images);
            } else if (identity.equals("player_shot_2J_2")) {
                menu.lowerPlayerTirSelector_2J_2(nb_images);
            } else if (identity.equals("alien_2J")) {
                menu.lowerAlienSelector_2J(nb_images);
            } else if (identity.equals("ship_2J_1")) {
                menu.lowerShipSelector_2J_1(nb_images);
            } else if (identity.equals("ship_2J_2")) {
                menu.lowerShipSelector_2J_2(nb_images);
            }
        }

        if (e.getSceneX()>droite.get(0)+(screen_width/2) && e.getSceneX()<droite.get(1)+(screen_width/2)
                && e.getSceneY()>droite.get(2)+screen_height/2 && e.getSceneY()<droite.get(3)+screen_height/2) {
            if (identity.equals("alien_shot_1J")) {
                menu.raiseAlienTirSelector_1J(nb_images);
            } else if (identity.equals("player_shot_1J")) {
                menu.raisePlayerTirSelector_1J(nb_images);
            } else if (identity.equals("alien_1J")) {
                menu.raiseAlienSelector_1J(nb_images);
            } else if (identity.equals("ship_1J")) {
                menu.raiseShipSelector_1J(nb_images);
            } else if (identity.equals("alien_shot_2J")) {
                menu.raiseAlienTirSelector_2J(nb_images);
            } else if (identity.equals("player_shot_2J_1")) {
                menu.raisePlayerTirSelector_2J_1(nb_images);
            } else if (identity.equals("player_shot_2J_2")) {
                menu.raisePlayerTirSelector_2J_2(nb_images);
            } else if (identity.equals("alien_2J")) {
                menu.raiseAlienSelector_2J(nb_images);
            } else if (identity.equals("ship_2J_1")) {
                menu.raiseShipSelector_2J_1(nb_images);
            } else if (identity.equals("ship_2J_2")) {
                menu.raiseShipSelector_2J_2(nb_images);
            }
        }
    }

    /**
     * Sur l'écran principal, permet de rediriger vers les autres écrans
     * @param e MouseEvent
     * @param stage
     * @param scene1 Jeu 1 Joueur
     * @param scene3 Credits
     * @param scene4 Selection multijoueur
     * @throws IOException
     */
    public static void mainMenuSelection(MouseEvent e, Stage stage, Scene scene1, Scene scene3, Scene scene4)
            throws IOException {
        if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>420 && e.getSceneY()<450) {
            stage.setScene(scene1);
        }
        else if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>490 && e.getSceneY()<530) {
            stage.setScene(scene4);
        }
        else if (e.getSceneX()>550 && e.getSceneX()<670 && e.getSceneY()>580 && e.getSceneY()<600) {
            stage.setScene(scene3);
        }
    }

    /**
     * Sur la page de crédit, permet de ramener à la page principale
     * @param e MouseEvent
     * @param stage
     * @param scene0 Page principale
     * @throws IOException
     */
    public void creditsSelection(MouseEvent e, Stage stage, Scene scene0) throws IOException {
        if (e.getSceneX()>-100d+screen_width && e.getSceneX()<-15d+screen_width
                && e.getSceneY()>-40d+screen_height && e.getSceneY()<-20d+screen_height) {
            stage.setScene(scene0);
        }
    }

    /**
     * Gère les propositions de skins pour le jeu à 1 joueur et les redirections vers d'autres pages
     * @param e MouseEvent
     * @param stage Stage
     * @param scene0 Page d'accueil
     * @throws IOException
     */
    public void P1optionsMenuSelection(MouseEvent e, Stage stage, Scene scene0) throws IOException {

        ArrayList<Integer> alien_tir_gauche=new ArrayList<>(List.of(-80,-60,-20,0));
        ArrayList<Integer> alien_tir_droite=new ArrayList<>(List.of(110,130,-20,0));
        Affichage_choix(e,6,alien_tir_gauche,alien_tir_droite, "alien_shot_1J");

        ArrayList<Integer> joueur_tir_gauche=new ArrayList<>(List.of(-80,-60,50,70));
        ArrayList<Integer> joueur_tir_droite=new ArrayList<>(List.of(110,130,50,70));
        Affichage_choix(e,6,joueur_tir_gauche,joueur_tir_droite, "player_shot_1J");

        ArrayList<Integer> alien_gauche=new ArrayList<>(List.of(-80,-60,125,145));
        ArrayList<Integer> alien_droite=new ArrayList<>(List.of(110,130,125,145));
        Affichage_choix(e,4,alien_gauche,alien_droite, "alien_1J");

        ArrayList<Integer> ship_gauche=new ArrayList<>(List.of(-80,-60,200,215));
        ArrayList<Integer> ship_droite=new ArrayList<>(List.of(110,130,200,215));
        Affichage_choix(e,3,ship_gauche,ship_droite, "ship_1J");

        //Lancement du jeu 1 joueur
        if (e.getSceneX()>-5d+(screen_width/2) && e.getSceneX()<60d+(screen_width/2)
                && e.getSceneY()>270d+screen_height/2 && e.getSceneY()<300d+screen_height/2) {
            Game game = new Game();
            GameGUI gameGUI = new GameGUI(game);
            game.setURL_vaisseau("file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_"
                    +menu.getShipSelector_1J()+".png");
            game.setURL_alien("file:Space_Invaders/src/main/resources/Image_alien/Image_alien_"
                    +menu.getAlienSelector_1J()+".png");
            game.setURL_tir_vaisseau("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                    +menu.getPlayerTirSelector_1J()+"_u.png");
            game.setURL_tir_alien("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                    +menu.getAlienTirSelector_1J()+"_d.png");
            gameGUI.game_1_joueur(stage);
        }

        //retour à l'écran d'accueil
        if (e.getSceneX()>-100d+screen_width && e.getSceneX()<-15d+screen_width
                && e.getSceneY()>-40d+screen_height && e.getSceneY()<-20d+screen_height) {
            stage.setScene(scene0);
        }
    }

    /**
     * Gère les propositions de skins pour le jeu à 2 joueurs et les redirections vers d'autres pages
     * @param e MouseEvent
     * @param stage Stage
     * @param scene0 Page d'accueil
     * @throws IOException
     */
    public void P2optionsMenuSelection(MouseEvent e, Stage stage, Scene scene0) throws IOException {

        ArrayList<Integer> alien_tir_gauche=new ArrayList<>(List.of(-90,-70,-205,-190));
        ArrayList<Integer> alien_tir_droite=new ArrayList<>(List.of(120,140,-205,-190));
        Affichage_choix(e,6,alien_tir_gauche,alien_tir_droite, "alien_shot_2J");

        ArrayList<Integer> player_tir_1_gauche=new ArrayList<>(List.of(-90,-70,-130,-115));
        ArrayList<Integer> player_tir_1_droite=new ArrayList<>(List.of(120,140,-130,-115));
        Affichage_choix(e,6,player_tir_1_gauche,player_tir_1_droite, "player_shot_2J_1");

        ArrayList<Integer> player_tir_2_gauche=new ArrayList<>(List.of(-90,-70,-55,-40));
        ArrayList<Integer> player_tir_2_droite=new ArrayList<>(List.of(120,140,-55,-40));
        Affichage_choix(e,6,player_tir_2_gauche,player_tir_2_droite, "player_shot_2J_2");

        ArrayList<Integer> alien_gauche = new ArrayList<>(List.of(-90,-70,20,55));
        ArrayList<Integer> alien_droite = new ArrayList<>(List.of(120,140,20,55));
        Affichage_choix(e,4,alien_gauche,alien_droite, "alien_2J");

        ArrayList<Integer> ship_1_gauche=new ArrayList<>(List.of(-90,-70,90,105));
        ArrayList<Integer> ship_1_droite=new ArrayList<>(List.of(120,140,90,105));
        Affichage_choix(e,3,ship_1_gauche,ship_1_droite, "ship_2J_1");

        ArrayList<Integer> ship_2_gauche=new ArrayList<>(List.of(-90,-70,160,175));
        ArrayList<Integer> ship_2_droite=new ArrayList<>(List.of(120,140,160,175));
        Affichage_choix(e,3,ship_2_gauche,ship_2_droite, "ship_2J_2");


        //lancement du jeu 2 joueurs
        if (e.getSceneX()>-5d+(screen_width/2) && e.getSceneX()<60d+(screen_width/2)
                && e.getSceneY()>230d+screen_height/2 && e.getSceneY()<260d+screen_height/2) {
            Game game = new Game();
            GameGUI gameGUI = new GameGUI(game);
            game.setURL_vaisseau("file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_"
                    +menu.getShipSelector_2J_1()+".png");
            game.setURL_vaisseau_rev("file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_"
                    +menu.getShipSelector_2J_2()+"_r.png");
            game.setURL_alien("file:Space_Invaders/src/main/resources/Image_alien/Image_alien_"
                    +menu.getAlienSelector_2J()+".png");
            game.setURL_alien_r("file:Space_Invaders/src/main/resources/Image_alien/Image_alien_"
                    +menu.getAlienSelector_2J()+"_r.png");
            game.setURL_tir_alien_up("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                    +menu.getAlienTirSelector_2J()+"_u.png");
            game.setURL_tir_alien_down("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                    +menu.getAlienTirSelector_2J()+"_d.png");
            game.setURL_tir1("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                    +menu.getPlayerTirSelector_2J_1()+"_u.png");
            game.setURL_tir2("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                    +menu.getPlayerTirSelector_2J_2()+"_d.png");
            game.setNetwork(menu.getNetwork());
            gameGUI.game_2_joueurs(stage);
            if (menu.getNetwork()==true){
                game.setMonClientTCP(menu.getMonClientTCP());
                game.setMonServeur(menu.getMonServeur());
                game.getMonClientTCP().connecterAuServeur();
            }
        }

        //retour à la page d'accueil
        if (e.getSceneX()>-100d+screen_width && e.getSceneX()<-15d+screen_width
                && e.getSceneY()>-40d+screen_height && e.getSceneY()<-20d+screen_height) {
            stage.setScene(scene0);
        }
    }

    public void multimodeSelection(MouseEvent e, Stage stage, Scene scene0, Scene scene2) throws IOException {
        if (e.getSceneX()>-100d+screen_width && e.getSceneX()<-15d+screen_width
                && e.getSceneY()>-40d+screen_height && e.getSceneY()<-20d+screen_height) {
            stage.setScene(scene0);
        }
        if (e.getSceneX()>520d && e.getSceneX()<730d && e.getSceneY()>325d && e.getSceneY()<355d) {
            stage.setScene(scene2);
        }
        if (e.getSceneX()>545d && e.getSceneX()<705d && e.getSceneY()>400d && e.getSceneY()<425d) {
            menu.getMonClientTCP().connecterAuServeur();
            menu.setNetwork(true);
            stage.setScene(scene2);
        }
    }

    //Initialisation des différentes pages
    public void initGUI() {

        //------------------------------scene 0 - Main Menu----------------------------------

        BorderPane root0 = new BorderPane();
        Image main_background = new Image(MainBackgroundURL,1200,700,false,false);
        try {
            root0.setBackground(new Background((new BackgroundImage(main_background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT))));
        }
        catch ( Exception e) {
            System.out.println("Impossible d'afficher le fond");
            //On n'affiche pas de fond
        }
        Scene scene0 = new Scene(root0,screen_width,screen_height,Color.BLACK);

        Text title = text_func(screen_width,
                screen_height,
                "Space Invaders",
                "Impact",
                FontWeight.BOLD,
                100,
                Color.LIMEGREEN,
                false,
                -310,
                -100);

        Text options = text_func(screen_width,
                screen_height,
                "Single Player\n\nTwo Players\n\nCredits",
                "Impact",
                FontWeight.NORMAL,
                30,
                Color.LIMEGREEN,
                true,
                -60,
                100);

        root0.getChildren().addAll(title,options);



        //--------------------------------scene1 - Options 1 joueur
        BorderPane root1 = new BorderPane();

        //Initialisation des images
        disp_alien_shot_URL_1J ="file:Space_Invaders/src/main/resources/Image_tir/Image_tir_1_d.png";
        disp_alien_shot_img_1J = new Image(disp_alien_shot_URL_1J);
        disp_alien_shot_1J = new ImageView(disp_alien_shot_img_1J);
        disp_alien_shot_1J.setLayoutX(400);
        disp_alien_shot_1J.setLayoutY(300);

        disp_player_shot_URL_1J ="file:Space_Invaders/src/main/resources/Image_tir/Image_tir_1_u.png";
        disp_player_shot_img_1J = new Image(disp_player_shot_URL_1J);
        disp_player_shot_1J = new ImageView(disp_player_shot_img_1J);
        disp_player_shot_1J.setLayoutX(800);
        disp_player_shot_1J.setLayoutY(350);

        disp_aliens_URL_1J ="file:Space_Invaders/src/main/resources/Image_alien/Image_alien_1.png";
        disp_aliens_img_1J = new Image(disp_aliens_URL_1J);
        disp_aliens_1J = new ImageView(disp_aliens_img_1J);
        disp_aliens_1J.setLayoutX(385);
        disp_aliens_1J.setLayoutY(450);
        disp_aliens_1J.setFitWidth(100);
        disp_aliens_1J.setPreserveRatio(true);

        disp_ship_URL_1J ="file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_1.png";
        disp_ship_img_1J = new Image(disp_ship_URL_1J);
        disp_ship_1J= new ImageView(disp_ship_img_1J);
        disp_ship_1J.setLayoutX(775);
        disp_ship_1J.setLayoutY(500);
        disp_ship_1J.setFitWidth(100);
        disp_ship_1J.setPreserveRatio(true);

        try {
            root1.setBackground(new Background((new BackgroundImage(main_background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT))));
        }
        catch ( Exception e) {
            System.out.println("Impossible d'afficher le fond");
            //On n'affiche pas de fond
        }
        Scene scene1 = new Scene(root1,screen_width,screen_height,Color.BLACK);
        Text options_title_1J = text_func(screen_width,
                screen_height,
                "Options",
                "Impact",
                FontWeight.BOLD,
                100,
                Color.LIMEGREEN,
                true,
                -135,
                -100);


        Text selection_fields_1J = text_func(
                screen_width,
                screen_height,
                "Alien shots\n\nShip shots\n\nAliens\n\nShip\n\nStart",
                "Impact",
                FontWeight.NORMAL,
                30,
                Color.LIMEGREEN,
                true,
                -50,
                0);



        Text options_return_1J = text_func(screen_width,
                screen_height,
                "Return",
                "Impact",
                FontWeight.BOLD,
                30,
                Color.LIMEGREEN,
                true,
                500,
                330);

        Group GroupArrow_1J=new Group();

        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,-60,-20,"L"));
        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,110,-20,"R"));
        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,-60,50,"L"));
        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,110,50,"R"));
        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,-60,125,"L"));
        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,110,125,"R"));
        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,-60,200,"L"));
        GroupArrow_1J.getChildren().add(Arrow(screen_width,screen_height,110,200,"R"));

        root1.getChildren().addAll(options_title_1J,options_return_1J,selection_fields_1J,
                disp_alien_shot_1J,disp_player_shot_1J, disp_aliens_1J,disp_ship_1J,GroupArrow_1J);



        //--------------------------------scene2 - Options 2 joueurs
        BorderPane root2 = new BorderPane();

        //Initialisation des images
        disp_alien_shot_URL_2J ="file:Space_Invaders/src/main/resources/Image_tir/Image_tir_1_d.png";
        disp_alien_shot_img_2J = new Image(disp_alien_shot_URL_2J);
        disp_alien_shot_2J = new ImageView(disp_alien_shot_img_2J);
        disp_alien_shot_2J.setLayoutX(400);
        disp_alien_shot_2J.setLayoutY(90);

        disp_player_shot_URL_2J_1 ="file:Space_Invaders/src/main/resources/Image_tir/Image_tir_1_u.png";
        disp_player_shot_img_2J_1 = new Image(disp_player_shot_URL_2J_1);
        disp_player_shot_2J_1 = new ImageView(disp_player_shot_img_2J_1);
        disp_player_shot_2J_1.setLayoutX(800);
        disp_player_shot_2J_1.setLayoutY(170);

        disp_player_shot_URL_2J_2 ="file:Space_Invaders/src/main/resources/Image_tir/Image_tir_1_d.png";
        disp_player_shot_img_2J_2 = new Image(disp_player_shot_URL_2J_2);
        disp_player_shot_2J_2 = new ImageView(disp_player_shot_img_2J_2);
        disp_player_shot_2J_2.setLayoutX(400);
        disp_player_shot_2J_2.setLayoutY(240);

        disp_aliens_URL_2J ="file:Space_Invaders/src/main/resources/Image_alien/Image_alien_1.png";
        disp_aliens_img_2J = new Image(disp_aliens_URL_2J);
        disp_aliens_2J = new ImageView(disp_aliens_img_2J);
        disp_aliens_2J.setLayoutX(780);
        disp_aliens_2J.setLayoutY(355);
        disp_aliens_2J.setFitWidth(100);
        disp_aliens_2J.setPreserveRatio(true);

        disp_ship_URL_2J_1 ="file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_1.png";
        disp_ship_img_2J_1 = new Image(disp_ship_URL_2J_1);
        disp_ship_2J_1= new ImageView(disp_ship_img_2J_1);
        disp_ship_2J_1.setLayoutX(395);
        disp_ship_2J_1.setLayoutY(400);
        disp_ship_2J_1.setFitWidth(80);
        disp_ship_2J_1.setPreserveRatio(true);

        disp_ship_URL_2J_2 ="file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_1_r.png";
        disp_ship_img_2J_2 = new Image(disp_ship_URL_2J_2);
        disp_ship_2J_2= new ImageView(disp_ship_img_2J_2);
        disp_ship_2J_2.setLayoutX(780);
        disp_ship_2J_2.setLayoutY(470);
        disp_ship_2J_2.setFitWidth(80);
        disp_ship_2J_2.setPreserveRatio(true);

        try {
            root2.setBackground(new Background((new BackgroundImage(main_background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT))));
        }
        catch ( Exception e) {
            System.out.println("Impossible d'afficher le fond");
            //On n'affiche pas de fond
        }
        Scene scene2 = new Scene(root2,screen_width,screen_height,Color.BLACK);
        Text options_title_2J = text_func(screen_width,
                screen_height,
                "Options",
                "Impact",
                FontWeight.BOLD,
                100,
                Color.LIMEGREEN,
                true,
                -135,
                -250);


        Text selection_fields_2J = text_func(
                screen_width,
                screen_height,
                "Alien shots\n\nShip 2 shots\n\nShip 1 shots\n\nAliens\n\nShip 2\n\n Ship 1\n\nStart",
                "Impact",
                FontWeight.NORMAL,
                30,
                Color.LIMEGREEN,
                true,
                -60,
                -180);



        Text options_return_2J = text_func(screen_width,
                screen_height,
                "Return",
                "Impact",
                FontWeight.BOLD,
                30,
                Color.LIMEGREEN,
                true,
                500,
                330);

        Group GroupArrow_2J=new Group();

        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,-70,-205,"L"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,120,-205,"R"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,-70,-130,"L"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,120,-130,"R"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,-70,-55,"L"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,120,-55,"R"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,-70,20,"L"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,120,20,"R"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,-70,90,"L"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,120,90,"R"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,-70,160,"L"));
        GroupArrow_2J.getChildren().add(Arrow(screen_width,screen_height,120,160,"R"));


        root2.getChildren().addAll(options_title_2J,options_return_2J,selection_fields_2J,disp_alien_shot_2J,
                disp_player_shot_2J_1, disp_player_shot_2J_2,disp_aliens_2J,disp_ship_2J_1,disp_ship_2J_2,
                GroupArrow_2J);




        //------------------------------------scene3 - Credits
        BorderPane root3 = new BorderPane();
        Image credits_background = new Image(MainBackgroundURL,1200,700,false,false);
        try {
            root3.setBackground(new Background((new BackgroundImage(credits_background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT))));
        }
        catch ( Exception e) {
            System.out.println("Impossible d'afficher le fond");
            //On n'affiche pas de fond
        }
        Scene scene3 = new Scene(root3,screen_width,screen_height,Color.BLACK);
        Text credits_title = text_func(screen_width,
                screen_height,
                "Credits",
                "Impact",
                FontWeight.BOLD,
                100,
                Color.LIMEGREEN,
                true,
                -135,
                -100);


        Text credits_list = text_func(screen_width,
                screen_height,
                "Vincent Castellan\n\nNicolas Dias\n\nNicolas Le Roux",
                "Impact",
                FontWeight.BOLD,
                30,
                Color.LIMEGREEN,
                true,
                -80,
                0);


        Text credits_return = text_func(screen_width,
                screen_height,
                "Return",
                "Impact",
                FontWeight.BOLD,
                30,
                Color.LIMEGREEN,
                true,
                500,
                330);

        root3.getChildren().addAll(credits_title,credits_list,credits_return);

        //------------------------------------scene4 - Multiplayer Mode Selection
        BorderPane root4 = new BorderPane();
        Image multi_mode_background = new Image(MainBackgroundURL,1200,700,false,false);
        try {
            root4.setBackground(new Background((new BackgroundImage(multi_mode_background,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT))));
        }
        catch ( Exception e) {
            System.out.println("Impossible d'afficher le fond");
            //On n'affiche pas de fond
        }

        Scene scene4 = new Scene(root4,screen_width,screen_height,Color.BLACK);
        Text multi_mode_title = text_func(screen_width,
                screen_height,
                "Choose your mode",
                "Impact",
                FontWeight.BOLD,
                100,
                Color.LIMEGREEN,
                true,
                -375,
                -100);


        Text modes_list = text_func(screen_width,
                screen_height,
                "Shared keyboard\n\nNetwork play",
                "Impact",
                FontWeight.BOLD,
                30,
                Color.LIMEGREEN,
                true,
                -80,
                0);


        Text multi_mode_return = text_func(screen_width,
                screen_height,
                "Return",
                "Impact",
                FontWeight.BOLD,
                30,
                Color.LIMEGREEN,
                true,
                500,
                330);

        root4.getChildren().addAll(multi_mode_title,modes_list,multi_mode_return);

        //Initialisation des MouseListener de toutes les pages

        //Page d'accueil
        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try
                {
                    mainMenuSelection(e, stage, scene1, scene3, scene4);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        //Options 1J
        EventHandler<MouseEvent> options_1J_Mouse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try
                {
                    P1optionsMenuSelection(e,stage,scene0);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        //Options 2J
        EventHandler<MouseEvent> options_2J_Mouse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try
                {
                    P2optionsMenuSelection(e,stage,scene0);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        //Credits
        EventHandler<MouseEvent> creditsMouse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try
                {
                    creditsSelection(e,stage,scene0);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        //Multiplayer Mode Selection
        EventHandler<MouseEvent> multimodeMouse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try
                {
                    multimodeSelection(e,stage,scene0,scene2);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        scene0.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseListener);
        scene1.addEventHandler(MouseEvent.MOUSE_CLICKED,options_1J_Mouse);
        scene2.addEventHandler(MouseEvent.MOUSE_CLICKED,options_2J_Mouse);
        scene3.addEventHandler(MouseEvent.MOUSE_CLICKED,creditsMouse);
        scene4.addEventHandler(MouseEvent.MOUSE_CLICKED,multimodeMouse);


        stage.setTitle("Space Invaders");
        stage.setScene(scene0);
        stage.setResizable(false);
        stage.show(); // everything happens everywhere at once
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        disp_alien_shot_1J.setImage(new Image("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                +menu.getAlienTirSelector_1J()+"_d.png"));
        disp_player_shot_1J.setImage(new Image("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                +menu.getPlayerTirSelector_1J()+"_u.png"));
        disp_aliens_1J.setImage(new Image("file:Space_Invaders/src/main/resources/Image_alien/Image_alien_"
                +menu.getAlienSelector_1J()+".png"));
        disp_ship_1J.setImage(new Image("file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_"
                +menu.getShipSelector_1J()+".png"));
        disp_alien_shot_2J.setImage(new Image("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                +menu.getAlienTirSelector_2J()+"_d.png"));
        disp_player_shot_2J_1.setImage(new Image("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                +menu.getPlayerTirSelector_2J_1()+"_u.png"));
        disp_player_shot_2J_2.setImage(new Image("file:Space_Invaders/src/main/resources/Image_tir/Image_tir_"
                +menu.getPlayerTirSelector_2J_2()+"_d.png"));
        disp_aliens_2J.setImage(new Image("file:Space_Invaders/src/main/resources/Image_alien/Image_alien_"
                +menu.getAlienSelector_2J()+".png"));
        disp_ship_2J_1.setImage(new Image("file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_"
                +menu.getShipSelector_2J_1()+".png"));
        disp_ship_2J_2.setImage(new Image("file:Space_Invaders/src/main/resources/Image_vaisseau/Image_vaisseau_"
                +menu.getShipSelector_2J_2()+"_r.png"));
    }
}