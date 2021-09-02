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
        var user = Optional.ofNullable(User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(role)
                .build());
        given(testInstance.create(any(), any(), any(), any(), any())).willReturn(user);
//        given(testInstance.create(firstName, lastName, email, password, role.name())).willReturn(user);


        verify(securityService).encrypt(password);
        verify(userDao).save(user.get());
    }

//    @Test
//    void whenPassChanged_update_test() {
//        User user = User.builder()
//                .id(3)
//                .firstName("firstName")
//                .lastName("lastName")
//                .email("user@mail.com")
//                .role(Role.USER)
//                .build();
//
//        when(userDaoMock.update(any(User.class))).thenReturn(Optional.of(user));
//
//        userService.update("3", "firstName",
//                "lastName", "123", Role.USER.name(), "");
//
//        verify(securityServiceMock).encrypt("123");
//        verify(userDaoMock).update(any(User.class));
//    }
//
//    @Test
//    void findAll_test() {
//        userService.findAll();
//        verify(userDaoMock).findAll();
//    }
//
//    @Test
//    void findById_test() {
//        long id = 12;
//        userService.findById(id);
//        verify(userDaoMock).findById(id);
//    }
//
//    @Test
//    void delete_test() {
//        long id = 12;
//        userService.delete(id);
//        verify(userDaoMock).deleteById(id);
//    }
}