package hexlet.code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Task {
    private boolean status;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Priority priority;
    @Getter
    @Setter
    private static Set<Tag> tags = new HashSet<>();

    public Task(String title, String description, LocalDateTime deadline,
                Priority priority, Set<Tag> tags) {
        this.title = title;
        this.description = description;
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("deadline must be after now");
        }
        this.deadline = deadline;
        this.priority = priority;
        this.status = true;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Task{" +
                "status=" + status +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", tags=" + (Task.getTags() != null ? Task.getTags().stream()
                .map(tag -> "#" + tag.getName())
                .collect((Collectors.joining(", "))) :
                "нет тэга") +
                '}';
    }
}
