package com.servlet.project.util;

import com.servlet.project.controller.command.*;
import com.servlet.project.model.service.EventService;
import com.servlet.project.model.service.SecurityService;
import com.servlet.project.model.service.TopicService;
import com.servlet.project.model.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static com.servlet.project.util.ViewResolver.resolve;

public class CommandResolver {

    private static final SecurityService securityService = new SecurityService();
    private static final UserService userService = new UserService(securityService);
    private static final EventService eventService = new EventService();
    private static final TopicService topicService = new TopicService();

    private static final Map<String, Command> commands = new HashMap<>() {{
        put("logout", new Logout());
        put("login", new Login(userService, securityService));
        put("registration", new Registration(userService));
        put("index", new Index(eventService));
        put("register/event", new RegisterToEvent(eventService, securityService));
        put("event/all", new ModeratorGetAllEvents(eventService));
        put("event/create", new ModeratorCreateEvent(eventService));
        put("event/edit", new ModeratorEditEvent(eventService, topicService));

    }};

    public static Command getCommand(String path) {
        return commands.getOrDefault(path, (def) -> resolve("login"));
    }

}
