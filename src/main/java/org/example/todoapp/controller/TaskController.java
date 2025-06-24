package org.example.todoapp.controller;

import javafx.scene.control.Alert;
import org.example.todoapp.view.HomeScreenView;

public class TaskController {

    private HomeScreenView view;

    public TaskController(HomeScreenView view){
        this.view = view;
    }

    public void saveTask(){

        String description = view.getDescription();
        String priority = view.getPriority();

        if (description == null || description.trim().isEmpty() || priority == null){
            view.showAlert("Not all fields have been filled in", Alert.AlertType.ERROR);

        }
    }
}
