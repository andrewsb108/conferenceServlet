package com.servlet.project.controller.command;

import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ModeratorCreateEvent implements Command {

    private final EventService eventService;
    public static final String REDIRECT_TO_MODERATOR = "redirect:/event_create";


    public ModeratorCreateEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String scheduledDate = request.getParameter("scheduledDate");

        if (Objects.isNull(id)) {
            return REDIRECT_TO_MODERATOR;
        }

        long eventId = Long.parseLong(id);
        eventService.createEvent(eventId, title, scheduledDate);

        return REDIRECT_TO_MODERATOR;

    }
}
