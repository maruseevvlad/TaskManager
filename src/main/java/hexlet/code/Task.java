package hexlet.code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Task {
    private boolean completed;
    private TaskStatus status;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Priority priority;
    @Getter
    private final Set<Tag> tags;

    public Task(String title, String description, LocalDateTime deadline,
                Priority priority, Set<Tag> tags) {
        this.title = title;
        this.description = description;
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("deadline must be after now");
        }
        this.deadline = deadline;
        this.priority = priority;
        this.completed = true;
        this.status = TaskStatus.NEW;
        this.tags = new HashSet<>(tags);
    }

    @Override
    public String toString() {
        return "Task{" +
                "completed=" + completed +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", tags=" + (tags != null ? tags.stream()
                .map(Tag::toString)
                .collect(Collectors.joining(", ")) :
                "нет тэга") +
                '}';

    }
}
