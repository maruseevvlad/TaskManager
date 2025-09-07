package hexlet.code.menu;

import hexlet.code.Priority;
import hexlet.code.Tag;
import hexlet.code.TaskStatus;
import hexlet.code.interfaces.Command;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class UpdateTaskCommand implements Command {
    private final Scanner scanner;

    public UpdateTaskCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Обновить задачу";
    }

    @Override
    public void execute() {
        ShowTaskCommand showTasksCommand = new ShowTaskCommand(scanner);
        showTasksCommand.execute();

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/sql.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("Введите id задачи, которую хотите обновить: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            System.out.print("Введите название задачи: ");
            String title = scanner.nextLine();

            System.out.print("Введите описание: ");
            String description = scanner.nextLine();

            System.out.print("Введите дедлайн (например 2029-08-15T18:00): ");
            LocalDateTime deadline = LocalDateTime.parse(scanner.nextLine());

            System.out.print("Выберите приоритет (1 - низкий, 2 - средний, 3 - высокий): ");
            Priority priority = Priority.LOW;
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 2 -> priority = Priority.MEDIUM;
                case 3 -> priority = Priority.HIGH;
            }

            System.out.print("Введите теги через запятую (например: работа, учеба, urgent): ");
            Set<Tag> tags = Arrays.stream(scanner.nextLine().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Tag::new)
                    .collect(Collectors.toSet());

            String sql = "UPDATE tasks " +
                    "SET title = ?, description = ?, deadline = ?, priority = ?, tags = ?, status = ?, completed = ? " +
                    "WHERE id = ?";

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
                pstmt.setString(6, TaskStatus.NEW.name());
                pstmt.setBoolean(7, false);
                pstmt.setInt(8, id);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Задача успешно обновлена!");
                } else {
                    System.out.println("Задача с таким ID не найдена.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обновлении задачи: " + e.getMessage());
        }
    }
}
