package org.example.todoapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.todoapp.view.HomeScreenView;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("ToDo's");
        stage.setScene(new HomeScreenView());
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}