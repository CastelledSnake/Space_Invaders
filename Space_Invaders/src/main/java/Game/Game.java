package Game;

import Objet_jeux.*;
import Server.ClientTCP;
import Server.ServeurTCP;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Gère les parties de jeu
 */
public class Game {
    private PropertyChangeSupport pcSupport;

    // Utilisé pour connaître la position et le sens de déplacement du/des groupe(s) d'aliens
    private int deplacement=1;
    private int deplacement2=1;
    private int pos_gr_alien=0;
    private int pos_gr_alien2=0;

    // Utilisé pour mémoriser l'espacement entre les tirs des joueurs
    private int t=0;
    private int t2=0;

    // Variable d'état : pause ou jeu
    private Boolean pause=false;

    // Utilisés pour retenir le temps passé en pause (pause actuelle et somme des pauses)
    private long tempause=0;
    private long tpa=0;
    private int dir_p1=0;
    private int dir_p2=0;
    private int difficulte;
    private String URL_vaisseau;
    private String URL_vaisseau_rev;
    private String URL_alien;
    private String URL_alien_r;
    private String URL_tir_alien;
    private String URL_tir_alien_up;
    private String URL_tir_alien_down;
    private String URL_tir_vaisseau;
    private String URL_tir1;
    private String URL_tir2;
    private Boolean network;
    private ArrayList<Alien> aliens1J= new ArrayList<Alien>();
    private ArrayList<Double> aliens1J_x= new ArrayList<Double>();
    private ArrayList<Double> aliens1J_y= new ArrayList<Double>();
    private ArrayList<Block> blocks1J= new ArrayList<Block>();
    private ArrayList<Block> blocks2J= new ArrayList<Block>();
    private ArrayList<Alien> aliens2J_1= new ArrayList<Alien>();
    private ArrayList<Alien> aliens2J_2= new ArrayList<Alien>();
    private Canon player1;
    private double player1_x;
    private Canon player2;
    private double player2_x;
    private ClientTCP monClientTCP;
    private ServeurTCP monServeur;

    public Game() {
        pcSupport = new PropertyChangeSupport(this);
    }

