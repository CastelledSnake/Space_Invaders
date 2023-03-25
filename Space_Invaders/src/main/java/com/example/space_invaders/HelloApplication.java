package com.example.space_invaders;

import Menu.MenuGUI;
import Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) {
        Menu menu = new Menu();
        MenuGUI monMenuGUI = new MenuGUI(menu, stage);
        monMenuGUI.initGUI();

    }

    public static void main(String[] args) {
        launch();
    }
}