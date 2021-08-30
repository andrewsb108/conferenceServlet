package com.servlet.project.controller.command;

import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.servlet.project.util.ViewResolver.resolveAdmin;

public class ModeratorCreateEvent implements Command {

    private final EventService eventService;

    public ModeratorCreateEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getMethod().equals("GET")) {
            return resolveAdmin("event_create");
        }

        String title = request.getParameter("title");
        String scheduledDate = request.getParameter("scheduledDate");

        if (Objects.isNull(title)) {
            return "redirect:/event/all";
        }

        eventService.createEvent(title, scheduledDate);

        return "redirect:/event/all";

    }
}
