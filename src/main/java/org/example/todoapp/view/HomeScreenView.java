package org.example.todoapp.view;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.todoapp.model.TaskModel;

public class HomeScreenView extends Scene {
    public HomeScreenView() {
        VBox root = new VBox();
        super(root);
        setupView(root);

    }

    private VBox setupView(VBox root){

        HBox mainContainer = new HBox();

        // table and columns
        TableView<TaskModel> table = new TableView<>();
        TableColumn<TaskModel, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        TableColumn<TaskModel, String> priorityColumn = new TableColumn<>("Priority");
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        TableColumn<TaskModel, Void> deleteColumn = new TableColumn<>("");
        table.getColumns().addAll(descriptionColumn,priorityColumn,deleteColumn);
        // After creating all columns and adding them to the table
        table.widthProperty().addListener((obs, oldVal, newVal) -> {
            double colWidth = newVal.doubleValue() / 3;
            descriptionColumn.setPrefWidth(colWidth);
            priorityColumn.setPrefWidth(colWidth);
            deleteColumn.setPrefWidth(colWidth);
        });


        VBox rightContainer = new VBox();

        Label descriptionLabel = new Label("Task description");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Describe the task here ..");

        Label priorityLabel = new Label("Task priority");
        CheckBox highCheckbox = new CheckBox("HIGH");
        CheckBox mediumCheckbox = new CheckBox("MEDIUM");
        CheckBox lowCheckbox = new CheckBox("LOW");

        Button addButton = new Button("Add to ToDo list");

        VBox.setVgrow(mainContainer, Priority.ALWAYS);
        mainContainer.setPadding(new Insets(100));
        mainContainer.setSpacing(100);

        table.setMaxHeight(Double.MAX_VALUE);
        table.setPrefWidth(800);

        rightContainer.getChildren().addAll(descriptionLabel,descriptionField,priorityLabel,highCheckbox,mediumCheckbox,lowCheckbox,addButton);
        mainContainer.getChildren().addAll(table,rightContainer);

        root.getChildren().add(mainContainer);
        return root;
    }

    private void populateTableView(){

    }
}
