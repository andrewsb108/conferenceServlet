package com.servlet.project.model.dao.impl;

import com.servlet.project.exceptions.EventAlreadyExistException;
import com.servlet.project.exceptions.TopicAlreadyExistException;
import com.servlet.project.model.dao.TopicDao;
import com.servlet.project.model.dao.mapper.TopicMapper;
import com.servlet.project.model.entity.Topic;
import com.servlet.project.util.DBQueries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicDaoImpl implements TopicDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private Connection connection;
    private final TopicMapper topicMapper = new TopicMapper();

    @Override
    public List<Topic> findByEventId(long eventId) {
        try (PreparedStatement ps = connection.prepareStatement(DBQueries.FIND_BY_EVENT_ID_FROM_TOPIC_QUERY,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            ps.setLong(1, eventId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               return topicMapper.extractAll(rs);
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide topic findByEventId operation!", e);
        }
        return new ArrayList<>();
    }

    public TopicDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Topic> findById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(
                DBQueries.FIND_TOPIC_BY_ID_QUERY)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.ofNullable(topicMapper.extract(rs));
            }
        } catch (SQLException e) {
            log.warn("ERROR: can't provide topic findById operation!", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Topic> findAll() {
        return null;
    }

    @Override
    public Optional<Topic> save(Topic topic) {
        try (PreparedStatement ps =
                     connection.prepareStatement(DBQueries.SAVE_TOPIC_QUERY)) {
            ps.setString(1, topic.getTitle());
            ps.setLong(2, topic.getEventId());
            boolean saved = ps.executeUpdate() > 0;
            if (saved) {
                return Optional.of(topic);
            }
        } catch (SQLIntegrityConstraintViolationException ex1) {
            log.info("Attempt to create an existing topic: {}", topic.getTitle());
            throw new EventAlreadyExistException();
        } catch (SQLException ex2) {
            log.error("Can not provide topic save operation", ex2);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Topic> updateTopic(Topic topic) {
        try (PreparedStatement ps =
                     connection.prepareStatement(DBQueries.UPDATE_TOPIC_QUERY)) {
            ps.setLong(1, topic.getSpeakerId());
            ps.setLong(2, topic.getEventId());
            ps.setLong(3, topic.getId());
            boolean isUpdated = ps.executeUpdate() > 0;
            if (isUpdated) {
                return Optional.of(topic);
            }
        } catch (SQLIntegrityConstraintViolationException ex1) {
            throw new TopicAlreadyExistException();
        } catch (SQLException ex2) {
            log.error("Can not provide updateTopic update operation", ex2);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Topic> update(Topic topic) {
        return Optional.empty();
    }

    @Override
    public Optional<Topic> delete(Topic topic) {
        return Optional.empty();
    }
}
