package org.example.todoapp.dao;

import com.mysql.cj.protocol.Resultset;
import org.example.todoapp.model.TaskModel;

import java.sql.*;
import java.util.ArrayList;

public class TaskDao {

    public TaskDao() {
    }

    public static void saveTask(TaskModel task) throws SQLException {

        String query = "INSERT INTO tasks (description, priority) VALUES (?, ?);";

        try (PreparedStatement statement = DbConn.getInstance().conn.prepareStatement(query)) {

            statement.setString(1, task.getDescription());
            statement.setString(2, task.getPriority());

            statement.execute();
        }
    }

    public static ArrayList<TaskModel> getAllTasks() throws SQLException {

        String query = "SELECT * FROM tasks;";

        Statement stmt = DbConn.getInstance().conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);

        ArrayList<TaskModel> tasks = new ArrayList<>();

        while(resultSet.next()) {
        String description = resultSet.getString("description");
        String priority = resultSet.getString("priority");
        TaskModel task = new TaskModel(description, priority);
        tasks.add(task);
        }
        return tasks;
    }

}
