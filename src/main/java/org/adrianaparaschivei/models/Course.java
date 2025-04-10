package org.adrianaparaschivei.models;

import java.util.Objects;

public class Course {
    private String title;
    private String description;

    //@all args
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    //@getter
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return title.equals(course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
