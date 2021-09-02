package com.servlet.project.controller.command;

import com.servlet.project.model.entity.Event;
import com.servlet.project.model.entity.Participant;
import com.servlet.project.model.entity.User;
import com.servlet.project.model.service.EventService;
import com.servlet.project.model.service.ParticipantService;
import com.servlet.project.model.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static com.servlet.project.util.ViewResolver.resolve;

public class SpeakerEvents implements Command {

    private final SecurityService securityService;
    private final EventService eventService;
    private final ParticipantService participantService;


    public SpeakerEvents(SecurityService securityService, EventService eventService, ParticipantService participantService) {
        this.securityService = securityService;
        this.eventService = eventService;
        this.participantService = participantService;
    }


    @Override
    public String execute(HttpServletRequest request) {
/*
        Long userId = Optional.ofNullable(request.getParameter("userId"))
                .map(Long::valueOf)
                .orElse(null);

        if (Objects.isNull(userId)) {
            return resolve("index");
        }
*/

        HttpSession session = request.getSession();
        User user = securityService.getLoggedUser(session);

        List<Long> eventIds = participantService.findAllByUserId(user.getId()).stream()
                .map(Participant::getEventId)
                .collect(Collectors.toList());

        List<Event> events = eventService.findByIdIn(eventIds);
        request.setAttribute("events", events);
        return resolve("cabinet");
    }
}
