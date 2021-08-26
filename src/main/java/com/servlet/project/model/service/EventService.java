package com.servlet.project.model.service;

import com.servlet.project.exceptions.EventAlreadyExistException;
import com.servlet.project.model.dao.EventDao;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.dto.EventDto;
import com.servlet.project.model.entity.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class EventService {

    private final EventDao eventDao = DaoFactory.createEventDao();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event createEvent(long id, String title, String scheduledDate) {
        return eventDao.save(Event.builder()
                .id(id)
                .title(title)
                .scheduledDate(LocalDateTime.parse(scheduledDate, formatter))
                .build()).orElseThrow(() -> new EventAlreadyExistException("event.exist"));
    }

    public List<EventDto> getAllEvents() {
        List<Event> events = eventDao.findAll();
        if (events == null) {
            return null;
        }
        List<EventDto> list = new ArrayList<>();
        for (Event event : events) {
            list.add(convertEventToEventDto(event));
        }
        return list;
    }

    public EventDto convertEventToEventDto(Event event) {
        if (event == null) {
            return null;
        }
        EventDto eventDto = new EventDto();

        eventDto.setId(event.getId());
        eventDto.setTitle(event.getTitle());
        eventDto.setScheduledDate(event.getScheduledDate().format(formatter));
        return eventDto;
    }

}
