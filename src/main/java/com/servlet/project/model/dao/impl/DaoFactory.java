package com.servlet.project.model.dao.impl;

import com.servlet.project.model.dao.UserDao;

import static com.servlet.project.model.dao.impl.ConnectionManager.getConnection;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }
}
