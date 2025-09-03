package hexlet.code;

import lombok.Getter;

public class Tag {
    private final String name;

    public Tag(String name) {
        this.name = name.toLowerCase().trim(); // нормализация (lowercase, без пробелов)
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "#" + name;
    }
}

