package org.example.todoapp.model;

public class TaskModel {

    private int idTask;
    private String description;
    private String priority;

    // Without ID
    public TaskModel(String description, String priority){
        this.description = description;
        this.priority = priority;
    }

    // with ID
    public TaskModel(int idTask, String description, String priority){
        this.idTask = idTask;
        this.description = description;
        this.priority = priority;
    }

    public int getIdTask(){
        return idTask;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }
}
