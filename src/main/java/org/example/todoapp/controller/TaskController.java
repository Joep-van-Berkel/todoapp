package org.example.todoapp.controller;

import javafx.scene.control.Alert;
import org.example.todoapp.dao.TaskDao;
import org.example.todoapp.model.TaskModel;
import org.example.todoapp.view.HomeScreenView;

import java.util.ArrayList;

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
            return;
        }
        TaskModel newTask = new TaskModel(description.trim(), priority);

        try {
            TaskDao.saveTask(newTask);
            refreshTable();
        } catch (Exception e) {
            view.showAlert("Error: " + e, Alert.AlertType.ERROR);
        }
    }

    public ArrayList<TaskModel> getAllTasks() {
        ArrayList<TaskModel> tasks = new ArrayList<>();
        try {
            tasks = TaskDao.getAllTasks();

        } catch (Exception e){
            view.showAlert("Error: " + e, Alert.AlertType.ERROR);
        }
        return tasks;
    }

    public void deleteTask(TaskModel task){

        try {
            TaskDao.deleteTask(task);
            refreshTable();
        } catch (Exception e) {
            view.showAlert("Error: " + e, Alert.AlertType.ERROR);
        }
    }

    public void refreshTable(){
        ArrayList<TaskModel> tasks = getAllTasks();

        view.getTable().getItems().clear();
        view.getTable().getItems().addAll(tasks);
    }

}
