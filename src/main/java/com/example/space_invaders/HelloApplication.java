package com.example.space_invaders;

import Menu.menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) {
        //System.out.println(System.getProperty("os.name"));
        menu.menu_home(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}