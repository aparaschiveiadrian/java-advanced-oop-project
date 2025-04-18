package org.adrianaparaschivei.models;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {
    private final Set<Course> ownedCourses = new HashSet<>();

    public Teacher(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName, username, email, password);
    }

    public Set<Course> getOwnedCourses() {
        return ownedCourses;
    }

    @Override
    public String getDetails() {
        return "Teacher: " + getFirstName() + " " + getLastName() + ", username: " + getUsername();
    }
}
