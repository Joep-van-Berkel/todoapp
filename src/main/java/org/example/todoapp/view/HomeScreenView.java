package org.example.todoapp.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.todoapp.controller.TaskController;
import org.example.todoapp.model.TaskModel;

public class HomeScreenView extends Scene {
    // Instance variables for UI components
    private final TableView<TaskModel> table;
    private final TextField descriptionField;
    private final CheckBox highCheckbox;
    private final CheckBox mediumCheckbox;
    private final CheckBox lowCheckbox;
    private final Button addTaskButton;

    // instance variables for fonts
    private final Font titleFont;
    private final Font checkboxFont;

    private TaskController controller;

    public HomeScreenView() {
        VBox root = new VBox();
        super(root);

        // Initialize UI components
        table = new TableView<>();
        descriptionField = new TextField();
        highCheckbox = new CheckBox("HIGH");
        mediumCheckbox = new CheckBox("MEDIUM");
        lowCheckbox = new CheckBox("LOW");
        addTaskButton = new Button("Add to ToDo list");

        // Initialize fonts
        titleFont = Font.font("Montserrat", FontWeight.BOLD, 16);
        checkboxFont = Font.font("Montserrat", FontWeight.SEMI_BOLD, 14);

        controller = new TaskController(this);

        setupView(root);
    }

    public String getDescription(){
        return descriptionField.getText();
    }

    public String getPriority(){
        if (highCheckbox.isSelected()) return "HIGH";
        if (mediumCheckbox.isSelected()) return "MEDIUM";
        if (lowCheckbox.isSelected()) return "LOW";

        return null;

    }

    private void setupView(VBox root) {
        // Table columns
        TableColumn<TaskModel, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        TableColumn<TaskModel, String> priorityColumn = new TableColumn<>("Priority");
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        TableColumn<TaskModel, Void> deleteColumn = new TableColumn<>("");
        table.getColumns().addAll(descriptionColumn, priorityColumn, deleteColumn);
        table.setPrefWidth(800);
        table.setMaxHeight(Double.MAX_VALUE);
        table.widthProperty().addListener((obs, oldVal, newVal) -> {
            double colWidth = newVal.doubleValue() / 3;
            descriptionColumn.setPrefWidth(colWidth);
            priorityColumn.setPrefWidth(colWidth);
            deleteColumn.setPrefWidth(colWidth);
        });

        // Description input
        Label descriptionLabel = new Label("Task description");
        descriptionLabel.setFont(titleFont);
        descriptionField.setPromptText("Describe the task.");
        descriptionField.setMaxWidth(Double.MAX_VALUE);
        descriptionField.setPrefHeight(40);
        VBox descriptionBox = new VBox(5, descriptionLabel, descriptionField);
        descriptionBox.setFillWidth(true);

        // Priority input
        Label priorityLabel = new Label("Task priority");
        priorityLabel.setFont(titleFont);
        VBox priorityBox = new VBox(5, priorityLabel, highCheckbox, mediumCheckbox, lowCheckbox);
        highCheckbox.setFont(checkboxFont);
        mediumCheckbox.setFont(checkboxFont);
        lowCheckbox.setFont(checkboxFont);

        highCheckbox.setOnAction(e -> {
            if (highCheckbox.isSelected()) {
                mediumCheckbox.setSelected(false);
                lowCheckbox.setSelected(false);
            }
        });
        mediumCheckbox.setOnAction(e -> {
            if (mediumCheckbox.isSelected()) {
                highCheckbox.setSelected(false);
                lowCheckbox.setSelected(false);
            }
        });
        lowCheckbox.setOnAction(e -> {
            if (lowCheckbox.isSelected()) {
                highCheckbox.setSelected(false);
                mediumCheckbox.setSelected(false);
            }
        });

        // AddTask button
        addTaskButton.setMaxWidth(Double.MAX_VALUE);
        addTaskButton.setPrefHeight(40);
        addTaskButton.setOnAction(e -> controller.saveTask());

        // Spacer
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Right container
        VBox rightContainer = new VBox(20, descriptionBox, priorityBox, spacer, addTaskButton);
        rightContainer.setFillWidth(true);
        rightContainer.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(rightContainer, Priority.ALWAYS);

        // Main layout
        HBox mainContainer = new HBox(100, table, rightContainer);
        mainContainer.setPadding(new Insets(100));
        VBox.setVgrow(mainContainer, Priority.ALWAYS);

        root.getChildren().add(mainContainer);

        Platform.runLater(() -> mainContainer.requestFocus());
    }

    public void showAlert(String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Error" : "Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void populateTableView() {
        // Implementation for populating the table
    }
}