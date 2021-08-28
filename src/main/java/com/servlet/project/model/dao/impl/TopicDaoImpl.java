package com.servlet.project.model.dao.impl;

import com.servlet.project.model.dao.TopicDao;
import com.servlet.project.model.dao.mapper.TopicMapper;
import com.servlet.project.model.entity.Topic;
import com.servlet.project.util.DBQueries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return Optional.empty();
    }

    @Override
    public List<Topic> findAll() {
        return null;
    }

    @Override
    public boolean save(Topic topic) {
        return false;
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
