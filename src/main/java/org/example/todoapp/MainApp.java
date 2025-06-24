package org.example.todoapp;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.todoapp.dao.DbConn;
import org.example.todoapp.view.HomeScreenView;

import java.io.IOException;

public class MainApp extends Application {

    private DbConn connection ;

    @Override
    public void start(Stage stage) throws IOException {

        connection = new DbConn();

        boolean connected = DbConn.getInstance().makeSqlConnection();
        System.out.println("Connection successful: " + connected);

        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        stage.getIcons().add(icon);

        stage.setIconified(true);
        stage.setTitle("ToDo's");
        stage.setScene(new HomeScreenView());
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void stop(){

        DbConn.getInstance().closeSqlConnection();

        boolean closed = DbConn.getInstance().closeSqlConnection();
        System.out.println("Connection closed: " + closed);
    }

    public static void main(String[] args) {
        launch();
    }
}