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

/**
 * Gère la fin des parties et la correcte redirection
 */
public class end_of_game {

    private static final String MainBackgroundURL="file:Space_Invaders\\src\\main\\resources\\Image_menu\\Image_menu_1.jpg";    // Le fond d'écran
    static double screen0_width = 1200;
    static double screen0_height = 700;

    /**
     * Définit le titre et l'écran de fin de jeu
     * @param reason cas de fin de partie (victoire, ou cas de défaite)
     * @return le Text associé
     */
    public static Text endOfGameTitle(int reason){

        //https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
        Text title = new Text();
        if (reason == 0) {  // reason = 0 <=> Victoire des joueurs
            title.setText("VICTORY !");
            title.setLayoutX(-200 + screen0_width/2);   // L'ordonnée -200 est fixée pour centrer la boîte de texte.
        }
        else if (reason == 1) { // reason = 1 <=> J1 est mort.
            title.setText("PLAYER 1 DIED");
            title.setLayoutX(-300 + screen0_width/2);
        }
        else if (reason == 2) { // reason = 2 <=> J2 est mort.
            title.setText("PLAYER 2 DIED");
            title.setLayoutX(-300 + screen0_width/2);
        }
        else if (reason == 3) { // reason = 3 <=> Les aliens du J1 ont atteint le bas de l'écran.
            title.setText("PLAYER 1 FAILED ITS MISSION");
            title.setLayoutX(-600 + screen0_width/2);
        }
        else if (reason == 4) { // reason = 4 <=> Les aliens du J2 ont atteint le haut de l'écran.
            title.setText("PLAYER 2 FAILED ITS MISSION");
            title.setLayoutX(-600 + screen0_width/2);
        }
        title.setFont(Font.font("Impact", FontWeight.BOLD,100));
        title.setFill(Color.LIMEGREEN);
        title.setLayoutY(-250 + screen0_height/2);
        return title;
    }

    /**
     * Affiche les résultats de fin de parties
     * @param temps temps écoulé dans la partie
     * @param restants Nombre d'aliens ayant survécu
     * @return le Text correspondant
     */
    public static Text endOfGameResults(float temps, int restants){
        Text score = new Text();
        if (restants==0) score.setText(String.format("Aliens Killed !\nTime %.2fs", temps));
        else score.setText(String.format("Remaining: %d\nTime %.2fs", restants, temps));
        score.setFont(Font.font("Impact", FontWeight.BOLD,30));
        score.setFill(Color.LIMEGREEN);
        score.setLayoutX(-200+screen0_width/2);
        score.setLayoutY(-100+screen0_height/2);
        return score;
    }

    /**
     * Crée le Text de la redirection vers le menu
     * @return le Text correspondant
     */
    public static Text endOfGameMenu() {
        Text options = new Text();
        options.setText("Main Menu");
        options.setFont(Font.font("Impact", FontWeight.NORMAL,50));
        options.setFill(Color.LIMEGREEN);
        options.setLayoutX(-200+screen0_width/2);
        options.setLayoutY(100+screen0_height/2);
        options.setTextAlignment(TextAlignment.CENTER);
        return options;
    }

    /**
     * Crée le texte proposant d'aller au niveau suivant
     * @return le Text correspondant
     */
    public static Text endOfGamelvl_suivant() {
        Text lvl_suivant = new Text();
        lvl_suivant.setText("Niveau suivant");
        lvl_suivant.setFont(Font.font("Impact", FontWeight.NORMAL,50));
        lvl_suivant.setFill(Color.LIMEGREEN);
        lvl_suivant.setLayoutX(-200+screen0_width/2);
        lvl_suivant.setLayoutY(150+screen0_height/2);
        lvl_suivant.setTextAlignment(TextAlignment.CENTER);
        return lvl_suivant;
    }

    /**
     * Gère les redirections vers le menu suivant ou la page d'accueil
     * @param e MouseEvent
     * @param stage Stage
     * @param player MediaPlayer
     * @param niveau Niveau de difficulté
     * @param URL_vaisseau1 adresse relative de l'image du vaisseau du joueur 1
     * @param URL_vaisseau2 adresse relative de l'image du vaisseau du joueur 2 (null si inexistant)
     * @param URL_tir_1 adresse relative de l'image des tirs du joueur 1
     * @param URL_tir_2 adresse relative de l'image des tirs du joueur 1 (null si inexistant)
     * @param URL_alien adresse relative de l'image des aliens
     * @param URL_alien_r adresse relative de l'image des aliens renversés (null si inexistant)
     * @param URL_tir_alien_up adresse relative de l'image des tirs des aliens
     * @param URL_tir_alien_down adresse relative de l'image des tirs des aliens renversés (null si inexistant)
     * @param reason cas de fin de partie (victoire ou défaite)
     * @param nbjoueurs Donne si la partie était à 1 ou 2 joueurs
     * @throws IOException
     */
    public static void endOfGameSelection(MouseEvent e, Stage stage, MediaPlayer player,int niveau,String URL_vaisseau1, String URL_vaisseau2,
                                          String URL_tir_1, String URL_tir_2, String URL_alien, String URL_alien_r, String URL_tir_alien_up,String URL_tir_alien_down,
                                          int reason,int nbjoueurs) throws IOException {

        if (e.getSceneX()>400 && e.getSceneX()<625 && e.getSceneY()>400 && e.getSceneY()<450) {
            if (player!=null) player.stop();
            menu.menu(stage);
        }
        if (e.getSceneX()>400 && e.getSceneX()<700 && e.getSceneY()>460 && e.getSceneY()<500 && reason==0) {
            if (player!=null) player.stop();
            if (nbjoueurs == 1) game.game_1_joueur(stage,niveau+1,URL_vaisseau1,URL_alien,URL_tir_1,URL_tir_alien_up);
            if (nbjoueurs == 2) game.game_2_joueurs(stage,niveau+1,URL_vaisseau1,URL_vaisseau2,
                    URL_alien,URL_alien_r,URL_tir_alien_up,URL_tir_alien_down,URL_tir_1,URL_tir_2);
        }
    }

