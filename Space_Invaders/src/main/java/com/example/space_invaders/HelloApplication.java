package com.example.space_invaders;

import Objet.Objet;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.stream.DoubleStream;

public class HelloApplication extends Application {

    public int deplacement=1;
    public int t=0;

    public int pos_gr_alien=1;

    double[] formalien={0.0d,0.0d,40.0d,0.0d,40.0d,20.0d,0.0d,20.0d};
    double[] formecanon= {0.0d,0.0d,60.0d,0.0d,60.0d,80.0d,0.0d,80.0d};

    double[] formetir= {0.0d,0.0d,20.0d,0.0d,20.0d,40.0d,0.0d,40.0d};

    double[] formebloc= {0.0d,0.0d,80.0d,0.0d,80.0d,20.0d,0.0d,20.0d};

    private static final String AlienURL="file:src\\main\\java\\Objet\\Image_alien.jpg";
    private static final String VaisseauURL="file:src\\main\\java\\Objet\\Image_vaisseau.jpg";

    private static final String TirAlienURL="file:src\\main\\java\\Objet\\Image_tir_1_d.png";
    private static final String TirJoueurURL="file:src\\main\\java\\Objet\\Image_tir_1_u.png";
    public void depjoueur(KeyEvent e,Objet Player) {
        if (e.getCode() == KeyCode.LEFT && Player.getLayoutX()>0) {
            Player.setLayoutX(Player.getLayoutX()-6d);
        }
        if (e.getCode() == KeyCode.RIGHT && Player.getLayoutX()<1140) {
            Player.setLayoutX(Player.getLayoutX()+6d);
        }
    }

