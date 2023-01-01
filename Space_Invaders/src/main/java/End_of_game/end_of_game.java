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
    static double screen0_height = 700;

    /**
     * Définit le titre de l'écran de fin du jeu.
     * @param reason Entier donnant la raison de la fin de partie
     * @return Un texte
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
     * Gère l'affichage des informations en fin de partie.
     * @param temps Le temps écoulé durant la partie
     * @param restants Nombre d'aliens restants (potentiellement nul)
     * @return Un texte
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
     * Définit l'affichage d'une redirection vers le menu.
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
     * Définit l'affichage d'une redirection vers le niveau suivant.
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
     * Méthode redirigeant le joueur vers le niveau suivant, où le menu. En fonction de l'endroit où il a cliqué.
     * @param e Le clic du joueur
     * @param stage Le Stage du précédent jeu
     * @param player Le lecteur de musique
     * @param niveau Le niveau de difficulté précédent
     * @param numTirJoueur1 ID du cosmétique des tirs du J1
     * @param numTirJoueur2 ID du cosmétique des tirs du J2
     * @param numTirAlien ID du cosmétique des tirs des aliens
     * @param reason ID de la raison de la fin de partie
     * @param nbjoueurs Nombre de joueurs (1 ou 2)
     * @throws IOException Exception
     */
    public static void endOfGameSelection(MouseEvent e, Stage stage, MediaPlayer player, int niveau,int numTirJoueur1, int numTirJoueur2, int numTirAlien, int reason,int nbjoueurs) throws IOException {
        if (e.getSceneX()>400 && e.getSceneX()<625 && e.getSceneY()>400 && e.getSceneY()<450) {
            if (player != null) player.stop();
            menu.menu_home(stage);
        }
        if (e.getSceneX()>400 && e.getSceneX()<700 && e.getSceneY()>460 && e.getSceneY()<500 && reason==0) {
            if (player != null) player.stop();
            if (nbjoueurs == 1) game.game_1_joueur(stage,numTirJoueur1,numTirAlien,niveau+1);
            if (nbjoueurs == 2) game.game_2_joueurs(stage,numTirJoueur1,numTirJoueur2,numTirAlien,niveau+1);
        }
    }

    /**
     * Fonction principale de la partie Fin de Jeu pour les parties à 1 joueur.
     * On y définit les paramètres globaux de la vue, et on appelle les fonctions annexes.
     * @param stage Le Stage du précédent jeu
     * @param reason ID de la raison de la fin de partie
     * @param temps Le temps écoulé durant la partie
     * @param restants Nombre d'aliens restants (potentiellement nul)
     * @param player Le lecteur de musique
     * @param niveau Le niveau de difficulté précédent
     * @param numTirJoueur ID du cosmétique des tirs du joueur
     * @param numTirAlien ID du cosmétique des tirs des aliens
     */
    public static void endOfGame_1_joueur(Stage stage, int reason, float temps, int restants, MediaPlayer player, int niveau, int numTirJoueur, int numTirAlien) {
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
                System.out.println("My click at ("+e.getSceneX()+", "+e.getSceneY()+")");
                try{
                    endOfGameSelection(e, stage, player, niveau, numTirJoueur, numTirJoueur, numTirAlien, reason, 1);
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
     * Fonction principale de la partie Fin de Jeu pour les parties à 2 joueurs.
     * On y définit les paramètres globaux de la vue, et on appelle les fonctions annexes.
     * @param stage Le Stage du précédent jeu
     * @param reason ID de la raison de la fin de partie
     * @param temps Le temps écoulé durant la partie
     * @param restants Nombre d'aliens restants (potentiellement nul)
     * @param player Le lecteur de musique
     * @param niveau Le niveau de difficulté précédent
     * @param numTirJoueur1 ID du cosmétique des tirs du J1
     * @param numTirJoueur2 ID du cosmétique des tirs du J2
     * @param numTirAlien ID du cosmétique des tirs des aliens
     */
    public static void endOfGame_2_joueurs(Stage stage, int reason, float temps, int restants, MediaPlayer player, int niveau, int numTirJoueur1, int numTirJoueur2, int numTirAlien) {
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
                System.out.println("My click at ("+e.getSceneX()+", "+e.getSceneY()+")");
                try{
                    endOfGameSelection(e, stage, player, niveau, numTirJoueur1, numTirJoueur2, numTirAlien, reason, 2);
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
