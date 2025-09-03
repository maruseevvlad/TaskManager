package hexlet.code.interfaces;

import hexlet.code.Task;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task);
    void removeTask(Task task);
    List<Task> getTasks();
}
