package com.servlet.project.model.service;

import com.servlet.project.model.dao.UserDao;
import com.servlet.project.model.dao.impl.UserDaoImpl;
import com.servlet.project.model.entity.Role;
import com.servlet.project.model.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserDao userDaoMock = mock(UserDaoImpl.class);
    private final SecurityService securityServiceMock = mock(SecurityService.class);
    private final UserService userService = spy(new UserService(securityServiceMock));

    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "user@mail.com";
    private final String password = "1234";
    private final Role role = Role.USER;

    @Test
    void create_test() {

    }

    @Test
    void getAllUsers() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}