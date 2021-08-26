package com.servlet.project.model.dao.impl;

import com.servlet.project.model.dao.TopicDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.Topic;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class TopicDaoImpl implements TopicDao {
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private Connection connection;

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
    public Optional<Topic> save(Topic topic) {
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
