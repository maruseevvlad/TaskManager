package hexlet.code;

import java.util.*;

public class Menu {
    private final List<Command> commands;
    private final Scanner scanner;

    public Menu(List<Command> commands, Scanner scanner) {
        this.commands = commands;
        this.scanner = scanner;
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n=== TaskMaster Pro ===");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i).getName());
            }
            System.out.println("0. Выход");

            try {
                System.out.print("Выбор: ");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    System.out.println("До свидания!");
                    break;
                }
                if (choice > 0 && choice <= commands.size()) {
                    commands.get(choice - 1).execute();
                } else {
                    System.out.println("Некорректный выбор!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число, пожалуйста.");
                choice = -1; // чтобы цикл не завершился
            }

        } while (true);
    }
}

