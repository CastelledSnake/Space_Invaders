package EndOfGame;

import Game.Game;
import Menu.Menu;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class EndOfGame {

    private static final String MainBackgroundURL="file:src\\main\\resources\\Image_menu\\Image_menu_1.jpg";    // Le fond d'écran

    public static Text endOfGameTitle(double screen0_width, double screen0_height, boolean victory){
        /**
         * Définit le titre de l'écran de fin du jeu.
         */
        //https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
        Text title = new Text();
        if (victory) title.setText("VICTORY !");
        else title.setText("EARTH HES BEEN DEFEATED");
        title.setFont(Font.font("Impact", FontWeight.BOLD,100));
        title.setFill(Color.LIMEGREEN);
        title.setLayoutX(-310+screen0_width/2);
        title.setLayoutY(-100+screen0_height/2);
        return title;
    }

    public static Text endOfGameOptions(double screen0_width, double screen0_height) {
        /**
         * Définit l'affichage des options sur l'écran de fin du jeu.
         * En fait, il s'agit des redirections vers le menu, ou pour quitter le jeu.
         */
        Text options = new Text();
        options.setText("Main Menu\n\nQuit");
        options.setFont(Font.font("Impact", FontWeight.NORMAL,30));
        options.setFill(Color.LIMEGREEN);
        options.setLayoutX(-60+screen0_width/2);
        options.setLayoutY(100+screen0_height/2);
        options.setTextAlignment(TextAlignment.CENTER);
        return options;
    }

    public static void endOfGameSelection(MouseEvent e, Stage stage) throws IOException {
        if (e.getSceneX()>530 && e.getSceneX()<710 && e.getSceneY()>470 && e.getSceneY()<510) {
            Menu.menu(stage);
        }
    }

    public static void endOfGame_1_joueur(Stage stage, boolean victory) {
        /**
         * Fonction principale de la partie Fin de Jeu.
         * On y définit les paramètres globaux de la vue, et on appelle les fonctions annexes.
         */
        double screen0_width = 1200;
        double screen0_height = 800;
        BorderPane root0 = new BorderPane();
        Image main_background = new Image(MainBackgroundURL,1200,800,false,false);
        root0.setBackground(new Background((new BackgroundImage(main_background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT))));
        Scene scene0 = new Scene(root0, screen0_width, screen0_height, Color.BLACK);
        Text title = endOfGameTitle(screen0_width, screen0_height, victory);
        Text options = endOfGameOptions(screen0_width, screen0_height);
        root0.getChildren().addAll(title, options);


        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("My click at ("+e.getSceneX()+", "+e.getSceneY()+")");
                try{
                    endOfGameSelection(e,stage);
                }
                catch (IOException ie) {
                    ie.printStackTrace();
                }
            }
        };
        scene0.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseListener);

        stage.setTitle("Space Invaders");
        stage.setScene(scene0);
        stage.setResizable(false);
        stage.show();
    }
}
