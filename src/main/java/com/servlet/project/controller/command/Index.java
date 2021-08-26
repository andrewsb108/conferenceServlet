package com.servlet.project.controller.command;

import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;

import static com.servlet.project.util.ViewResolver.resolve;

public class Index implements Command {

    private final EventService eventService;

    public Index(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("events",  eventService.getAllEvents());
        return resolve("index");
    }
}
