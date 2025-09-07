package hexlet.code.menu;

import hexlet.code.interfaces.Command;
import hexlet.code.TaskRepository;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;

public class ShowTaskCommand implements Command {
    private final Scanner scanner;

    public ShowTaskCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Показать задачи";
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

                TaskRepository.showAllTasks(connection);

            }
        } catch (Exception e) {
            System.out.println("Ошибка при показе задач: " + e.getMessage());
        }
    }
}
