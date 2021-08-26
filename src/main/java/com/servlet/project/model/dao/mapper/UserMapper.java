package com.servlet.project.model.dao.mapper;

import com.servlet.project.model.entity.Event;
import com.servlet.project.model.entity.Role;
import com.servlet.project.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper implements ObjectMapper<User> {
    private static final Logger log = LogManager.getLogger(UserMapper.class);

    @Override
    public User extract(ResultSet rs) throws SQLException {
        return Optional.ofNullable(User.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .enabled(rs.getBoolean("enabled"))
                .role(Role.valueOf(rs.getString("role")))
                .build()).orElseThrow(SQLException::new);
    }

    @Override
    public List<User> extractAll(ResultSet rs) throws SQLException {
        List<User> list = new ArrayList<>();
        rs.beforeFirst();

        while (rs.next()) {
            User user = extract(rs);
            list.add(user);
        }
        return list;
    }
}
