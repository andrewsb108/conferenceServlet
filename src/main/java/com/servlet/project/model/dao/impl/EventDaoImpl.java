package com.servlet.project.model.dao.impl;

import com.servlet.project.exceptions.UserAlreadyExistException;
import com.servlet.project.model.dao.EventDao;
import com.servlet.project.model.dao.mapper.EventMapper;
import com.servlet.project.model.entity.Event;
import com.servlet.project.util.DBQueries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventDaoImpl implements EventDao {
    private static final Logger log = LogManager.getLogger(EventDaoImpl.class);
    private Connection connection;
    private final EventMapper eventMapper = new EventMapper();


    public EventDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Event> findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(DBQueries.FIND_EVENT_BY_ID_QUERY)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(eventMapper.extract(rs));
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide event findById operation!", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Event> findAll() {
        try (PreparedStatement ps = connection.prepareStatement(DBQueries.FIND_ALL_EVENTS_QUERY,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return eventMapper.extractAll(rs);
            }
        } catch (SQLException e) {
            log.error("ERROR: can't provide event findAll operation!", e);
        }
        return null;
    }

    @Override
    public boolean save(Event event) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.SAVE_EVENT_QUERY)) {
            statement.setString(1, event.getTitle());
            statement.setObject(2, LocalDateTime.class);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex1) {
            log.warn("Attempt to create existing event [title: {}]", event.getTitle());
            throw new UserAlreadyExistException("Such event already exists: " + event.getTitle());
        } catch (SQLException e) {
            log.error("ERROR: can't provide event save operation!", e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<Event> update(Event event) {
        return Optional.empty();
    }

    @Override
    public Optional<Event> delete(Event event) {
        return Optional.empty();
    }
}
