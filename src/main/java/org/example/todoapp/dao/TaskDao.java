package org.example.todoapp.dao;

import org.example.todoapp.model.TaskModel;

import java.sql.*;
import java.util.ArrayList;

public class TaskDao {

    public TaskDao() {
    }

    public static void saveTask(TaskModel task) throws SQLException {

        String sql = "INSERT INTO tasks (description, priority) VALUES (?, ?);";

        try (PreparedStatement statement = DbConn.getInstance().conn.prepareStatement(sql)) {

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

        int idTask = resultSet.getInt("idtask");
        String description = resultSet.getString("description");
        String priority = resultSet.getString("priority");
        TaskModel task = new TaskModel(idTask, description, priority);
        tasks.add(task);
        }
        return tasks;
    }

    public static void deleteTask(TaskModel task) throws SQLException {

        String sql = "DELETE FROM tasks WHERE idtask = ?;";

        try (PreparedStatement statement = DbConn.getInstance().conn.prepareStatement(sql)) {
            statement.setInt(1, task.getIdTask());

            statement.execute();
        }
    }

}