    public void initAliens_1J() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                Alien alien = new Alien(10d + 50 * i, 10d + 35 * j, URL_alien);
                aliens1J.add(alien);
                aliens1J_x.add(alien.getX());
                aliens1J_y.add(alien.getY());
            }
        }
    }

    public void initAliens_2J() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                Alien alien1 = new Alien(10d + 50 * i, 355 + 35 * j, URL_alien);
                Alien alien2 = new Alien(10d + 50 * i, 325 -35 * j, URL_alien_r);
                aliens2J_1.add(alien1);
                aliens2J_2.add(alien2);
            }
        }
    }

    public void initBlocks_1J() {
        for (int i = 0; i < 4; i++) {
            Block block = new Block(300 * i + 110d, 550);
            blocks1J.add(block);
        }
    }

    public void initBlocks_2J() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                Block block = new Block(300 * i + 110d, 90+510*j);
                blocks2J.add(block);
            }
        }
    }

    public void initPlayer_1J() {
        player1 = new Canon(600, 590, URL_vaisseau);
        player1_x = player1.getX();
    }

    public void initPlayer_2J_1() {
        player1 = new Canon(600, 15, URL_vaisseau_rev);
        player1_x = player1.getX();
    }

    public void initPlayer_2J_2() {
        player2 = new Canon(600, 620, URL_vaisseau);
        player2_x = player2.getX();
    }

    public void depPlayer1() {
        double ancien = player1_x;
        player1.dep_joueur(dir_p1, difficulte);
        player1_x = player1.getX();
        pcSupport.firePropertyChange("player1_x", ancien, player1_x);
    }

    public void depPlayer2() {
        double ancien = player2.getX();
        player2.dep_joueur(dir_p2, difficulte);
        player2_x = player2.getX();
        pcSupport.firePropertyChange("player2_x", ancien, player2_x);
    }

    public void depAliens_1J() {
        ArrayList<Double> ancien_x = aliens1J_x;
        ArrayList<Double> ancien_y = aliens1J_y;
        int ancien_pos = pos_gr_alien;
        int ancien_dep = deplacement;
        int ret[];
        ret = Alien.depalien2(aliens1J,pos_gr_alien,deplacement,"DOWN", difficulte);
        for (int k = 0; k<aliens1J_x.size(); k++) {
            aliens1J_x.set(k, aliens1J.get(k).getX());
            aliens1J_y.set(k, aliens1J.get(k).getY());
        }
        setPos_gr_alien(ret[0]);
        setDeplacement(ret[1]);
        pcSupport.firePropertyChange("pos_gr_alien", ancien_pos, pos_gr_alien);
        pcSupport.firePropertyChange("deplacement", ancien_dep, deplacement);
        pcSupport.firePropertyChange("aliens1J_x", ancien_x, aliens1J_x);
        pcSupport.firePropertyChange("aliens1J_y", ancien_y, aliens1J_y);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {return pcSupport;}

    public void setPcSupport(PropertyChangeSupport pcSupport) {this.pcSupport = pcSupport;}

    public int getDeplacement() {return deplacement;}

    public void setDeplacement(int deplacement) {this.deplacement = deplacement;}

    public int getDeplacement2() {return deplacement2;}

    public void setDeplacement2(int deplacement2) {this.deplacement2 = deplacement2;}

    public int getPos_gr_alien() {return pos_gr_alien;}

    public void setPos_gr_alien(int pos_gr_alien) {this.pos_gr_alien = pos_gr_alien;}

    public int getPos_gr_alien2() {return pos_gr_alien2;}

    public void setPos_gr_alien2(int pos_gr_alien2) {this.pos_gr_alien2 = pos_gr_alien2;}

    public int getT() {return t;}

    public void setT(int t) {this.t = t;}

    public int getT2() {return t2;}

    public void setT2(int t2) {this.t2 = t2;}

    public Boolean getPause() {return pause;}

    public void setPause(Boolean pause) {this.pause = pause;}

    public long getTempause() {return tempause;}

    public void setTempause(long tempause) {this.tempause = tempause;}

    public long getTpa() {return tpa;}

    public void setTpa(long tpa) {this.tpa = tpa;}

    public int getDir_p1() {return dir_p1;}

    public void setDir_p1(int dir_p1) {this.dir_p1 = dir_p1;}

    public int getDir_p2() {return dir_p2;}

    public void setDir_p2(int dir_p2) {this.dir_p2 = dir_p2;}

    public int getDifficulte() {return difficulte;}

    public void setDifficulte(int difficulte) {this.difficulte = difficulte;}

    public String getURL_vaisseau() {return URL_vaisseau;}

    public void setURL_vaisseau(String URL_vaisseau) {this.URL_vaisseau = URL_vaisseau;}

    public String getURL_vaisseau_rev() {return URL_vaisseau_rev;}

    public void setURL_vaisseau_rev(String URL_vaisseau_rev) {this.URL_vaisseau_rev = URL_vaisseau_rev;}

    public String getURL_alien() {return URL_alien;}

    public void setURL_alien(String URL_alien) {this.URL_alien = URL_alien;}

    public String getURL_alien_r() {return URL_alien_r;}

    public void setURL_alien_r(String URL_alien_r) {this.URL_alien_r = URL_alien_r;}

    public String getURL_tir_alien() {return URL_tir_alien;}

    public void setURL_tir_alien(String URL_tir_alien) {this.URL_tir_alien = URL_tir_alien;}

    public String getURL_tir_alien_up() {return URL_tir_alien_up;}

    public void setURL_tir_alien_up(String URL_tir_alien_up) {this.URL_tir_alien_up = URL_tir_alien_up;}

    public String getURL_tir_alien_down() {return URL_tir_alien_down;}

    public void setURL_tir_alien_down(String URL_tir_alien_down) {this.URL_tir_alien_down = URL_tir_alien_down;}

    public String getURL_tir_vaisseau() {return URL_tir_vaisseau;}

    public void setURL_tir_vaisseau(String URL_tir_vaisseau) {this.URL_tir_vaisseau = URL_tir_vaisseau;}

    public String getURL_tir1() {return URL_tir1;}

    public void setURL_tir1(String URL_tir1) {this.URL_tir1 = URL_tir1;}

    public String getURL_tir2() {return URL_tir2;}

    public void setURL_tir2(String URL_tir2) {this.URL_tir2 = URL_tir2;}

    public Boolean getNetwork() {return network;}

    public void setNetwork(Boolean network) {this.network = network;}

    public ArrayList<Alien> getAliens1J() {return aliens1J;}

    public ArrayList<Alien> getAliens2J_1() {return aliens2J_1;}

    public ArrayList<Alien> getAliens2J_2() {return aliens2J_2;}

    public ArrayList<Block> getBlocks1J() {return blocks1J;}

    public ArrayList<Block> getBlocks2J() {return blocks2J;}

    public Canon getPlayer1() {return player1;}

    public Canon getPlayer2() {return player2;}

    public double getPlayer1_x() {return player1_x;}

    public double getPlayer2_x() {return player2_x;}

    public ArrayList<Double> getAliens1J_x() {return aliens1J_x;}

    public ArrayList<Double> getAliens1J_y() {return aliens1J_y;}

    public ClientTCP getMonClientTCP() {return monClientTCP;}

    public void setMonClientTCP(ClientTCP monClientTCP) {this.monClientTCP = monClientTCP;}

    public ServeurTCP getMonServeur() {return monServeur;}

    public void setMonServeur(ServeurTCP monServeur) {this.monServeur = monServeur;}
}
