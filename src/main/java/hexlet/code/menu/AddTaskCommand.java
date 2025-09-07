package hexlet.code.menu;

import hexlet.code.Priority;
import hexlet.code.Tag;
import hexlet.code.Task;
import hexlet.code.TaskStatus;
import hexlet.code.interfaces.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class AddTaskCommand implements Command {
    private final Scanner scanner;

    public AddTaskCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Создать задачу";
    }

    @Override
    public void execute() {
        try {
            System.out.println("Введите название задачи:");
            String title = scanner.nextLine();

            System.out.println("Введите описание:");
            String description = scanner.nextLine();

            System.out.println("Введите дедлайн (например 2029-08-15T18:00):");
            String deadlineInput = scanner.nextLine();
            LocalDateTime deadline = LocalDateTime.parse(deadlineInput);

            System.out.println("Выберите приоритет (1 - низкий, 2 - средний, 3 - высокий):");
            Priority priority = Priority.LOW;
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    priority = Priority.LOW;
                    break;
                case 2:
                    priority = Priority.MEDIUM;
                    break;
                case 3:
                    priority = Priority.HIGH;
                    break;
                default:
                    System.out.println("Неверный выбор");
            }

            System.out.println("Введите теги через запятую (например: работа, учеба, urgent):");
            String tagsInput = scanner.nextLine();


            Set<Tag> tags = Arrays.stream(tagsInput.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Tag::new)
                    .collect(Collectors.toSet());

            Properties properties = new Properties();

            try (InputStream input = new FileInputStream("src/main/resources/sql.properties")) {
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String sql = "INSERT INTO tasks (title, description, deadline, priority, tags, status, completed) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

                pstmt.setString(1, title);
                pstmt.setString(2, description);
                pstmt.setString(3, deadline.toString());
                pstmt.setString(4, priority.name());
                pstmt.setString(5, String.valueOf(tags));
                pstmt.setString(6, String.valueOf(TaskStatus.NEW));
                pstmt.setInt(7, 0);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Задача успешно добавлена в базу данных!");
                }

            }

        } catch (Exception e) {
            System.out.println("Ошибка при создании задачи: " + e.getMessage());
        }
    }
}
