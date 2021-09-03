package com.servlet.project.model.service;

import com.servlet.project.model.dao.UserDao;
import com.servlet.project.model.entity.Role;
import com.servlet.project.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;
    @Mock
    private SecurityService securityService;
    @Mock
    private User user;

    @InjectMocks
    private UserService testInstance;

    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "user@mail.com";
    private final String password = "1234";
    private final Role role = Role.USER;

    @Test
    void shouldReturnAllUsers() {
        List<User> expectedUsers = List.of(user);
        given(userDao.findAll()).willReturn(expectedUsers);

        List<User> actualUsers = testInstance.getAllUsers();

        assertThat(actualUsers).isEqualTo(expectedUsers);
        verify(userDao).findAll();
    }

    @Test
    void create_test() {
        //given
        var user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(role)
                .build();

        String encodedPassword = "sadfghjklkuyjth456";
        given(securityService.encrypt(password)).willReturn(encodedPassword);
        given(userDao.save(user)).willReturn(Optional.of(user));

        //when
        Optional<User> actualUser = testInstance.create(firstName, lastName, email, password, role.name());

        //than
        assertThat(actualUser.get()).isEqualTo(user);
        verify(securityService).encrypt(password);
        verify(userDao).save(user);
    }

    @Test
    void when_update_test() {
        //given
        User user = User.builder()
                .id(1)
                .firstName("firstName")
                .lastName("lastName")
                .email("user@mail.com")
                .role(Role.USER)
                .build();

        when(userDao.update(any(User.class))).thenReturn(Optional.of(user));

        //when
        testInstance.update("3", "firstName",
                "lastName", "123", Role.USER.name(), "");

        //than
        verify(securityService).encrypt("123");
        verify(userDao).update(any(User.class));
    }

    @Test
    void findAll_test() {
        testInstance.findAll();
        verify(userDao).findAll();
    }

    @Test
    void findById_test() {
        long id = 1;
        testInstance.findById(id);
        verify(userDao).findById(id);
    }

    @Test
    void delete_test() {
        long id = 1;
        testInstance.delete(id);
        verify(userDao).deleteById(id);
    }
}