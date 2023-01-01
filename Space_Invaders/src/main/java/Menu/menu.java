package Menu;

import Game.game;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class menu {

    private static final String MainBackgroundURL="file:src\\main\\resources\\Image_menu\\Image_menu_1.jpg";


    //Options de jeu, modifiables par l'utilisateur
    static int numTirJoueur=2;
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


    public static void mainMenuSelection(MouseEvent e, Stage stage) throws IOException {
        if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>420 && e.getSceneY()<450) {
            try {
                game.game_1_joueur(stage, numTirJoueur, numTirAlien,1, true);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>490 && e.getSceneY()<530) {
            try {
                game.game_2_joueurs(stage, numTirJoueur, numTirJoueur, numTirAlien, 1, true);
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void menu_home(Stage stage) {
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



        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try{
                    mainMenuSelection(e,stage);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };

        //scene0.addEventHandler(KeyEvent.KEY_PRESSED,keyListener); inutile ???
        scene0.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseListener);


        stage.setTitle("Space Invaders");
        stage.setScene(scene0);
        stage.setResizable(false);
        stage.show(); // everything happens everywhere at once
    }

}
