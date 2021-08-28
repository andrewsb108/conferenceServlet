package com.servlet.project.controller.command;

import com.servlet.project.model.dto.EventDto;
import com.servlet.project.model.dto.TopicDto;
import com.servlet.project.model.entity.Topic;
import com.servlet.project.model.service.EventService;
import com.servlet.project.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.servlet.project.util.ViewResolver.resolveAdmin;

public class ModeratorEditEvent implements Command {

    private final EventService eventService;
    private final TopicService topicService;

    public ModeratorEditEvent(EventService eventService, TopicService topicService) {
        this.eventService = eventService;
        this.topicService = topicService;
    }

    @Override
    public String execute(HttpServletRequest request) {
            Long eventId = Long.valueOf(request.getParameter("id"));
//            request.setAttribute("eventId", eventId);

            EventDto event = eventService.getEventById(eventId);
            List<Topic> topics = topicService.findByEventId(eventId);

            request.setAttribute("event", event);
            request.setAttribute("topics", topics);

        return resolveAdmin("event_edit");
    }
}
