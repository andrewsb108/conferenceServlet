package com.servlet.project.controller.command;

import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ModeratorDeleteEvent implements Command {

    private final EventService eventService;

    public ModeratorDeleteEvent(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        long eventId = Long.valueOf(request.getParameter("id"));

        try {
            eventService.deleteById(eventId);
            // todo: change exception to be more specific
        } catch (SQLException e) {
            request.getSession().setAttribute(
                    "event_error_message","valid.event.not.deletable.message");
        }
        return "redirect:/event/all";
    }
}
