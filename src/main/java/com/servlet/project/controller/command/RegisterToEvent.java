package com.servlet.project.controller.command;

import com.servlet.project.exceptions.UniqueParticipantException;
import com.servlet.project.model.entity.User;
import com.servlet.project.model.service.EventService;
import com.servlet.project.model.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.servlet.project.util.ViewResolver.resolve;

public class RegisterToEvent implements Command {

    private final EventService eventService;
    private final SecurityService securityService;

    public RegisterToEvent(EventService eventService, SecurityService securityService) {
        this.eventService = eventService;
        this.securityService = securityService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            Long eventId = Long.valueOf(request.getParameter("id"));
            request.setAttribute("eventId", eventId);
            return resolve("register_to_event");
        }

        HttpSession session = request.getSession();
        User user = securityService.getLoggedUser(session);
        long eventId = Long.valueOf(request.getParameter("id"));
        String nickName = request.getParameter("nickName");
        Boolean isSpeaker = request.getParameter("speaker") != null;

        eventService.getEventById(eventId);

        try {
            eventService.registerToEvent(eventId, nickName, isSpeaker, user);
        } catch (UniqueParticipantException e) {
            session.setAttribute("unique_participant_error", true);
        }

        return "redirect:/index";
    }
}
