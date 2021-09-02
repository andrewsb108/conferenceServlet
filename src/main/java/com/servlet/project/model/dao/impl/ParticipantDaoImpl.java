package com.servlet.project.model.dao.impl;

import com.servlet.project.exceptions.UniqueParticipantException;
import com.servlet.project.model.dao.ParticipantDao;
import com.servlet.project.model.dao.mapper.ParticipantMapper;
import com.servlet.project.model.entity.Participant;
import com.servlet.project.util.DBQueries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipantDaoImpl implements ParticipantDao {

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);
    private final ParticipantMapper participantMapper = new ParticipantMapper();

    private Connection connection;

    public ParticipantDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Participant> findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(DBQueries.FIND_PARTICIPANT_BY_ID_QUERY)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(participantMapper.extract(rs));
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide participant findById operation!", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Participant> findByUserId(long userId) {
        try (PreparedStatement ps = connection.prepareStatement(DBQueries.FIND_BY_USER_ID_FROM_PARTICIPANT_QUERY,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return participantMapper.extractAll(rs);
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide participant findByEventId operation!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Participant> save(Participant participant) {
        try (PreparedStatement ps = connection.prepareStatement(DBQueries.SAVE_PARTICIPANTS_QUERY)) {
            ps.setBoolean(1, participant.isSpeaker());
            ps.setString(2, participant.getNickName());
            ps.setLong(3, participant.getUserId());
            ps.setLong(4, participant.getEventId());
            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new UniqueParticipantException();
        } catch (SQLException ex) {
            log.warn("ERROR: can't provide participant save operation!", ex);
        }
        return Optional.of(participant);
    }

    @Override
    public Optional<Participant> update(Participant participant) {
        return Optional.empty();
    }

    @Override
    public Optional<Participant> delete(Participant participant) {
        return Optional.empty();
    }

    @Override
    public List<Participant> findAll() {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.FIND_ALL_PARTICIPANTS_QUERY,
                             ResultSet.TYPE_SCROLL_INSENSITIVE,
                             ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return participantMapper.extractAll(rs);
            }
        } catch (SQLException e) {
            log.error("ERROR: can't provide participants findAll operation!", e);
        }
        return new ArrayList<>();
    }
}
