package hexlet.code.menu;

import hexlet.code.TaskStatus;
import hexlet.code.interfaces.Command;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DeleteTaskCommand implements Command {
    private final Scanner scanner;

    public DeleteTaskCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Удалить задачу";
    }

    @Override
    public void execute() {
        try {
            Properties properties = new Properties();
            try (InputStream input = new FileInputStream("src/main/resources/sql.properties")) {
                properties.load(input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            String sql = "DELETE FROM tasks WHERE id = ?";

            try (Connection connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {

                System.out.print("Введите ID задачи для удаления: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Задача успешно удалена.");
                    } else {
                        System.out.println("Задача не найдена.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            System.out.println("Ошибка при удалении задачи");
        }
    }
}
