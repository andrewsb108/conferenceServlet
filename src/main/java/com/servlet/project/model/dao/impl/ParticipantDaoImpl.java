package com.servlet.project.model.dao.impl;

import com.servlet.project.exceptions.UniqueParticipantException;
import com.servlet.project.model.dao.ParticipantDao;
import com.servlet.project.model.entity.Participant;
import com.servlet.project.util.DBQueries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ParticipantDaoImpl implements ParticipantDao {

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private Connection connection;

    public ParticipantDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Participant> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Participant> findAll() {
        return null;
    }

    @Override
    public boolean save(Participant participant) {
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
        return true;
    }

    @Override
    public Optional<Participant> update(Participant participant) {
        return Optional.empty();
    }

    @Override
    public Optional<Participant> delete(Participant participant) {
        return Optional.empty();
    }
}