    public void depalien(Group aliens) {
        int n_alien=aliens.getChildren().size();
        if ((pos_gr_alien==0 && deplacement==-1) || (pos_gr_alien==800 && deplacement==1))
        {
            deplacement=-deplacement;
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutY(aliens.getChildren().get(i).getLayoutY() + 10d);
            }
        }
        else if (deplacement==1) {
            for (int i = 0; i < n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX() + 1d);
            }
            pos_gr_alien++;
        }
        else if (deplacement==-1) {
            for (int i=0;i<n_alien; i++) {
                aliens.getChildren().get(i).setLayoutX(aliens.getChildren().get(i).getLayoutX()-1d);
            }
            pos_gr_alien--;
        }
    }

    public void Tirup(Group tirs) {
        int n = tirs.getChildren().size();
        for (int i=0;i<n;i++) {
            tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY()-3d);
        }
    }

    public void Tirdown(Group tirs) {
        int n = tirs.getChildren().size();
        for (int i=0;i<n;i++) {
            tirs.getChildren().get(i).setLayoutY(tirs.getChildren().get(i).getLayoutY()+3d);
        }
    }

    public Boolean memeposition(javafx.scene.Node g1, javafx.scene.Node g2,int xmin, int xmax, int ymin, int ymax) {
        double x1=g1.getLayoutX();
        double y1=g1.getLayoutY();
        double x2=g2.getLayoutX();
        double y2=g2.getLayoutY();
        if (x1-x2>xmin && x1-x2<xmax && y1-y2>ymin && y1-y2<ymax) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    public void Collision(Group g1, Group g2, int xmin, int xmax, int ymin, int ymax) {
        int na=g1.getChildren().size();
        int nt=g2.getChildren().size();

        int i=0;
        for (int a=0;a<na;a++) {
            for (int t=0; t<nt;t++) {
                if (memeposition(g1.getChildren().get(a),g2.getChildren().get(t),xmin,xmax,ymin,ymax)){

                    String vie1=g1.getChildren().get(a).getAccessibleText();
                    String vie2=g2.getChildren().get(t).getAccessibleText();

                    int nvie1=Integer.parseInt(vie1)-1;
                    int nvie2=Integer.parseInt(vie2)-1;

                    g1.getChildren().get(a).setAccessibleText(Integer.toString(nvie1));
                    g2.getChildren().get(t).setAccessibleText(Integer.toString(nvie2));

                }
            }
        }
    }

    public void supp(Group g) {
        ArrayList<javafx.scene.Node> a_supp=new ArrayList<>();

        for (javafx.scene.Node elem : g.getChildren()) {
            if (elem.getAccessibleText().equals("0")) a_supp.add(elem);
        }

        for (javafx.scene.Node elem : a_supp) g.getChildren().remove(elem);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void start(Stage stage) throws IOException {

        double screen_width = 1200;
        double screen_height = 600;
        int alien_Xcapacity = (int) 8;
        int alien_Ycapacity = (int) 3;
        BorderPane root = new BorderPane(); //investigate Group root
        Scene scene = new Scene(root, screen_width, screen_height, Color.BLACK);

        Group tirs_joueurs=new Group();

        Group tirs_aliens=new Group();


        Group aliens = new Group();

        for (int i=0;i<alien_Xcapacity;i++){
            for (int j=0; j<alien_Ycapacity;j++) {
                Objet alien = new Objet(10d + 50 * i, 10d+35*j, formalien, Color.LIMEGREEN, AlienURL,1);
                aliens.getChildren().add(alien);

            }
        }

        Group blocks=new Group();
        Group vie_blocks=new Group();
        for (int i=0; i<4; i++) {
            Objet block = new Objet(screen_width * (i) / 4 + 110d, screen_height - 200d, formebloc, Color.LIMEGREEN, "NULL", 10);
            blocks.getChildren().add(block);
            Text v = new Text(block.getAccessibleText());
            v.setFill(Color.WHITE);
            vie_blocks.getChildren().add(v);
            v.setX(block.getLayoutX());
            v.setY(block.getLayoutY());
        }


        Objet Player1= new Objet(screen_width/2,screen_height-110d,formecanon, Color.LIMEGREEN,VaisseauURL,2);

        Text vie_joueur= new Text(Player1.getAccessibleText());
        vie_joueur.setFill(Color.WHITE);

        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                depjoueur(e,Player1);
            }
        };

        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //pattern de déplacement des aliens
                depalien(aliens);

                //tir du joueur tous les 50 mouvements
                if (t==50) {
                    Objet tirj = new Objet(Player1.getLayoutX()+25d,Player1.getLayoutY(),formetir,Color.GREEN,TirJoueurURL,1);
                    tirs_joueurs.getChildren().add(tirj);
                    t=0;
                }
                else t++;

                //tir des aliens
                if (getRandomNumber(0,60)==0) {
                    int a=getRandomNumber(0,aliens.getChildren().size());
                    Objet tira = new Objet(aliens.getChildren().get(a).getLayoutX(),aliens.getChildren().get(a).getLayoutY(),formetir,Color.RED,TirAlienURL,1);
                    tirs_aliens.getChildren().add(tira);
                }

                //déplacement des tirs
                Tirup(tirs_joueurs);
                Tirdown(tirs_aliens);
                //enlever les tirs en dehors
                tirs_joueurs.getChildren().removeIf(elem -> elem.getLayoutY()<0);
                tirs_aliens.getChildren().removeIf(elem -> elem.getLayoutY()>700);

                //gestion des collisions
                Collision(aliens,tirs_joueurs,-50,10,-15,5);
                Collision(tirs_aliens,tirs_joueurs,-30,10,-10,0);
                Collision(tirs_aliens,blocks,-10,80,-10,10);
                Collision(tirs_joueurs,blocks,-10,80,-10,10);
                //retirer si plus de vie
                supp(aliens);
                supp(tirs_joueurs);
                supp(tirs_aliens);
                supp(blocks);

                //affichage des vies du joueur (collision avec joueur encore à faire), contenu à changer (voir blocks)
                vie_joueur.setX(Player1.getLayoutX());
                vie_joueur.setY(Player1.getLayoutY());

                //affichage des vies des blocks
                vie_blocks.getChildren().clear();
                for (int i=0; i<blocks.getChildren().size(); i++) {
                    Text v = new Text(blocks.getChildren().get(i).getAccessibleText());
                    v.setFill(Color.WHITE);
                    v.setX(blocks.getChildren().get(i).getLayoutX());
                    v.setY(blocks.getChildren().get(i).getLayoutY());
                    vie_blocks.getChildren().add(v);
                }
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().addAll(aliens,Player1,tirs_joueurs,tirs_aliens,blocks,vie_joueur,vie_blocks);
        loop.start();
        stage.setTitle("Space Invaders");
        stage.setScene(scene);
        stage.show(); // everything happens everywhere at once
    }

    public static void main(String[] args) {
        launch();
    }
}