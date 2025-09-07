package hexlet.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRepository {

    public static void showAllTasks(Connection connection) throws SQLException {
        String sql = "SELECT id, title, description, deadline, priority, tags, status, completed FROM tasks";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Список всех задач:");
            while (rs.next()) {
                printTask(rs);
            }
        }
    }

    public static void showIncompleteTasks(Connection connection) throws SQLException {
        String sql = "SELECT id, title, description, deadline, priority, tags, status, completed " +
                "FROM tasks WHERE completed = 0";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Незавершённые задачи:");
            while (rs.next()) {
                printTask(rs);
            }
        }
    }

    private static void printTask(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        String deadline = rs.getString("deadline");
        String priority = rs.getString("priority");
        String tags = rs.getString("tags");
        String status = rs.getString("status");
        boolean completed = rs.getBoolean("completed");

        System.out.printf(
                "ID: %d%nНазвание: %s%nОписание: %s%nДедлайн: %s%nПриоритет: %s%nТеги: %s%nСтатус: %s%nВыполнено: %s%n",
                id, title, description, deadline, priority, tags, status, completed ? "✔" : " "
        );
        System.out.println("-----------------------------------");
    }
}
