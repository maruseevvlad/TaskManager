package hexlet.code;

import lombok.Getter;

public class Tag {
    @Getter
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public static boolean createTag(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        boolean tagExists = Task.getTags().stream().anyMatch(tag -> tag.getName().equals(name));
        if (tagExists) {
            throw new IllegalArgumentException("Tag already exists");
        }
        Task.getTags().add(new Tag(name));
        return true;
    }

    public static boolean removeTag(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        } else {
            Task.getTags().removeIf(tag -> tag.getName().equals(name));
            return true;
        }
    }

    public static Tag findByName(String name) {
        return Task.getTags().stream()
                .filter(tag -> tag.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "#" + name;
    }

}
