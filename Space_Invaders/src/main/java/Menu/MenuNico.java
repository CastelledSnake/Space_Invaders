package Menu;

        import Game.Game;
        import javafx.event.EventHandler;
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

public class MenuNico {

    //private static final String MainBackgroundURL="file:src\\main\\resources\\Image_menu\\Image_menu_1.jpg";
    private static final String MainBackgroundURL="file:src/main/resources/Image_menu/Image_menu_1.jpg";


    //Options de jeu, modifiables par l'utilisateur
    static int numTirJoueur=5;
    static int numTirAlien=3;



    public static Text mainMenuTitle(double screen0_width,double screen0_height){
        //https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
        Text title = new Text();
        title.setText("Space Invaders");
        title.setFont(Font.font("Impact", FontWeight.BOLD,100));
        title.setFill(Color.LIMEGREEN);
        title.setLayoutX(-310+screen0_width/2);
        title.setLayoutY(-100+screen0_height/2);
        return title;
    }

    public static Text mainMenuOptions(double screen0_width, double screen0_height) {
        Text options = new Text();
        options.setText("Single Player\n\nTwo Players\n\nCredits");
        options.setFont(Font.font("Impact", FontWeight.NORMAL,30));
        options.setFill(Color.LIMEGREEN);
        options.setLayoutX(-60+screen0_width/2);
        options.setLayoutY(100+screen0_height/2);
        //options.setLayoutX(0);
        //options.setLayoutY(0);
        //for some reason, the reference is just bellow New game
        options.setTextAlignment(TextAlignment.CENTER);
        return options;
    }

