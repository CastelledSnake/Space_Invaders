package End_of_game;

import Game.game;
import Menu.menu;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class end_of_game {

    private static final String MainBackgroundURL="file:src\\main\\resources\\Image_menu\\Image_menu_1.jpg";    // Le fond d'écran
    static double screen0_width = 1200;
    static double screen0_height = 800;

    public static Text endOfGameTitle(int reason){
        /**
         * Définit le titre de l'écran de fin du jeu.
         */
        //https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
        Text title = new Text();
        if (reason == 0) {  // reason = 0 <=> Victoire des joueurs
            title.setText("VICTORY !");
            title.setLayoutX(-200 + screen0_width/2);   // L'ordonnée -200 est fixée pour centrer la boîte de texte.
        }
        else if (reason == 1) { // reason = 1 <=> L'un des joueurs est mort.
            title.setText("YOU DIED");
            title.setLayoutX(-200 + screen0_width/2);
        }
        else if (reason == 2) { // reason = 2 <=> Les aliens ont atteint le bas de l'écran.
            title.setText("EARTH HAS BEEN INVADED");
            title.setLayoutX(-500 + screen0_width/2);
        }
        title.setFont(Font.font("Impact", FontWeight.BOLD,100));
        title.setFill(Color.LIMEGREEN);
        title.setLayoutY(-250 + screen0_height/2);
        return title;
    }

    public static Text endOfGameResults(float temps, int restants){
        /**
         * Gère l'affichage des informations en fin de partie.
         */
        Text score = new Text();
        if (restants==0) score.setText(String.format("Aliens Killed !\nTime %.2fs", temps));
        else score.setText(String.format("Remaining: %d\nTime %.2fs", restants, temps));
        score.setFont(Font.font("Impact", FontWeight.BOLD,30));
        score.setFill(Color.LIMEGREEN);
        score.setLayoutX(-200+screen0_width/2);
        score.setLayoutY(-100+screen0_height/2);
        return score;
    }

    public static Text endOfGameMenu() {
        /**
         * Définit l'affichage des options sur l'écran de fin du jeu.
         * En fait, il s'agit des redirections vers le menu, ou pour quitter le jeu.
         */
        Text options = new Text();
        options.setText("Main Menu");
        options.setFont(Font.font("Impact", FontWeight.NORMAL,50));
        options.setFill(Color.LIMEGREEN);
        options.setLayoutX(-200+screen0_width/2);
        options.setLayoutY(100+screen0_height/2);
        options.setTextAlignment(TextAlignment.CENTER);
        return options;
    }

    public static Text endOfGamelvl_suivant() {
        /**
         * Définit l'affichage des options sur l'écran de fin du jeu.
         * En fait, il s'agit des redirections vers le menu, ou pour quitter le jeu.
         */
        Text lvl_suivant = new Text();
        lvl_suivant.setText("Niveau suivant");
        lvl_suivant.setFont(Font.font("Impact", FontWeight.NORMAL,50));
        lvl_suivant.setFill(Color.LIMEGREEN);
        lvl_suivant.setLayoutX(-200+screen0_width/2);
        lvl_suivant.setLayoutY(150+screen0_height/2);
        lvl_suivant.setTextAlignment(TextAlignment.CENTER);
        return lvl_suivant;
    }

    public static void endOfGameSelection(MouseEvent e, Stage stage, MediaPlayer player, int niveau,int numTirJoueur, int numTirAlien, int reason,int nbjoueurs) throws IOException {
        if (e.getSceneX()>400 && e.getSceneX()<625 && e.getSceneY()>460 && e.getSceneY()<510) {
            player.stop();
            menu.menu_home(stage);
        }
        if (e.getSceneX()>400 && e.getSceneX()<700 && e.getSceneX()>510 && e.getSceneY()<550 && reason==0) {
            player.stop();
            if (nbjoueurs==1) game.game_1_joueur(stage,numTirJoueur,numTirAlien,niveau+1);
            if (nbjoueurs==2) game.game_2_joueurs(stage,numTirJoueur,numTirJoueur,numTirAlien,niveau+1);
        }
    }

    public static void endOfGame_1_joueur(Stage stage, int reason, float temps, int restants, MediaPlayer player, int niveau, int numTirJoueur, int numTirAlien, int nbjoueurs) {
        /**
         * Fonction principale de la partie Fin de Jeu.
         * On y définit les paramètres globaux de la vue, et on appelle les fonctions annexes.
         */
        BorderPane root0 = new BorderPane();
        Image main_background = new Image(MainBackgroundURL,screen0_width,screen0_height,false,false);
        root0.setBackground(new Background((new BackgroundImage(main_background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT))));
        Scene scene0 = new Scene(root0, screen0_width, screen0_height, Color.BLACK);
        Text title = endOfGameTitle(reason);
        Text score = endOfGameResults(temps, restants);
        Text options = endOfGameMenu();
        if (reason==0) {
            Text lvl_suivant = endOfGamelvl_suivant();
            root0.getChildren().addAll(title, score, options, lvl_suivant);
        }
        else {
            root0.getChildren().addAll(title, score, options);
        }


        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("My click at ("+e.getSceneX()+", "+e.getSceneY()+")");
                try{
                    endOfGameSelection(e,stage, player,niveau,numTirJoueur,numTirAlien,reason,nbjoueurs);
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
