package com.example.space_invaders;

import Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        //Game.game_1_joueur(stage,5,3);
        Menu.menu(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}