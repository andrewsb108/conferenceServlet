package com.servlet.project.controller.command;

import com.servlet.project.exceptions.TopicAlreadyExistException;
import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ModeratorCreateTopic implements Command {

    private final EventService eventService;

    public ModeratorCreateTopic(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        long topicId = Long.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        long speakerId = Long.valueOf(request.getParameter("speakerId"));
        long eventId = Long.valueOf(request.getParameter("eventId"));


        if (Objects.isNull(title) || title.isEmpty()) {
            request.getSession().setAttribute(
                    "topic_error_message", "valid.new.topic.empty");
            return "redirect:event_edit";
        }

        try {
            eventService.createTopic(topicId, title, speakerId, eventId);
        } catch (TopicAlreadyExistException e) {
            request.getSession().setAttribute(
                    "topic_error_message", "valid.new.topic.exist");
        }
        return "redirect:event/edit";
    }
}
