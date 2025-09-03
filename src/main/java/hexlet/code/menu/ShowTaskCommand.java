package hexlet.code.menu;

import hexlet.code.interfaces.Command;
import hexlet.code.interfaces.TaskRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ShowTaskCommand implements Command {
    private final TaskRepository repository;
    private final Scanner scanner;

    public ShowTaskCommand(TaskRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Показать задачи";
    }

    @Override
    public void execute() {
        try {
            Properties properties = new Properties();
            try (InputStream input = new FileInputStream("src/main/resources/sql.properties")) {
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String sql = "SELECT * FROM tasks";

            try (Connection connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                boolean hasTask = false;

                while (resultSet.next()) {
                    hasTask = true;
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String deadline = resultSet.getString("deadline");
                    String priority = resultSet.getString("priority");
                    String tags = resultSet.getString("tags");
                    String status = resultSet.getString("status");
                    boolean completed = resultSet.getBoolean("completed");

                    System.out.printf(
                            "ID: %d | Название: %s | Описание: %s | Дедлайн: %s | Приоритет: %s | Теги: %s | Статус: %s | Завершено: %s%n",
                            id, title, description, deadline, priority, tags, status, completed
                    );
                }

                if (!hasTask) {
                    System.out.println("Список задач пуст");
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка при показе задач: " + e.getMessage());
        }
    }
}
