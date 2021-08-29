package com.servlet.project.controller.command;

import com.servlet.project.exceptions.EventAlreadyExistException;
import com.servlet.project.model.dto.EventDto;
import com.servlet.project.model.entity.Topic;
import com.servlet.project.model.service.EventService;
import com.servlet.project.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Long eventId = Optional.ofNullable(request.getParameter("id"))
                .map(Long::valueOf)
                .orElse(null);

        if (Objects.isNull(eventId)) {
            request.setAttribute("category_not_found", true);
            return resolveAdmin("event_list");
        }

        EventDto event = eventService.getEventById(eventId);
        List<Topic> topics = topicService.findByEventId(eventId);
        request.setAttribute("event", event);
        request.setAttribute("topics", topics);

        if (request.getMethod().equals("POST")) {
            String title = request.getParameter("title");
            String scheduledDate = request.getParameter("scheduledDate");
            if (Objects.isNull(title) || title.isEmpty()) {
                request.setAttribute("event_empty_name", true);
                request.setAttribute("id", eventId);
                return resolveAdmin("event_edit");
            }

            if (Objects.isNull(scheduledDate) || scheduledDate.isEmpty()) {
                request.setAttribute("event_empty_name", true);
                request.setAttribute("id", eventId);
                return resolveAdmin("event_edit");
            }
            try {
//                    EventDto eventDto = new EventDto();
//                    eventDto.setId(eventId);
                eventService.updateEvent(eventId, title, scheduledDate);
            } catch (EventAlreadyExistException e) {
                request.setAttribute("id", eventId);
                request.setAttribute("event_already_exist", true);
                return resolveAdmin("event_edit");
            }
            return "redirect:/event/all";
        }
        request.setAttribute("eventId", eventId);
        return resolveAdmin("event_edit");
    }
}
