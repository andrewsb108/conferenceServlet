package com.servlet.project.util;

public class DBQueries {
    public static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE users.id = ?";
    public static final String FIND_BY_USER_EMAIL_QUERY = "SELECT * FROM users WHERE users.email = ?";
    public static final String SAVE_USER_QUERY = "INSERT INTO users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    public static final String UPDATE_USER_QUERY = "UPDATE users SET first_name = ?, last_name = ?, password = ?, enabled = ?, role = ? WHERE id = ?";
    public static final String UPDATE_USER_BY_ROLE_QUERY = "UPDATE users set role = ? WHERE id = ?";

    public static final String FIND_EVENT_BY_ID_QUERY = "SELECT * FROM events WHERE id = ?";
    public static final String FIND_ALL_EVENTS_QUERY = "SELECT * FROM events";
    public static final String SAVE_EVENT_QUERY = "INSERT INTO events (title, scheduled_date) VALUES (?, ?)";

    public static final String SAVE_PARTICIPANTS_QUERY = "INSERT INTO participants (is_speaker, nick_name, user_id, event_id) VALUES (?, ?, ?, ?)";

    public static final String FIND_BY_EVENT_ID_FROM_TOPIC_QUERY = "SELECT * FROM topics WHERE event_id = ?";
    public static final String DELETE_EVENT_BY_ID_QUERY = "DELETE FROM events WHERE id = ?";
    public static final String UPDATE_EVENT_QUERY = "UPDATE events SET title = ?, scheduled_date = ? WHERE id = ?";

    public static final String SAVE_TOPIC_QUERY = "INSERT INTO topics (title, event_id) VALUES (?, ?)";
    public static final String FIND_TOPIC_BY_ID_QUERY = "SELECT * FROM topics WHERE id = ?";
    public static final String UPDATE_TOPIC_QUERY = "UPDATE topics SET speaker_id = ?, event_id = ? WHERE id = ?";


}

