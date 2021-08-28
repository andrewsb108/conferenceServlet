package com.servlet.project.model.dao.mapper;

import com.servlet.project.model.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicMapper implements ObjectMapper<Topic> {

    @Override
    public Topic extract(ResultSet rs) throws SQLException {

        return Topic.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .speakerId(rs.getLong("speaker_id"))
                .eventId(rs.getLong("event_id")).build();
    }

    @Override
    public List<Topic> extractAll(ResultSet rs) throws SQLException {

        List<Topic> list = new ArrayList<>();
        rs.beforeFirst();

        while (rs.next()) {
            Topic topic = extract(rs);
            list.add(topic);
        }
        return list;
    }
}
