package hexlet.code;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int userInput = 1;

        do {
            System.out.println("Выберите действие:" + "\n"
                    + "1. Создать задачу" + "\n"
                    + "2. Просмотреть список задач" + "\n"
                    + "3. Завершить задачу" + "\n"
                    + "4. Удалить задачу" + "\n"
                    + "0. Выход" + "\n"
            );
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                Set<Tag> tags = Set.of(new Tag("hexlet"));

                Task task = new Task("Название",
                "Описание",
                LocalDateTime.parse("2026-07-20T18:30"),
                Priority.HIGH,
                tags
                );
                tasks.add(task);
                    break;
                case 2:
                    if (!tasks.isEmpty()) {
                        for (Task t : tasks) {
                            System.out.println(t.toString());
                        }
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    break;
            }
        } while (userInput != 0);



//        Set<Tag> tags = Set.of(new Tag("hexlet"));
//
//        Task task = new Task("Название",
//                "Описание",
//                LocalDateTime.parse("2026-07-20T18:30"),
//                Priority.HIGH,
//                tags
//                );
//        System.out.println(task.toString());
    }
}