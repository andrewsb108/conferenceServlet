package com.servlet.project.model.dao.impl;

import com.servlet.project.exceptions.EventAlreadyExistException;
import com.servlet.project.exceptions.UserAlreadyExistException;
import com.servlet.project.model.dao.EventDao;
import com.servlet.project.model.dao.mapper.EventMapper;
import com.servlet.project.model.entity.Event;
import com.servlet.project.util.DBQueries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.servlet.project.util.DBQueries.SQL_FIND_FROM_EVENT_IDS;

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
    public Optional<Event> save(Event event) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.SAVE_EVENT_QUERY)) {
            statement.setString(1, event.getTitle());
            statement.setObject(2, event.getScheduledDate());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex1) {
            log.warn("Attempt to create existing event [title: {}]", event.getTitle());
            throw new UserAlreadyExistException("Such event already exists: " + event.getTitle());
        } catch (SQLException e) {
            log.error("ERROR: can't provide event save operation!", e);
            return Optional.empty();
        }
        return Optional.of(event);
    }

    @Override
    public Optional<Event> update(Event event) {
        try(PreparedStatement ps = connection.prepareStatement(DBQueries.UPDATE_EVENT_QUERY)) {
            ps.setString(1, event.getTitle());
            ps.setObject(2, event.getScheduledDate());
            ps.setLong(3, event.getId());
            boolean isUpdated = ps.executeUpdate() > 0;
            if (isUpdated) {
                return Optional.of(event);
            }
        } catch (SQLIntegrityConstraintViolationException ex1) {
            throw new EventAlreadyExistException();
        } catch (SQLException ex2) {
            log.error("Can not provide event update operation", ex2);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(long id) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DBQueries.DELETE_EVENT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Can not provide event deleteById operation", e);
        }
        return false;
    }

    public static String preparePlaceHolders(int length) {
        return String.join(",", Collections.nCopies(length, "?"));
    }

    public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    @Override
    public List<Event> findByIdIn(List<Long> eventIds) {
        String sql = String.format(SQL_FIND_FROM_EVENT_IDS, preparePlaceHolders(eventIds.size()));
        try (PreparedStatement ps = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            setValues(ps, eventIds.toArray());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return eventMapper.extractAll(rs);
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide event findByIdIn operation!", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Event> delete(Event event) {
        return Optional.empty();
    }
}
