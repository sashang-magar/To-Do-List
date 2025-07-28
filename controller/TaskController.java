package controller;

import dao.TaskDAO;
import model.Task;
import java.util.List;

public class TaskController {
    TaskDAO dao = new TaskDAO();

    public void addTask(String name) {
        dao.addTask(new Task(name));
    }

    public List<Task> getTasks() {
        return dao.getAllTasks();
    }

    public void updateTask(int id, String name) {
        dao.updateTask(new Task(id, name));
    }

    public void deleteTask(int id) {
        dao.deleteTask(id);
    }
}
