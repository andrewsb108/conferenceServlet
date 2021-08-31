package com.servlet.project.controller.command;

import com.servlet.project.exceptions.EventDeleteException;
import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;

public class ModeratorDeleteEvent implements Command {

    private final EventService eventService;

    public ModeratorDeleteEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        long eventId = Long.valueOf(request.getParameter("eventId"));
            eventService.deleteById(eventId);

        return "redirect:/event/all";
    }
}
