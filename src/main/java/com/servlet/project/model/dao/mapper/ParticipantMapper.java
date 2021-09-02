package com.servlet.project.model.dao.mapper;

import com.servlet.project.model.entity.Participant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipantMapper implements ObjectMapper<Participant> {
    @Override
    public Participant extract(ResultSet rs) throws SQLException {
        return Optional.ofNullable(Participant.builder()
                .id(rs.getLong("id"))
                .isSpeaker(rs.getBoolean("is_speaker"))
                .nickName(rs.getString("nick_name"))
                .userId(rs.getLong("user_id"))
                .eventId(rs.getLong("event_id"))
                .build()).orElseThrow(SQLException::new);
    }

    @Override
    public List<Participant> extractAll(ResultSet rs) throws SQLException {
        List<Participant> list = new ArrayList<>();
        rs.beforeFirst();

        while (rs.next()) {
            Participant participant = extract(rs);
            list.add(participant);
        }
        return list;
    }
}
