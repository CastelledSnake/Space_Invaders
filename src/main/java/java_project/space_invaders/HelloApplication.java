package java_project.space_invaders;

import Aliens.Alien;
import Cannons.Cannon;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        double screen_width = 600;
        double screen_height = 600;
        int alien_capacity = (int) (2*(screen_width/170)/3); //occupy about 2/3 of screen
        System.out.println(alien_capacity);
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);
        Group aliens = new Group();
        for (int i=0;i<alien_capacity;i++){
            double dx = (double) i*170;
            Alien alien = new Alien(10d+dx,10d);
            aliens.getChildren().add(alien);
        }
        Path alien_path = new Path();
        alien_path.getElements().add(new MoveTo(10d,10d));
        Cannon Player1 = new Cannon(screen_width/2,screen_height-110d);
        TranslateTransition playerLeft = new TranslateTransition(Duration.seconds(0.50), Player1);
        playerLeft.setByX(-50d);
        TranslateTransition playerRight = new TranslateTransition(Duration.seconds(0.50), Player1);
        playerRight.setByX(50d);
        TranslateTransition alienRight = new TranslateTransition(Duration.seconds(1.0),aliens);
        alienRight.setByX(50d);
        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT) {
                    //System.out.println("LEFT");
                    playerLeft.play();
                }
                if (e.getCode() == KeyCode.RIGHT) {
                    //System.out.println("RIGHT");
                    playerRight.play();
                }
            }
        };
        /*
        for (int i=0;i<4;i++){
            alienRight.play();
            aliens.setLayoutX(aliens.getLayoutX()+50d);
            System.out.println("Move!");
        }
        */
        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().addAll(aliens,Player1);
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show(); // everything happens everywhere at once
    }

    public static void main(String[] args) {
        launch();
    }
}