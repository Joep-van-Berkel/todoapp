package org.example.todoapp.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.example.todoapp.model.TaskModel;

public class HomeScreenView extends Scene {
    // Instance variables for UI components
    private final TableView<TaskModel> table;
    private final TextField descriptionField;
    private final CheckBox highCheckbox;
    private final CheckBox mediumCheckbox;
    private final CheckBox lowCheckbox;
    private final Font titleFont;
    private final Font checkboxFont;

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

        setupView(root);

    }
    private final Button addTaskButton;

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

        // AddTask button
        addTaskButton.setMaxWidth(Double.MAX_VALUE);
        addTaskButton.setPrefHeight(40);

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

    private void populateTableView() {
        // Implementation for populating the table
    }
}