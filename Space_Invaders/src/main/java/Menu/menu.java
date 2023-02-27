package Menu;

import Game.game;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère le menu d'entrée, et l'entrée en partie avec les "skins" adaptés
 */
public class menu {

    private static final String MainBackgroundURL="file:src/main/resources/Image_menu/Image_menu_1.jpg";

    //représente les "skins" des objets du jeu
    private static int AlienTirSelector_1J;
    private static int PlayerTirSelector_1J;
    private static int AlienSelector_1J;
    private static int ShipSelector_1J;


    private static int AlienTirSelector_2J;
    private static int PlayerTirSelector_2J_1;
    private static int PlayerTirSelector_2J_2;
    private static int AlienSelector_2J;
    private static int ShipSelector_2J_1;
    private static int ShipSelector_2J_2;

    private static double screen_height=700;
    private static double screen_width=1200;

    /**
     * Permet d'afficher un texte à l'écran
     * @param screen_width largeur de l'écran
     * @param screen_height hauteur de l'écran
     * @param contenu texte
     * @param police police du texte
     * @param fontWeight texte en gras, normal, etc
     * @param taille taille du texte
     * @param color couleur du texte
     * @param alignement centré ou non
     * @param deplacement_x ordonnée du texte
     * @param deplacement_y abcisse du texte
     * @return l'objet texte
     */
    public static Text text_func(double screen_width, double screen_height, String contenu, String police, FontWeight fontWeight, int taille, Color color, boolean alignement, int deplacement_x, int deplacement_y) {
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
     * @param décalage_x abcisse du polygone
     * @param décalage_y ordonnée du polygone
     * @param sens sens de la flèche
     * @return le polygone
     */
    public static Polygon Arrow(double screen_width, double screen_height, int décalage_x, int décalage_y, String sens) {
        Polygon triangle = new Polygon();
        if (sens.equals("R")) {
            triangle.getPoints().setAll(0d,0d,0d,20d,20d,10d);
        }
        else {
            triangle.getPoints().setAll(0d,0d,0d,20d,-20d,10d);
        }
        triangle.setFill(Color.LIMEGREEN);
        triangle.setLayoutX(décalage_x+(screen_width/2));
        triangle.setLayoutY(décalage_y+(screen_height/2));
        return triangle;
    }

    /**
     * Permet de modifier à l'écran les skins des différents objets possibles
     * @param e MouseEvent
     * @param disp Image potentiellement à modifier
     * @param nb_images Nombre d'images du type considéré
     * @param gauche Localisation où cliquer pour aller à l'image précédente
     * @param droite Localisation où cliquer pour aller à l'image suivante
     * @param deb_adresse adresse relative de l'image avant l'entier représentatif
     * @param fin_adresse adresse relative de l'image après l'entier représentatif
     * @param Selector entier représentant le numéro de l'image choisie
     * @return le nouveau entier représentant le numéro de l'image choisie
     */
    public static int Affichage_choix(MouseEvent e, ImageView disp, int nb_images,
                                       ArrayList<Integer> gauche, ArrayList<Integer> droite,
                                       String deb_adresse, String fin_adresse,
                                      int Selector) {


        if (e.getSceneX()>gauche.get(0)+(screen_width/2) && e.getSceneX()<gauche.get(1)+(screen_width/2)
                && e.getSceneY()>gauche.get(2)+screen_height/2 && e.getSceneY()<gauche.get(3)+screen_height/2) {
            if (Selector!=1) {
                String URL = deb_adresse+(Selector-1)+fin_adresse;
                Image newImage = new Image(URL);
                if (newImage.isError()) System.out.println("erreur dans le chargement de l'image : "+ URL);
                disp.setImage(newImage);
                Selector--;
            }
            else {
                String URL = deb_adresse+Integer.toString(nb_images)+fin_adresse;
                Image newImage = new Image(URL);
                if (newImage.isError()) System.out.println("erreur dans le chargement de l'image : "+ URL);
                disp.setImage(newImage);
                Selector=nb_images;
            }
        }

        if (e.getSceneX()>droite.get(0)+(screen_width/2) && e.getSceneX()<droite.get(1)+(screen_width/2)
                && e.getSceneY()>droite.get(2)+screen_height/2 && e.getSceneY()<droite.get(3)+screen_height/2) {
            if (Selector!=nb_images) {
                String URL = deb_adresse+(Selector+1)+fin_adresse;
                Image newImage = new Image(URL);
                if (newImage.isError()) System.out.println("erreur dans le chargement de l'image : "+ URL);
                disp.setImage(newImage);
                Selector++;
            }
            else {
                String URL = deb_adresse+"1"+fin_adresse;
                Image newImage = new Image(URL);
                if (newImage.isError()) System.out.println("erreur dans le chargement de l'image : "+ URL);
                disp.setImage(newImage);
                Selector=1;
            }
        }
        return(Selector);
    }

    /**
     * Sur l'écran principal, permet de rediriger vers les autres écrans
     * @param e MouseEvent
     * @param stage
     * @param scene1 Jeu 1 Joueur
     * @param scene2 Jeu 2 Joueurs
     * @param scene3 Credits
     * @throws IOException
     */
    public static void mainMenuSelection(MouseEvent e, Stage stage, Scene scene1, Scene scene2, Scene scene3) throws IOException {
        if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>420 && e.getSceneY()<450) {
            stage.setScene(scene1);
        }
        else if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>490 && e.getSceneY()<530) {
            stage.setScene(scene2);
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
    public static void creditsSelection(MouseEvent e, Stage stage, Scene scene0) throws IOException {
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
     * @param disp_alien_shot Image du tir alien modifiable
     * @param disp_player_shot Image du tir du joueur modifiable
     * @param disp_alien Image de l'alien modifiable
     * @param disp_ship Image du vaisseau modifiable
     * @throws IOException
     */
    public static void P1optionsMenuSelection(MouseEvent e, Stage stage, Scene scene0,
                                              ImageView disp_alien_shot, ImageView disp_player_shot,
                                              ImageView disp_alien, ImageView disp_ship) throws IOException {

        ArrayList<Integer> alien_tir_gauche=new ArrayList<>(List.of(-80,-60,-20,0));
        ArrayList<Integer> alien_tir_droite=new ArrayList<>(List.of(110,130,-20,0));
        AlienTirSelector_1J=Affichage_choix(e,disp_alien_shot,6,alien_tir_gauche,alien_tir_droite,
                "file:src/main/resources/Image_tir/Image_tir_","_d.png",
                AlienTirSelector_1J);

        ArrayList<Integer> joueur_tir_gauche=new ArrayList<>(List.of(-80,-60,50,70));
        ArrayList<Integer> joueur_tir_droite=new ArrayList<>(List.of(110,130,50,70));
        PlayerTirSelector_1J=Affichage_choix(e,disp_player_shot,6,joueur_tir_gauche,joueur_tir_droite,
                "file:src/main/resources/Image_tir/Image_tir_","_u.png",
                PlayerTirSelector_1J);

        ArrayList<Integer> alien_gauche=new ArrayList<>(List.of(-80,-60,125,145));
        ArrayList<Integer> alien_droite=new ArrayList<>(List.of(110,130,125,145));
        AlienSelector_1J=Affichage_choix(e,disp_alien,4,alien_gauche,alien_droite,
                "file:src/main/resources/Image_alien/Image_alien_",".png",
                AlienSelector_1J);

        ArrayList<Integer> ship_gauche=new ArrayList<>(List.of(-80,-60,200,215));
        ArrayList<Integer> ship_droite=new ArrayList<>(List.of(110,130,200,215));
        ShipSelector_1J=Affichage_choix(e,disp_ship,3,ship_gauche,ship_droite,
                "file:src/main/resources/Image_vaisseau/Image_vaisseau_",".png",
                ShipSelector_1J);

        //Lancement du jeu 1 joueur
        if (e.getSceneX()>-5d+(screen_width/2) && e.getSceneX()<60d+(screen_width/2)
                && e.getSceneY()>270d+screen_height/2 && e.getSceneY()<300d+screen_height/2) {
            game.game_1_joueur(stage,1,
                    "file:src/main/resources/Image_vaisseau/Image_vaisseau_"+Integer.toString(ShipSelector_1J)+".png",
                    "file:src/main/resources/Image_alien/Image_alien_"+Integer.toString(AlienSelector_1J)+".png",
                    "file:src/main/resources/Image_tir/Image_tir_"+Integer.toString(PlayerTirSelector_1J)+"_u.png",
                    "file:src/main/resources/Image_tir/Image_tir_"+Integer.toString(AlienTirSelector_1J)+"_d.png"
            );
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
     * @param disp_alien_shot Image du tir d'aliens modifiable
     * @param disp_player_shot_1 Image du tir du joueur 1 modifiable
     * @param disp_player_shot_2 Image du tir du joueur 2 modifiable
     * @param disp_alien Image de l'alien modifiable
     * @param disp_ship1 Image du vaisseau 1 modifiable
     * @param disp_ship2 Image du vaisseau 2 modifiable
     * @throws IOException
     */
    public static void P2optionsMenuSelection(MouseEvent e, Stage stage, Scene scene0,
                                              ImageView disp_alien_shot, ImageView disp_player_shot_1,
                                              ImageView disp_player_shot_2, ImageView disp_alien,
                                              ImageView disp_ship1, ImageView disp_ship2) throws IOException {

        ArrayList<Integer> alien_tir_gauche=new ArrayList<>(List.of(-90,-70,-205,-190));
        ArrayList<Integer> alien_tir_droite=new ArrayList<>(List.of(120,140,-205,-190));
        AlienTirSelector_2J=Affichage_choix(e,disp_alien_shot,6,alien_tir_gauche,alien_tir_droite,
                "file:src/main/resources/Image_tir/Image_tir_","_d.png",
                AlienTirSelector_2J);

        ArrayList<Integer> player_tir_1_gauche=new ArrayList<>(List.of(-90,-70,-130,-115));
        ArrayList<Integer> player_tir_1_droite=new ArrayList<>(List.of(120,140,-130,-115));
        PlayerTirSelector_2J_1=Affichage_choix(e,disp_player_shot_1,6,player_tir_1_gauche,player_tir_1_droite,
                "file:src/main/resources/Image_tir/Image_tir_","_u.png",
                PlayerTirSelector_2J_1);

        ArrayList<Integer> player_tir_2_gauche=new ArrayList<>(List.of(-90,-70,-55,-40));
        ArrayList<Integer> player_tir_2_droite=new ArrayList<>(List.of(120,140,-55,-40));
        PlayerTirSelector_2J_2=Affichage_choix(e,disp_player_shot_2,6,player_tir_2_gauche,player_tir_2_droite,
                "file:src/main/resources/Image_tir/Image_tir_","_d.png",
                PlayerTirSelector_2J_2);

        ArrayList<Integer> alien_gauche = new ArrayList<>(List.of(-90,-70,20,55));
        ArrayList<Integer> alien_droite = new ArrayList<>(List.of(120,140,20,55));
        AlienSelector_2J=Affichage_choix(e,disp_alien,4,alien_gauche,alien_droite,
                "file:src/main/resources/Image_alien/Image_alien_",".png",
                AlienSelector_2J);

        ArrayList<Integer> ship_1_gauche=new ArrayList<>(List.of(-90,-70,90,105));
        ArrayList<Integer> ship_1_droite=new ArrayList<>(List.of(120,140,90,105));
        ShipSelector_2J_1=Affichage_choix(e,disp_ship1,3,ship_1_gauche,ship_1_droite,
                "file:src/main/resources/Image_vaisseau/Image_vaisseau_",".png",
                ShipSelector_2J_1);

        ArrayList<Integer> ship_2_gauche=new ArrayList<>(List.of(-90,-70,160,175));
        ArrayList<Integer> ship_2_droite=new ArrayList<>(List.of(120,140,160,175));
        ShipSelector_2J_2=Affichage_choix(e,disp_ship2,3,ship_2_gauche,ship_2_droite,
                "file:src/main/resources/Image_vaisseau/Image_vaisseau_","_r.png",
                ShipSelector_2J_2);


        //lancement du jeu 2 joueurs
        if (e.getSceneX()>-5d+(screen_width/2) && e.getSceneX()<60d+(screen_width/2)
                && e.getSceneY()>230d+screen_height/2 && e.getSceneY()<260d+screen_height/2) {
            game.game_2_joueurs(stage,1,
                    "file:src/main/resources/Image_vaisseau/Image_vaisseau_"+Integer.toString(ShipSelector_2J_1)+".png",
                    "file:src/main/resources/Image_vaisseau/Image_vaisseau_"+Integer.toString(ShipSelector_2J_2)+"_r.png",
                    "file:src/main/resources/Image_alien/Image_alien_"+Integer.toString(AlienSelector_2J)+".png",
                    "file:src/main/resources/Image_alien/Image_alien_"+Integer.toString(AlienSelector_2J)+"_r.png",
                    "file:src/main/resources/Image_tir/Image_tir_"+Integer.toString(AlienTirSelector_2J)+"_u.png",
                    "file:src/main/resources/Image_tir/Image_tir_"+Integer.toString(AlienTirSelector_2J)+"_d.png",
                    "file:src/main/resources/Image_tir/Image_tir_"+Integer.toString(PlayerTirSelector_2J_1)+"_u.png",
                    "file:src/main/resources/Image_tir/Image_tir_"+Integer.toString(PlayerTirSelector_2J_2)+"_d.png");
        }

        //retour à la page d'accueil
        if (e.getSceneX()>-100d+screen_width && e.getSceneX()<-15d+screen_width
                && e.getSceneY()>-40d+screen_height && e.getSceneY()<-20d+screen_height) {
            stage.setScene(scene0);
        }
    }

    //Initialisation des différentes pages
    public static void menu(Stage stage) {


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
        final String disp_alien_shot_URL_1J ="file:src/main/resources/Image_tir/Image_tir_1_d.png";
        AlienTirSelector_1J=1;
        Image disp_alien_shot_img_1J = new Image(disp_alien_shot_URL_1J);
        ImageView disp_alien_shot_1J = new ImageView(disp_alien_shot_img_1J);
        disp_alien_shot_1J.setLayoutX(400);
        disp_alien_shot_1J.setLayoutY(300);

        final String disp_player_shot_URL_1J ="file:src/main/resources/Image_tir/Image_tir_1_u.png";
        PlayerTirSelector_1J=1;
        Image disp_player_shot_img_1J = new Image(disp_player_shot_URL_1J);
        ImageView disp_player_shot_1J = new ImageView(disp_player_shot_img_1J);
        disp_player_shot_1J.setLayoutX(800);
        disp_player_shot_1J.setLayoutY(350);

        final String disp_aliens_URL_1J ="file:src/main/resources/Image_alien/Image_alien_1.png";
        AlienSelector_1J=1;
        Image disp_aliens_img_1J = new Image(disp_aliens_URL_1J);
        ImageView disp_aliens_1J = new ImageView(disp_aliens_img_1J);
        disp_aliens_1J.setLayoutX(385);
        disp_aliens_1J.setLayoutY(450);
        disp_aliens_1J.setFitWidth(100);
        disp_aliens_1J.setPreserveRatio(true);

        final String disp_ship_URL_1J ="file:src/main/resources/Image_vaisseau/Image_vaisseau_1.png";
        ShipSelector_1J=1;
        Image disp_ship_img_1J = new Image(disp_ship_URL_1J);
        ImageView disp_ship_1J= new ImageView(disp_ship_img_1J);
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

        root1.getChildren().addAll(options_title_1J,options_return_1J,selection_fields_1J, disp_alien_shot_1J,disp_player_shot_1J,
                disp_aliens_1J,disp_ship_1J,GroupArrow_1J);



        //--------------------------------scene2 - Options 2 joueurs
        BorderPane root2 = new BorderPane();

        //Initialisation des images
        final String disp_alien_shot_URL_2J ="file:src/main/resources/Image_tir/Image_tir_1_d.png";
        AlienTirSelector_2J=1;
        Image disp_alien_shot_img_2J = new Image(disp_alien_shot_URL_2J);
        ImageView disp_alien_shot_2J = new ImageView(disp_alien_shot_img_2J);
        disp_alien_shot_2J.setLayoutX(400);
        disp_alien_shot_2J.setLayoutY(90);

        final String disp_player_shot_URL_2J_1 ="file:src/main/resources/Image_tir/Image_tir_1_u.png";
        PlayerTirSelector_2J_1=1;
        Image disp_player_shot_img_2J_1 = new Image(disp_player_shot_URL_2J_1);
        ImageView disp_player_shot_2J_1 = new ImageView(disp_player_shot_img_2J_1);
        disp_player_shot_2J_1.setLayoutX(800);
        disp_player_shot_2J_1.setLayoutY(170);

        final String disp_player_shot_URL_2J_2 ="file:src/main/resources/Image_tir/Image_tir_1_d.png";
        PlayerTirSelector_2J_2=1;
        Image disp_player_shot_img_2J_2 = new Image(disp_player_shot_URL_2J_2);
        ImageView disp_player_shot_2J_2 = new ImageView(disp_player_shot_img_2J_2);
        disp_player_shot_2J_2.setLayoutX(400);
        disp_player_shot_2J_2.setLayoutY(240);

        final String disp_aliens_URL_2J ="file:src/main/resources/Image_alien/Image_alien_1.png";
        AlienSelector_2J=1;
        Image disp_aliens_img_2J = new Image(disp_aliens_URL_2J);
        ImageView disp_aliens_2J = new ImageView(disp_aliens_img_2J);
        disp_aliens_2J.setLayoutX(780);
        disp_aliens_2J.setLayoutY(355);
        disp_aliens_2J.setFitWidth(100);
        disp_aliens_2J.setPreserveRatio(true);

        final String disp_ship_URL_2J_1 ="file:src/main/resources/Image_vaisseau/Image_vaisseau_1.png";
        ShipSelector_2J_1=1;
        Image disp_ship_img_2J_1 = new Image(disp_ship_URL_2J_1);
        ImageView disp_ship_2J_1= new ImageView(disp_ship_img_2J_1);
        disp_ship_2J_1.setLayoutX(395);
        disp_ship_2J_1.setLayoutY(400);
        disp_ship_2J_1.setFitWidth(80);
        disp_ship_2J_1.setPreserveRatio(true);

        final String disp_ship_URL_2J_2 ="file:src/main/resources/Image_vaisseau/Image_vaisseau_1_r.png";
        ShipSelector_2J_2=1;
        Image disp_ship_img_2J_2 = new Image(disp_ship_URL_2J_2);
        ImageView disp_ship_2J_2= new ImageView(disp_ship_img_2J_2);
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


        root2.getChildren().addAll(options_title_2J,options_return_2J,selection_fields_2J,disp_alien_shot_2J,disp_player_shot_2J_1,
                disp_player_shot_2J_2,disp_aliens_2J,disp_ship_2J_1,disp_ship_2J_2,GroupArrow_2J);




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


        //Initialisation des MouseListener de toutes les pages

        //Page d'accueil
        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try
                {
                    mainMenuSelection(e,stage,scene1,scene2, scene3);
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
                    P1optionsMenuSelection(e,stage,scene0,disp_alien_shot_1J,disp_player_shot_1J,disp_aliens_1J,disp_ship_1J);
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
                    P2optionsMenuSelection(e,stage,scene0,
                            disp_alien_shot_2J,disp_player_shot_2J_1,disp_player_shot_2J_2,
                            disp_aliens_2J,disp_ship_2J_1,disp_ship_2J_2);
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

        scene0.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseListener);
        scene1.addEventHandler(MouseEvent.MOUSE_CLICKED,options_1J_Mouse);
        scene2.addEventHandler(MouseEvent.MOUSE_CLICKED,options_2J_Mouse);
        scene3.addEventHandler(MouseEvent.MOUSE_CLICKED,creditsMouse);


        stage.setTitle("Space Invaders");
        stage.setScene(scene0);
        stage.setResizable(false);
        stage.show(); // everything happens everywhere at once
    }

}