package hexlet.code;

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
        if (repository.getTasks().isEmpty()) {
            System.out.println("Список задач пуст");
        } else {
            System.out.println("Список задач:");
            repository.getTasks().forEach(System.out::println);
        }
    }
}
