package com.example.space_invaders_test;

import Aliens.Alien;
import Cannons.Cannon;
import Cannons.ImageTEst;
import Cannons.Laser;
import javafx.animation.AnimationTimer;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.abs;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        double screen_width = 1200;
        double screen_height = 600;
        int alien_capacity = (int) (2*(screen_width/170)/3); //occupy about 2/3 of screen
        System.out.println(alien_capacity);
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);
        Group aliens = new Group();
        for (int i=0;i<alien_capacity;i++){
            double dx = (double) i*170;
            Alien alien = new Alien(10d+dx,300d);
            aliens.getChildren().add(alien);
        }
        Group lasers = new Group();
        //Laser testShot = new Laser(100,500);
        //lasers.getChildren().add(testShot);
        Path alien_path = new Path();
        alien_path.getElements().add(new MoveTo(10d,10d));
        Cannon Player1 = new Cannon(screen_width/2,screen_height-110d);
        //ImageTEst test1 = new ImageTEst(10d, 20d);
        //Image im1 = new Image("/Users/nicolasleroux/Desktop/Cours/ENSTA Bretagne/2A/Java/SpaceInvadersTest/Space_Invaders_Test/src/main/java/Cannons/testimage.png");
        //ImageView test1 = new ImageView(im1);
       //test1.setX(10.d);
        //test1.setY(20.d);
        //test1.setFitHeight(100);
        //test1.setPreserveRatio(true);
        //Group imagegroup = new Group(test1);
        TranslateTransition playerLeft = new TranslateTransition(Duration.seconds(0.20), Player1);
        playerLeft.setByX(-50d);
        playerLeft.setOnFinished(event -> {
            Player1.setLayoutX(Player1.getLayoutX() + Player1.getTranslateX());
            Player1.setTranslateX(0);
            System.out.println("Player is at "+Player1.getLayoutX());
        });
        TranslateTransition playerRight = new TranslateTransition(Duration.seconds(0.20), Player1);
        playerRight.setByX(50d);
        playerRight.setOnFinished(event -> {
            Player1.setLayoutX(Player1.getLayoutX() + Player1.getTranslateX());
            Player1.setTranslateX(0);
            System.out.println("Player is at "+Player1.getLayoutX());
        });
        TranslateTransition alienRight = new TranslateTransition(Duration.seconds(1.0),aliens);
        alienRight.setByX(30d);
        alienRight.setOnFinished(event -> {
            aliens.setLayoutX(aliens.getLayoutX()+aliens.getTranslateX());
            aliens.setTranslateX(0);
        });

        TranslateTransition shotUp = new TranslateTransition(Duration.seconds(0.2),lasers);
        shotUp.setByY(-50d);
        shotUp.setOnFinished(event -> {
            lasers.setLayoutY(lasers.getLayoutY()+lasers.getTranslateY());
            lasers.setTranslateY(0);
            System.out.println("The laser group is at "+lasers.getLayoutY());
            for (int i=0;i<lasers.getChildren().size();i++) {
                Node laser = lasers.getChildren().get(i);
                //System.out.println("Before, laser "+i+" is at X: "+laser.getLayoutX()+" and Y: "+laser.getLayoutY());
                //laser.setLayoutY(laser.getLayoutY()+lasers.getLayoutY());
                //laser.setLayoutY(laser.getLayoutY() + laser.getTranslateY()+10*i);
                //laser.setTranslateY(0);
                //System.out.println("Lasers moving up");
                //System.out.println("Lasers:" + lasers.getChildren().size());
                //System.out.println("After, laser "+i+" is at "+laser.getLayoutY());
            }
            System.out.println("for reference, the player is at "+Player1.getLayoutX());
        });
        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.LEFT && Player1.getLayoutX()>0) {
                    playerLeft.play();
                }
                if (e.getCode() == KeyCode.RIGHT && Player1.getLayoutX()<screen_width-100) {
                    playerRight.play();
                }
                if (e.getCode() == KeyCode.UP) {
                    // This is the command to fire a laser
                    Laser shot = new Laser(Player1.getLayoutX()+30,Player1.getLayoutY()-20);
                    root.getChildren().addAll(shot);
                    lasers.getChildren().add(shot);
                    int lasers_size = lasers.getChildren().size();
                    lasers.getChildren().get(lasers_size-1).setLayoutY(lasers.getChildren().get(lasers_size-1).getLayoutY()-lasers.getLayoutY());
                    lasers.getChildren().get(lasers_size-1).setLayoutX(Player1.getLayoutX()+30);
                }
            }
        };
        System.out.println("Before anything happens, player is at "+Player1.getLayoutX());
        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().addAll(aliens,Player1,lasers);
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                alienRight.play();
                shotUp.play();
                checkCollision(lasers,aliens);
                checkOutOfBounds(lasers);
            }
        };
        loop.start();
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show(); // everything happens everywhere at once
    }
    public void checkCollision (Group lasers, Group aliens) {
        int lasers_size = lasers.getChildren().size();
        int aliens_size = aliens.getChildren().size();
        //int[][] eliminated = new int[Math.max(lasers_size,aliens_size)][2];
        //int target_laser_index=42069;
        //int target_alien_index=42069;
        //int k=0;
        for (int i=0;i<lasers_size;i++) {
            for (int j=0;j<aliens_size;j++) {
                double laser_i_x = lasers.getLayoutX() + lasers.getChildren().get(i).getLayoutX();
                double laser_i_y = lasers.getLayoutY() + lasers.getChildren().get(i).getLayoutY();
                double alien_j_x = aliens.getLayoutX() + aliens.getChildren().get(j).getLayoutX();
                double alien_j_y = aliens.getLayoutY() + aliens.getChildren().get(j).getLayoutY();
                Node laser_i = lasers.getChildren().get(i);
                Node alien_j = aliens.getChildren().get(j);
                if (((laser_i_y - alien_j_y) > 0 && (laser_i_y-alien_j_y)<100) &&
                        ((laser_i_x - alien_j_x) > 0 && (laser_i_x-alien_j_x)<150)) {
                    //eliminated[k][0] = i;
                    //eliminated[k][1] = j;
                    //k++;
                    System.out.println("The laser is at ("+laser_i_x+", "+laser_i_y+"). The alien is at ("+alien_j_x+", "+alien_j_y+").");
                    aliens.getChildren().remove(alien_j);
                    lasers.getChildren().remove(laser_i);
                    break;
                }
                /*
                if (lasers.getChildren().get(i).getBoundsInParent().intersects(aliens.getChildren().get(j).getBoundsInParent())) {
                    System.out.println("The laser is at ("+laser_i_x+", "+
                            laser_i_y+"). The alien is at ("+alien_j_x+", "+alien_j_y+").");
                    aliens.getChildren().remove(aliens.getChildren().get(j));
                }*/ //didn't work, but maybe there's something there
            }
        }
        /*
        for (int t=0;t<k;t++){
            aliens.getChildren().remove(aliens.getChildren().get(eliminated[t][0]));
            lasers.getChildren().remove(lasers.getChildren().get(eliminated[t][1]));

        }*/
    }
    public void checkOutOfBounds (Group lasers) {
        int lasers_size = lasers.getChildren().size();
        for (int i=0;i<lasers_size;i++) {
            double laser_i_y = lasers.getLayoutY() + lasers.getChildren().get(i).getLayoutY();
            Node laser_i = lasers.getChildren().get(i);
            if (laser_i_y<0) {
                lasers.getChildren().remove(laser_i);
                //System.out.println("Removed laser from play. There are now "+lasers_size+" lasers left.");
                break;
            }
        }
    }
    // TIMERS TIMERS TIMERS TIMERS, scheduleNextIteration PerformNextIteratiob... (last two are prof's names for methods)
    //public void
    //lasers y position still at 470, despite clearly not being that. It seems that they spawn at fixed distances
    // to the groups they're trying to reach. But only on the y axis? X axis they're free? No, not entirely either
    // as seen with the alien test. These fixed positions are based on the original distance between the first entity
    // and the location of the player that spawns the first one. EX: if I move super right, then ??
    // I think positions are just whack

    public static void main(String[] args) {
        launch();
    }
}