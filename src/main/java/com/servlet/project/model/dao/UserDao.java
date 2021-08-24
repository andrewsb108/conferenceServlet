package com.servlet.project.model.dao;

import com.servlet.project.model.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByEmail(String email);
    boolean deleteById(long id);
}
