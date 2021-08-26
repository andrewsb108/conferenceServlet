package com.servlet.project.model.dao.impl;

import com.servlet.project.model.dao.EventDao;
import com.servlet.project.model.dao.TopicDao;
import com.servlet.project.model.dao.UserDao;

import static com.servlet.project.model.dao.impl.ConnectionManager.getConnection;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    public static EventDao createEventDao() {
        return new EventDaoImpl(getConnection());
    }

    public static TopicDao createTopicDao() {
        return new TopicDaoImpl(getConnection());
    }
}
