package com.example.space_invaders;

import Objet.Objet;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.DoubleStream;

public class HelloApplication extends Application {

    public int deplacement=1;
    public int t=0;

    public int pos_gr_alien=1;

    double[] formalien= {0.0d,0.0d,10.0d,0.0d,10.0d,10.0d,
            20.0d, 10.0d,20.0d,0.0d,30.0d,0.0d,30.0d,20.0d,0.0d,20.0d};
    double[] formecanon= {0.0d,0.0d,20.0d,0.0d,20.0d,20.0d,
            40.0d, 20.0d,40.0d,0.0d,60.0d,0.0d,60.0d,100.0d,0.0d,100.0d};

    double[] formetir= {0.0d,0.0d,10.0d,0.0d,10.0d,10.0d,0.0d,10.0d};

    public void depjoueur(KeyEvent e,Objet Player) {
        if (e.getCode() == KeyCode.LEFT && Player.getLayoutX()>0) {
            Player.setLayoutX(Player.getLayoutX()-10d);
        }
        if (e.getCode() == KeyCode.RIGHT && Player.getLayoutX()<1140) {
            Player.setLayoutX(Player.getLayoutX()+10d);
        }
    }

    public void depalien(Group aliens) {
        int n_alien=aliens.getChildren().size();
        if ((pos_gr_alien==0 && deplacement==-1) || (pos_gr_alien==160 && deplacement==1))
        {
            deplacement=-deplacement;
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutY(aliens.getChildren().get(i).getLayoutY() + 10d);
            }
        }
        else if (deplacement==1) {
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() + 5d);
            }
            pos_gr_alien++;
        }
        else if (deplacement==-1) {
            for (int i=0;i<n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX()-5d);
            }
            pos_gr_alien--;
        }
    }

    public void Tirup(Group tirs) {
        int n = tirs.getChildren().size();
        for (int i=0;i<n;i++) {
            tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY()-18d);
        }
    }

    public Boolean memeposition(javafx.scene.Node alien, javafx.scene.Node tir) {
        double xa=alien.getLayoutX();
        double ya=alien.getLayoutY();
        double xt=tir.getLayoutX();
        double yt=tir.getLayoutY();
        if (Math.abs(xa-xt+15)<25 && Math.abs(ya-yt)<25) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    public void Collision_tir_alien(Group aliens, Group tirs) {
        int na=aliens.getChildren().size();
        int nt=tirs.getChildren().size();
        int[][] a_supp=new int[Math.max(na,nt)][2];

        for (int j=0; j<a_supp.length; j++) {
            a_supp[j][0]=-1;
            a_supp[j][1]=-1;
        }

        int i=0;
        for (int a=0;a<na;a++) {
            for (int t=0; t<nt;t++) {
                if (memeposition(aliens.getChildren().get(a),tirs.getChildren().get(t))){
                    a_supp[i][0]=a;
                    a_supp[i][1]=t;
                    i++;
                }
            }
        }

        for (int j=0; j<a_supp.length; j++) {
            if (a_supp[j][0]!=-1){
                aliens.getChildren().remove(a_supp[j][0]);
                tirs.getChildren().remove(a_supp[j][1]);
            }
        }

    }

    @Override
    public void start(Stage stage) throws IOException {

        double screen_width = 1200;
        double screen_height = 600;
        int alien_Xcapacity = (int) 8;
        int alien_Ycapacity = (int) 3;
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);
        Group tirs=new Group();
        Group aliens = new Group();
        for (int i=0;i<alien_Xcapacity;i++){
            for (int j=0; j<alien_Ycapacity;j++) {
                Objet alien = new Objet(10d + 50 * i, 10d+35*j, formalien);
                aliens.getChildren().add(alien);
            }
        }
        Path alien_path = new Path();
        alien_path.getElements().add(new MoveTo(10d,10d));


        Objet Player1= new Objet(screen_width/2,screen_height-110d,formecanon);


        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {

                depjoueur(e,Player1);

                depalien(aliens);

                if (t==5) {
                    Objet tir = new Objet(Player1.getLayoutX()+25d,Player1.getLayoutY()-tirs.getLayoutY(),formetir);
                    tirs.getChildren().add(tir);
                    t=0;
                }
                else t++;

                Tirup(tirs);
                tirs.getChildren().removeIf(elem -> elem.getLayoutY()<0);
                System.out.println(tirs.getChildren().size());
                Collision_tir_alien(aliens,tirs);

            }
        };

        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().addAll(aliens,Player1,tirs);
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show(); // everything happens everywhere at once
    }

    public static void main(String[] args) {
        launch();
    }
}