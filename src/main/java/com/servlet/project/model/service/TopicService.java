package com.servlet.project.model.service;

import com.servlet.project.model.dao.TopicDao;
import com.servlet.project.model.dao.impl.DaoFactory;

public class TopicService {

    private final TopicDao topicDao = DaoFactory.createTopicDao();
}
