package hexlet.code;

import hexlet.code.interfaces.Command;
import hexlet.code.interfaces.TaskRepository;
import hexlet.code.menu.AddTaskCommand;
import hexlet.code.menu.DeleteTaskCommand;
import hexlet.code.menu.Menu;
import hexlet.code.menu.ShowTaskCommand;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskRepository repository = new TaskList();

        List<Command> commands = List.of(
                new AddTaskCommand(repository, scanner),
                new ShowTaskCommand(repository, scanner),
                new DeleteTaskCommand(scanner)
        );

        new Menu(commands, scanner).run();
    }
}