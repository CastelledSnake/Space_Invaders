package com.example.space_invaders;

import Menu.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        Menu.menu(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}