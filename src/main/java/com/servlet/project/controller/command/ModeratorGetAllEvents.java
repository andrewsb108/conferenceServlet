package com.servlet.project.controller.command;

import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;

import static com.servlet.project.util.ViewResolver.resolve;

public class ModeratorGetAllEvents implements Command {

    private final EventService eventService;

    public ModeratorGetAllEvents(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        String scheduledDate = request.getParameter("scheduledDate");

        request.setAttribute("events", eventService.getAllEvents());

        return resolve("event_list");
    }
}
