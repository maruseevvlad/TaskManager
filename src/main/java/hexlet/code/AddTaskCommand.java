package hexlet.code;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class AddTaskCommand implements Command {
    private final TaskRepository repository;
    private final Scanner scanner;

    public AddTaskCommand(TaskRepository repository, Scanner scanner) {
        this.repository = repository;
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

            System.out.println("Введите дедлайн (например 2025-08-15T18:00):");
            String deadlineInput = scanner.nextLine();
            LocalDateTime deadline = LocalDateTime.parse(deadlineInput);

            System.out.println("Выберите приоритет (1 - низкий(по умолчанию), 2 - средний, 3 - высокий):");
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

            Task task = new Task(title, description, deadline, priority, Set.of(new Tag("работа")));
            repository.addTask(task);
            System.out.println("Задача добавлена: " + task);

        } catch (Exception e) {
            System.out.println("Ошибка при создании задачи: " + e.getMessage());
        }
    }
}
