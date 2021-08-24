package com.servlet.project.util;

import com.servlet.project.controller.command.Command;
import com.servlet.project.controller.command.Login;
import com.servlet.project.controller.command.Logout;
import com.servlet.project.controller.command.Registration;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.service.SecurityService;
import com.servlet.project.model.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static com.servlet.project.util.ViewResolver.resolve;

public class CommandResolver {

    private static final SecurityService securityService = new SecurityService();
    private static final UserService userService = new UserService(DaoFactory.createUserDao(), securityService);

    private static final Map<String, Command> commands = new HashMap<>() {{
        put("logout", new Logout());
        put("login", new Login(userService, securityService));
        put("registration", new Registration(userService));

    }};

    public static Command getCommand(String path) {
        return commands.getOrDefault(path, (def) -> resolve("login"));
    }

}
