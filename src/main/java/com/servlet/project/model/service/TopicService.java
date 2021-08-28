package com.servlet.project.model.service;

import com.servlet.project.model.dao.TopicDao;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.entity.Topic;

import java.util.List;

public class TopicService {

    private final TopicDao topicDao = DaoFactory.createTopicDao();

    public List<Topic> findByEventId(long eventId) {
        return topicDao.findByEventId(eventId);
    }
}
