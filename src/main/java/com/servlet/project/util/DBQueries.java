package com.servlet.project.util;

public class DBQueries {
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE users.id = ?";
    public static final String FIND_BY_USER_EMAIL_QUERY = "SELECT * FROM users WHERE users.email = ?";
    public static final String SAVE_USER_QUERY = "INSERT INTO users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    public static final String UPDATE_USER_QUERY = "UPDATE users SET first_name = ?, last_name = ?, password = ?, enabled = ?, role = ? WHERE id = ?";
    public static final String UPDATE_USER_SAME_PASSWORD_QUERY = "UPDATE users SET first_name = ?, last_name = ?, enabled = ?, role = ? WHERE id = ?";
}

