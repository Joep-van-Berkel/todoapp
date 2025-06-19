package org.example.todoapp.model;

public class TaskModel {

    private final String description;
    private final String priority;

    public TaskModel(String description, String priority){
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }
}
