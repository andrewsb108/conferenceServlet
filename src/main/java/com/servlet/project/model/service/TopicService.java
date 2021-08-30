package com.servlet.project.model.service;

import com.servlet.project.model.dao.TopicDao;
import com.servlet.project.model.dao.UserDao;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.dto.TopicDto;
import com.servlet.project.model.entity.Topic;
import com.servlet.project.model.entity.User;

import java.util.List;
import java.util.Optional;

public class TopicService {

    private final TopicDao topicDao = DaoFactory.createTopicDao();
    private final UserDao userDao = DaoFactory.createUserDao();

    public List<Topic> findByEventId(long eventId) {
        return topicDao.findByEventId(eventId);
    }

    public Topic getTopic(long topicId) {
        return topicDao.findById(topicId).orElseThrow();
    }

    public void assignSpeaker(long eventId, long topicId, long speakerId) {
        Optional<User> speaker = userDao.findById(topicId);
        Optional<Topic> topic = topicDao.findById(speakerId);

//        topic.setSpeaker(speaker);
        topicDao.save(topic.orElseThrow());
    }
}
