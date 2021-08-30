package com.servlet.project.controller.command;

import com.servlet.project.exceptions.TopicAlreadyExistException;
import com.servlet.project.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

import static com.servlet.project.util.ViewResolver.resolveAdmin;

public class ModeratorCreateTopic implements Command {

    private final EventService eventService;

    public ModeratorCreateTopic(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String title = request.getParameter("title");
        Long eventId = Optional.ofNullable(request.getParameter("eventId"))
                .map(Long::valueOf)
                .orElse(null);

        if (Objects.isNull(title) || title.isEmpty() || Objects.isNull(eventId)) {
            request.getSession().setAttribute(
                    "topic_error_message", "valid.new.topic.empty");
            return "redirect:/event/edit";
        }

        try {
            eventService.createTopic(title, eventId);
        } catch (TopicAlreadyExistException e) {
            request.getSession().setAttribute(
                    "topic_error_message", "valid.new.topic.exist");
        }
        return "redirect:/event/edit?id=" + eventId;
    }
}
