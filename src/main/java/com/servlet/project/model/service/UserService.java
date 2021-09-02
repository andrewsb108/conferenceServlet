package com.servlet.project.model.service;

import com.servlet.project.exceptions.UserAlreadyExistException;
import com.servlet.project.model.dao.UserDao;
import com.servlet.project.model.dao.impl.UserDaoImpl;
import com.servlet.project.model.entity.Role;
import com.servlet.project.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;
    private final SecurityService securityService;
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);


    public UserService(UserDao userDao, SecurityService securityService) {
        this.userDao = userDao;
        this.securityService = securityService;
    }

    public List<User> getAllUsers() {
        log.info("Handling find all users request");
        return userDao.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(long id) {
        return userDao.findById(id);
    }

    public Optional<User> create(String firstName,
                                 String lastName,
                                 String email,
                                 String password,
                                 String role) throws UserAlreadyExistException {
        String encodedPassword = securityService.encrypt(password);
        return userDao.save(User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(encodedPassword)
                .role(Role.valueOf(role))
                .build());
    }

    public Optional<User> update(String id, String firstName, String lastName,
                                 String password, String role, String enabled) {
        User user = User.builder()
                .id(Long.parseLong(id))
                .firstName(firstName)
                .lastName(lastName)
                .role(Role.valueOf(role))
                .build();

        if (!password.isEmpty()) {
            user.setPassword(securityService.encrypt(password));
        }
        if (!enabled.isEmpty()) {
            user.setEnabled(Boolean.parseBoolean(enabled));
        }
        return userDao.update(user);
    }

    public boolean delete(long id) {
        return userDao.deleteById(id);
    }

}