    /**
     * Gère les fins de parties à 1 Joueur
     * @param stage Stage
     * @param reason Cas de fin de partie (Victoire ou cas de défaite)
     * @param temps Temps passé dans la partie
     * @param restants Nombre d'aliens restants
     * @param player MediaPlayer
     * @param niveau Niveau de difficulté
     * @param URLvaisseau Adresse relative de l'image du vaisseau
     * @param URL_alien Adresse relative de l'image de l'alien
     * @param URL_tir_vaisseau Adresse relative de l'image des tirs du vaisseau
     * @param URL_tir_alien Adresse relative de l'image des tirs des aliens
     */
    public static void endOfGame_1_joueur(Stage stage, int reason, float temps, int restants, MediaPlayer player,
                                          int niveau,
                                          String URLvaisseau, String URL_alien,
                                          String URL_tir_vaisseau, String URL_tir_alien) {

        BorderPane root0 = new BorderPane();
        Image main_background = new Image(MainBackgroundURL,screen0_width,screen0_height,false,false);
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
        Scene scene0 = new Scene(root0, screen0_width, screen0_height, Color.BLACK);
        Text title = endOfGameTitle(reason);
        Text score = endOfGameResults(temps, restants);
        Text menu = endOfGameMenu();
        if (reason==0) {    // Si le joueur a gagné, on lui propose d'aller au niveau suivant, ou vers le menu.
            Text lvl_suivant = endOfGamelvl_suivant();
            root0.getChildren().addAll(title, score, menu, lvl_suivant);
        }
        else {  // Si le joueur a perdu, on ne lui propose que le menu.
            root0.getChildren().addAll(title, score, menu);
        }


        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try{
                    endOfGameSelection(e, stage, player, niveau, URLvaisseau, null,
                            URL_tir_vaisseau, null, URL_alien, null, URL_tir_alien,null, reason, 1);
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

    /**
     * Gère les fins de partie à 2 Joueurs
     * @param stage Stage
     * @param reason Cas de fin de partie (victoire ou cas de défaite)
     * @param temps temps passé dans la partie
     * @param restants Nombre d'aliens restants
     * @param player MediaPlayer
     * @param niveau Niveau de difficulté
     * @param URL_vaisseau1 adresse relative de l'image du vaisseau du joueur 1
     * @param URL_vaisseau2 adresse relative de l'image du vaisseau du joueur 2
     * @param URL_alien adresse relative de l'image des aliens
     * @param URL_alien_r adresse relative de l'image des aliens retournés
     * @param URL_tir_vaisseau_1 adresse relative de l'image des tirs du joueur 1
     * @param URL_tir_vaisseau_2 adresse relative de l'image des tirs du joueur 2
     * @param URL_tir_alien_up adresse relative de l'image des tirs des aliens
     * @param URL_tir_alien_down adresse relative de l'image des tirs des aliens retournés
     */
    public static void endOfGame_2_joueurs(Stage stage, int reason, float temps, int restants, MediaPlayer player,
                                           int niveau,
                                           String URL_vaisseau1, String URL_vaisseau2, String URL_alien, String URL_alien_r,
                                           String URL_tir_vaisseau_1, String URL_tir_vaisseau_2,
                                           String URL_tir_alien_up, String URL_tir_alien_down) {

        BorderPane root0 = new BorderPane();
        Image main_background = new Image(MainBackgroundURL,screen0_width,screen0_height,false,false);
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
        Scene scene0 = new Scene(root0, screen0_width, screen0_height, Color.BLACK);
        Text title = endOfGameTitle(reason);
        Text score = endOfGameResults(temps, restants);
        Text menu = endOfGameMenu();
        if (reason == 0) {  // Si les joueurs ont gagné, on leur propose d'aller au niveau suivant, ou vers le menu.
            Text lvl_suivant = endOfGamelvl_suivant();
            root0.getChildren().addAll(title, score, menu, lvl_suivant);
        }
        else {  // Si les joueurs ont perdu, on ne leur propose que le menu.
            root0.getChildren().addAll(title, score, menu);
        }

        EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try{
                    endOfGameSelection(e, stage, player, niveau,URL_vaisseau1,URL_vaisseau2,
                            URL_tir_vaisseau_1,URL_tir_vaisseau_2,URL_alien, URL_alien_r,
                            URL_tir_alien_up,URL_tir_alien_down,reason,2);
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
