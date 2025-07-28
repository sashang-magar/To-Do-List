package dao;

import db.DBConnection;
import model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public void addTask(Task task) {
        String sql = "INSERT INTO tasks(name) VALUES(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> list = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Task(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET name = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getName());
            stmt.setInt(2, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
