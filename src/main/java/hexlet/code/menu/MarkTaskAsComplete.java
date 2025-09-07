package hexlet.code.menu;

import hexlet.code.interfaces.Command;
import hexlet.code.TaskRepository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

public class MarkTaskAsComplete implements Command {
    private final Scanner scanner;

    public MarkTaskAsComplete(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Отметить задачу как выполненную";
    }

    @Override
    public void execute() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/sql.properties")) {
            properties.load(input);

            try (Connection connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"))) {

                // показываем только незавершённые задачи
                TaskRepository.showIncompleteTasks(connection);

                System.out.print("Введите id выполненной задачи: ");
                int id = Integer.parseInt(scanner.nextLine());

                String sql = "UPDATE tasks SET completed = 1 WHERE id = ?";

                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setInt(1, id);
                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Задача успешно обновлена!");
                    } else {
                        System.out.println("Задача с таким ID не найдена или уже выполнена.");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка при выполнении запроса: " + e.getMessage());
        }
    }
}
