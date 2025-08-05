package hexlet.code;

import java.util.ArrayList;
import java.util.List;

public class TaskList implements TaskRepository{
    private static final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        if (task != null) {
            tasks.remove(task);
        }
    }

    public List<Task> getTasks() {
            return tasks;
    }
}