    public static void mainMenuSelection(MouseEvent e, Stage stage, Scene scene2, Scene scene3) throws IOException {
        if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>420 && e.getSceneY()<450) {
            System.out.println("Options");
            stage.setScene(scene2);
        }
        else if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>490 && e.getSceneY()<530) {
            //System.out.println(1);
            //Game.game_2_joueurs(stage,numTirJoueur,numTirJoueur,numTirAlien);
            System.out.println("Options");
            stage.setScene(scene2);
        }
        else if (e.getSceneX()>550 && e.getSceneX()<670 && e.getSceneY()>580 && e.getSceneY()<600) {
            System.out.println("Credits");
            stage.setScene(scene3);
        }
    }

    public static Text P1optionsTitle(double screen2_width,double screen2_height){
        Text title = new Text();
        title.setText("Options");
        title.setFont(Font.font("Impact", FontWeight.BOLD,100));
        title.setFill(Color.LIMEGREEN);
        title.setLayoutX(-135+screen2_width/2);
        title.setLayoutY(-100+screen2_height/2);
        title.setTextAlignment(TextAlignment.CENTER);
        return title;
    }

    public static Text creditsTitle(double screen3_width,double screen3_height){
        Text title = new Text();
        title.setText("Credits");
        title.setFont(Font.font("Impact", FontWeight.BOLD,100));
        title.setFill(Color.LIMEGREEN);
        title.setLayoutX(-135+screen3_width/2);
        title.setLayoutY(-100+screen3_height/2);
        title.setTextAlignment(TextAlignment.CENTER);
        return title;
    }

    public static Text creditsList(double screen3_width, double screen3_height) {
        Text list = new Text();
        list.setText("Vincent Castellan\n\nNicolas Dias\n\nNicolas Le Roux");
        list.setFont(Font.font("Impact", FontWeight.BOLD,30));
        list.setFill(Color.LIMEGREEN);
        list.setLayoutX(-80+screen3_width/2);
        list.setLayoutY(screen3_height/2);
        list.setTextAlignment(TextAlignment.CENTER);
        return list;
    }

    public static Text mainReturn(double screen_width, double screen_height) {
        Text return_button = new Text();
        return_button.setText("Return");
        return_button.setFont(Font.font("Impact", FontWeight.BOLD,30));
        return_button.setFill(Color.LIMEGREEN);
        return_button.setLayoutX(-100d+screen_width);
        return_button.setLayoutY(-20d+screen_height);
        return_button.setTextAlignment(TextAlignment.CENTER);
        return return_button;
    }
    public static Text P1optionsSelectionFields(double screen2_width, double screen2_height) {
        Text selection_fields = new Text("Alien shots\n\nPlayer shots\n\nAliens\n\nShip\n\nStart");
        selection_fields.setLayoutX(-50+screen2_width/2);
        selection_fields.setLayoutY(screen2_height/2);
        selection_fields.setFont(Font.font("Impact", FontWeight.NORMAL,30));
        selection_fields.setFill(Color.LIMEGREEN);
        selection_fields.setTextAlignment(TextAlignment.CENTER);
        return selection_fields;
    }

    public static Polygon P1optionsAlienShotArrowLeft(double screen2_width, double screen2_height) {
        Polygon triangle_left = new Polygon();
        triangle_left.getPoints().setAll(0d,0d,0d,20d,-20d,10d);
        triangle_left.setFill(Color.LIMEGREEN);
        triangle_left.setLayoutX(-60d+(screen2_width/2));
        triangle_left.setLayoutY(-20d+screen2_height/2);
        return triangle_left;
    }

    public static Polygon P1optionsAlienShotArrowRight(double screen2_width, double screen2_height) {
        Polygon triangle_right = new Polygon();
        triangle_right.getPoints().setAll(0d,0d,0d,20d,20d,10d);
        triangle_right.setFill(Color.LIMEGREEN);
        triangle_right.setLayoutX(110d+(screen2_width/2));
        triangle_right.setLayoutY(-20d+screen2_height/2);
        return triangle_right;
    }

    public static Polygon P1optionsPlayerShotArrowLeft(double screen2_width, double screen2_height) {
        Polygon triangle_left = new Polygon();
        triangle_left.getPoints().setAll(0d,0d,0d,20d,-20d,10d);
        triangle_left.setFill(Color.LIMEGREEN);
        triangle_left.setLayoutX(-60d+(screen2_width/2));
        triangle_left.setLayoutY(50d+(screen2_height/2));
        return triangle_left;
    }

    public static Polygon P1optionsPlayerShotArrowRight(double screen2_width, double screen2_height) {
        Polygon triangle_right = new Polygon();
        triangle_right.getPoints().setAll(0d,0d,0d,20d,20d,10d);
        triangle_right.setFill(Color.LIMEGREEN);
        triangle_right.setLayoutX(110d+(screen2_width/2));
        triangle_right.setLayoutY(50d+(screen2_height/2));
        return triangle_right;
    }

    public static Polygon P1optionsAliensArrowLeft(double screen2_width, double screen2_height) {
        Polygon triangle_left = new Polygon();
        triangle_left.getPoints().setAll(0d,0d,0d,20d,-20d,10d);
        triangle_left.setFill(Color.LIMEGREEN);
        triangle_left.setLayoutX(-60d+(screen2_width/2));
        triangle_left.setLayoutY(125d+(screen2_height/2));
        return triangle_left;
    }

    public static Polygon P1optionsAliensArrowRight(double screen2_width, double screen2_height) {
        Polygon triangle_right = new Polygon();
        triangle_right.getPoints().setAll(0d,0d,0d,20d,20d,10d);
        triangle_right.setFill(Color.LIMEGREEN);
        triangle_right.setLayoutX(110d+(screen2_width/2));
        triangle_right.setLayoutY(125d+(screen2_height/2));
        return triangle_right;
    }

    public static Polygon P1optionsShipArrowLeft(double screen2_width, double screen2_height) {
        Polygon triangle_left = new Polygon();
        triangle_left.getPoints().setAll(0d,0d,0d,20d,-20d,10d);
        triangle_left.setFill(Color.LIMEGREEN);
        triangle_left.setLayoutX(-60d+(screen2_width/2));
        triangle_left.setLayoutY(200d+(screen2_height/2));
        return triangle_left;
    }

    public static Polygon P1optionsShipArrowRight(double screen2_width, double screen2_height) {
        Polygon triangle_right = new Polygon();
        triangle_right.getPoints().setAll(0d,0d,0d,20d,20d,10d);
        triangle_right.setFill(Color.LIMEGREEN);
        triangle_right.setLayoutX(110d+(screen2_width/2));
        triangle_right.setLayoutY(200d+(screen2_height/2));
        return triangle_right;
    }

    public static void creditsSelection(MouseEvent e, Stage stage, Scene scene0, double screen3_width, double screen3_height) throws IOException {
        if (e.getSceneX()>-100d+screen3_width && e.getSceneX()<-15d+screen3_width
                && e.getSceneY()>-40d+screen3_height && e.getSceneY()<-20d+screen3_height) {
            System.out.println("Return");
            stage.setScene(scene0);
        }

    }

    public static void P1optionsMenuSelection(MouseEvent e, Stage stage, Scene scene0,
                                            double screen2_width, double screen2_height, ImageView disp_alien_shot, ImageView disp_player_shot) throws IOException {
        char alienShotSelectorChar = disp_alien_shot.getImage().getUrl().charAt(44);
        int alienShotSelector = alienShotSelectorChar - '0';
        char playerShotSelectorChar = disp_player_shot.getImage().getUrl().charAt(44);
        int playerShotSelector = playerShotSelectorChar - '0';
        //System.out.println(alienShotSelector);
        if (e.getSceneX()>-80d+(screen2_width/2) && e.getSceneX()<-60d+(screen2_width/2)
                && e.getSceneY()>-20d+screen2_height/2 && e.getSceneY()<screen2_height/2) {
            if (alienShotSelector!=1) {
                String AlienShotURL = "file:src/main/resources/Image_tir/Image_tir_"+(alienShotSelector-1)+"_d.png";
                Image newImage = new Image(AlienShotURL);
                disp_alien_shot.setImage(newImage);
            }
            else {
                String AlienShotURL = "file:src/main/resources/Image_tir/Image_tir_6_d.png";
                Image newImage = new Image(AlienShotURL);
                disp_alien_shot.setImage(newImage);
            }
        }

        if (e.getSceneX()>110d+(screen2_width/2) && e.getSceneX()<130d+(screen2_width/2)
                && e.getSceneY()>-20d+screen2_height/2 && e.getSceneY()<screen2_height/2) {
            if (alienShotSelector!=6) {
                String AlienShotURL = "file:src/main/resources/Image_tir/Image_tir_"+(alienShotSelector+1)+"_d.png";
                Image newImage = new Image(AlienShotURL);
                disp_alien_shot.setImage(newImage);
            }
            else {
                String AlienShotURL = "file:src/main/resources/Image_tir/Image_tir_1_d.png";
                Image newImage = new Image(AlienShotURL);
                disp_alien_shot.setImage(newImage);
            }
        }

        if (e.getSceneX()>-80d+(screen2_width/2) && e.getSceneX()<-60d+(screen2_width/2)
                && e.getSceneY()>50d+screen2_height/2 && e.getSceneY()<70d+screen2_height/2) {
            if (playerShotSelector!=1) {
                String PlayerShotURL = "file:src/main/resources/Image_tir/Image_tir_"+(playerShotSelector-1)+"_u.png";
                Image newImage = new Image(PlayerShotURL);
                disp_player_shot.setImage(newImage);
            }
            else {
                String PlayerShotURL = "file:src/main/resources/Image_tir/Image_tir_6_u.png";
                Image newImage = new Image(PlayerShotURL);
                disp_player_shot.setImage(newImage);
            }

        }

        if (e.getSceneX()>110d+(screen2_width/2) && e.getSceneX()<130d+(screen2_width/2)
                && e.getSceneY()>50d+screen2_height/2 && e.getSceneY()<70d+screen2_height/2) {
            if (playerShotSelector!=6) {
                String PlayerShotURL = "file:src/main/resources/Image_tir/Image_tir_"+(playerShotSelector+1)+"_u.png";
                Image newImage = new Image(PlayerShotURL);
                disp_player_shot.setImage(newImage);
            }
            else {
                String PlayerShotURL = "file:src/main/resources/Image_tir/Image_tir_1_u.png";
                Image newImage = new Image(PlayerShotURL);
                disp_player_shot.setImage(newImage);
            }

        }

        if (e.getSceneX()>-5d+(screen2_width/2) && e.getSceneX()<60d+(screen2_width/2)
                && e.getSceneY()>270d+screen2_height/2 && e.getSceneY()<300d+screen2_height/2) {
            System.out.println("Start");
            Game.game_1_joueur(stage,playerShotSelector,alienShotSelector);
        }

        if (e.getSceneX()>-100d+screen2_width && e.getSceneX()<-15d+screen2_width
                && e.getSceneY()>-40d+screen2_height && e.getSceneY()<-20d+screen2_height) {
            System.out.println("Return");
            stage.setScene(scene0);
        }
    }

    public static void menu(Stage stage) {


        //scene 0 - Main Menu
        double screen0_width = 1200;
        double screen0_height = 700;
        BorderPane root0 = new BorderPane();
        Image main_background = new Image(MainBackgroundURL,1200,700,false,false);
        root0.setBackground(new Background((new BackgroundImage(main_background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT))));
        Scene scene0 = new Scene(root0,screen0_width,screen0_height,Color.BLACK);
        Text title = mainMenuTitle(screen0_width,screen0_height);
        Text options = mainMenuOptions(screen0_width,screen0_height);
        root0.getChildren().addAll(title,options);



        //scene2 - Options (inutilisé pour l'instant)
        double screen2_width = 1200;
        double screen2_height = 700;
        BorderPane root2 = new BorderPane();
        Image options_background = new Image(MainBackgroundURL,1200,700,false,false);

        final String disp_alien_shot_URL ="file:src/main/resources/Image_tir/Image_tir_1_d.png";
        Image disp_alien_shot_img = new Image(disp_alien_shot_URL);
        ImageView disp_alien_shot = new ImageView(disp_alien_shot_img);
        disp_alien_shot.setLayoutX(400);
        disp_alien_shot.setLayoutY(300);

        final String disp_player_shot_URL ="file:src/main/resources/Image_tir/Image_tir_1_u.png";
        Image disp_player_shot_img = new Image(disp_player_shot_URL);
        ImageView disp_player_shot = new ImageView(disp_player_shot_img);
        disp_player_shot.setLayoutX(800);
        disp_player_shot.setLayoutY(350);

        final String disp_aliens_URL ="file:src/main/resources/Image_alien/Image_alien.png";
        Image disp_aliens_img = new Image(disp_aliens_URL);
        ImageView disp_aliens = new ImageView(disp_aliens_img);
        disp_aliens.setLayoutX(385);
        disp_aliens.setLayoutY(450);
        disp_aliens.setFitWidth(100);
        disp_aliens.setPreserveRatio(true);

        final String disp_ship_URL ="file:src/main/resources/Image_vaisseau/Image_vaisseau.png";
        Image disp_ship_img = new Image(disp_ship_URL);
        ImageView disp_ship = new ImageView(disp_ship_img);
        disp_ship.setLayoutX(775);
        disp_ship.setLayoutY(500);
        disp_ship.setFitWidth(100);
        disp_ship.setPreserveRatio(true);

        //idea: mouseEvent to detect arrows, have a list of images, disp_alien_img.setImage()
        root2.setBackground(new Background((new BackgroundImage(main_background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT))));
        Scene scene2 = new Scene(root2,screen2_width,screen2_height,Color.BLACK);
        Text options_title = P1optionsTitle(screen2_width,screen2_height);
        Text selection_fields = P1optionsSelectionFields(screen2_width,screen2_height);
        Text options_return = mainReturn(screen2_width,screen2_height);
        Polygon alien_shot_arrow_left = P1optionsAlienShotArrowLeft(screen2_width,screen2_height);
        Polygon alien_shot_arrow_right = P1optionsAlienShotArrowRight(screen2_width,screen2_height);
        Polygon player_shot_arrow_left = P1optionsPlayerShotArrowLeft(screen2_width,screen2_height);
        Polygon player_shot_arrow_right = P1optionsPlayerShotArrowRight(screen2_width,screen2_height);
        Polygon aliens_arrow_left = P1optionsAliensArrowLeft(screen2_width,screen2_height);
        Polygon aliens_arrow_right = P1optionsAliensArrowRight(screen2_width,screen2_height);
        Polygon ship_arrow_left = P1optionsShipArrowLeft(screen2_width,screen2_height);
        Polygon ship_arrow_right = P1optionsShipArrowRight(screen2_width,screen2_height);
        root2.getChildren().addAll(options_title,options_return,selection_fields,alien_shot_arrow_left,
                alien_shot_arrow_right,player_shot_arrow_left,player_shot_arrow_right,disp_alien_shot,disp_player_shot,
                disp_aliens,disp_ship,aliens_arrow_left,aliens_arrow_right,ship_arrow_left,ship_arrow_right);

        //scene3 - Credits
        double screen3_width = 1200;
        double screen3_height = 700;
        BorderPane root3 = new BorderPane();
        Image credits_background = new Image(MainBackgroundURL,1200,700,false,false);
        root3.setBackground(new Background((new BackgroundImage(credits_background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT))));
        Scene scene3 = new Scene(root3,screen3_width,screen3_height,Color.BLACK);
        Text credits_title = creditsTitle(screen3_width,screen3_height);
        Text credits_list = creditsList(screen3_width,screen3_height);
        Text credits_return = mainReturn(screen3_width,screen3_height);
        root3.getChildren().addAll(credits_title,credits_list,credits_return);

        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("My click at ("+e.getSceneX()+", "+e.getSceneY()+")");
                //mainMenuSelection(e,stage,scene0,screen0_width,screen0_height,root0);
                //try catch sinon bug, pourquoi???
                try
                {
                    mainMenuSelection(e,stage,scene2,scene3);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        EventHandler<MouseEvent> optionsMouse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("My click at ("+e.getSceneX()+", "+e.getSceneY()+")");
                //mainMenuSelection(e,stage,scene0,screen0_width,screen0_height,root0);
                //try catch sinon bug, pourquoi???
                try
                {
                    P1optionsMenuSelection(e,stage,scene0,screen2_width,screen2_height,disp_alien_shot,disp_player_shot);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        EventHandler<MouseEvent> creditsMouse = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("My click at ("+e.getSceneX()+", "+e.getSceneY()+")");
                try
                {
                    creditsSelection(e,stage,scene0,screen3_width,screen3_height);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        //scene0.addEventHandler(KeyEvent.KEY_PRESSED,keyListener); inutile ???
        scene0.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseListener);
        scene2.addEventHandler(MouseEvent.MOUSE_CLICKED,optionsMouse);
        scene3.addEventHandler(MouseEvent.MOUSE_CLICKED,creditsMouse);


        stage.setTitle("Space Invaders");
        stage.setScene(scene0);
        stage.setResizable(false);
        stage.show(); // everything happens everywhere at once
    }

}
