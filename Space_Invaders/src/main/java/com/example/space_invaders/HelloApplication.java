package com.example.space_invaders;

import javafx.application.Application;
import javafx.stage.Stage;
import Menu.menu;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) {
        //System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.dir"));
        //menu.menu_home(stage);
        menu.menu(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}