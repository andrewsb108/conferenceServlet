package com.servlet.project.util;

import com.servlet.project.controller.command.*;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.service.*;

import java.util.HashMap;
import java.util.Map;

import static com.servlet.project.util.ViewResolver.resolve;

public class CommandResolver {

    private static final SecurityService securityService = new SecurityService();
    private static final UserService userService = new UserService(DaoFactory.createUserDao(), securityService);
    private static final EventService eventService = new EventService();
    private static final TopicService topicService = new TopicService();
    private static final ParticipantService participantService = new ParticipantService();

    private static final Map<String, Command> commands = new HashMap<>() {{
        put("logout", new Logout());
        put("login", new Login(userService, securityService));
        put("registration", new Registration(userService));
        put("index", new Index(eventService));
        put("register/event", new RegisterToEvent(eventService, securityService));
        put("event/all", new ModeratorGetAllEvents(eventService));
        put("event/create", new ModeratorCreateEvent(eventService));
        put("event/edit", new ModeratorEditEvent(eventService, topicService));
        put("event/delete", new ModeratorDeleteEvent(eventService));
        put("event/topic/add", new ModeratorCreateTopic(eventService));
        put("event/assign/speaker", new ModeratorAssignSpeaker(userService, topicService));
        put("index/cabinet-entrance", new SpeakerEvents(securityService, eventService, participantService));

    }};

    public static Command getCommand(String path) {
        return commands.getOrDefault(path, (def) -> resolve("login"));
    }

}
