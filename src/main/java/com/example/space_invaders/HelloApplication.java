package com.example.space_invaders;

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
import java.util.Objects;

public class HelloApplication extends Application {

    public int deplacement=1;


    @Override
    public void start(Stage stage) throws IOException {

        double screen_width = 1200;
        double screen_height = 600;
        int alien_Xcapacity = (int) 8;
        int alien_Ycapacity = (int) 3;
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);
        Group aliens = new Group();
        for (int i=0;i<alien_Xcapacity;i++){
            for (int j=0; j<alien_Ycapacity;j++) {
                Alien alien = new Alien(10d + 50 * i, 10d+35*j);
                aliens.getChildren().add(alien);
            }
        }
        Path alien_path = new Path();
        alien_path.getElements().add(new MoveTo(10d,10d));

        Cannon Player1 = new Cannon(screen_width/2,screen_height-110d);
        TranslateTransition playerLeft = new TranslateTransition(Duration.seconds(0.05), Player1);
        playerLeft.setByX(-10d);
        playerLeft.setOnFinished(event -> {
            Player1.setLayoutX(Player1.getLayoutX()+Player1.getTranslateX());
            Player1.setTranslateX(0);
        });

        TranslateTransition playerRight = new TranslateTransition(Duration.seconds(0.05), Player1);
        playerRight.setByX(10d);
        playerRight.setOnFinished(event -> {
            Player1.setLayoutX(Player1.getLayoutX()+Player1.getTranslateX());
            Player1.setTranslateX(0);
        });



        TranslateTransition alienRight = new TranslateTransition(Duration.seconds(0.05), aliens);
        alienRight.setByX(5d);
        alienRight.setOnFinished(event -> {
            aliens.setLayoutX(aliens.getLayoutX()+aliens.getTranslateX());
            aliens.setTranslateX(0);
        });

        TranslateTransition alienLeft = new TranslateTransition(Duration.seconds(0.05), aliens);
        alienLeft.setByX(-5d);
        alienLeft.setOnFinished(event -> {
            aliens.setLayoutX(aliens.getLayoutX()+aliens.getTranslateX());
            aliens.setTranslateX(0);
        });

        TranslateTransition alienDown = new TranslateTransition(Duration.seconds(0.05), aliens);
        alienDown.setByY(10d);
        alienDown.setOnFinished(event -> {
            aliens.setLayoutY(aliens.getLayoutY()+aliens.getTranslateY());
            aliens.setTranslateY(0);
        });
        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT && Player1.getLayoutX()>0) {
                    playerLeft.play();
                }
                if (e.getCode() == KeyCode.RIGHT && Player1.getLayoutX()<1140) {
                    playerRight.play();
                }
                if ((aliens.getLayoutX()==0 && deplacement==-1) || (aliens.getLayoutX()==800 && deplacement==1))
                {
                    deplacement=-deplacement;
                    alienDown.play();
                }
                else if (deplacement==1) {
                    alienRight.play();
                } else if (deplacement==-1) {
                    alienLeft.play();
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