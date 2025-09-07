package hexlet.code;

import hexlet.code.interfaces.Command;
import hexlet.code.menu.*;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Command> commands = List.of(
                new AddTaskCommand(scanner),
                new ShowTaskCommand(scanner),
                new DeleteTaskCommand(scanner),
                new UpdateTaskCommand(scanner),
                new MarkTaskAsComplete(scanner)
        );

        new Menu(commands, scanner).run();
    }
}