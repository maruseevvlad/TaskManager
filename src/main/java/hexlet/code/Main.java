package hexlet.code;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskRepository repository = new TaskList();

        List<Command> commands = List.of(
                new AddTaskCommand(repository, scanner),
                new ShowTaskCommand(repository, scanner)
        );

        new Menu(commands, scanner).run();
    }
}