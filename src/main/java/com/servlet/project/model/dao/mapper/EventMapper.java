package com.servlet.project.model.dao.mapper;

import com.servlet.project.model.entity.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventMapper implements ObjectMapper<Event> {

    @Override
    public Event extract(ResultSet rs) throws SQLException {
        return Optional.ofNullable(Event.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("first_name"))
                .topicId(rs.getLong("last_name"))
                .participantId(rs.getLong("participant"))
                .scheduledDate(rs.getObject(5, LocalDateTime.class))
                .build()).orElseThrow(SQLException::new);
    }

    @Override
    public List<Event> extractAll(ResultSet rs) throws SQLException {
        List<Event> list = new ArrayList<>();
        rs.beforeFirst();

        while (rs.next()) {
            Event event = extract(rs);
            list.add(event);
        }
        return list;
    }
}
