package org.adrianaparaschivei.models;

public abstract class User {
    private static long nextId = 1;

    private final long id;
    private String firstName;
    private String lastName;
    private final String username;
    private final String email;
    private String password;

    public abstract String getDetails();

    public User(String firstName, String lastName, String username, String email, String password) {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
