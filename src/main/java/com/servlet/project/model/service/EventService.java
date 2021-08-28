package com.servlet.project.model.service;

import com.servlet.project.exceptions.EventAlreadyExistException;
import com.servlet.project.exceptions.EventNotFoundException;
import com.servlet.project.model.dao.EventDao;
import com.servlet.project.model.dao.ParticipantDao;
import com.servlet.project.model.dao.UserDao;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.dao.mapper.EventMapper;
import com.servlet.project.model.dto.EventDto;
import com.servlet.project.model.entity.Event;
import com.servlet.project.model.entity.Participant;
import com.servlet.project.model.entity.Role;
import com.servlet.project.model.entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EventService {

    private final EventDao eventDao = DaoFactory.createEventDao();
    private final UserDao userDao = DaoFactory.createUserDao();
    private final ParticipantDao participantDao = DaoFactory.createParticipantDao();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public boolean createEvent(long id, String title, String scheduledDate) {
        return eventDao.save(Event.builder()
                .id(id)
                .title(title)
                .scheduledDate(LocalDateTime.parse(scheduledDate, formatter))
                .build());
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
        var eventDto = new EventDto();

        eventDto.setId(event.getId());
        eventDto.setTitle(event.getTitle());
        eventDto.setScheduledDate(event.getScheduledDate().format(formatter));
        return eventDto;
    }

    public EventDto getEventById(Long id) {
        Optional<Event> event = eventDao.findById(id);
        return event.map(this::convertEventToEventDto)
                .orElseThrow(() -> new EventNotFoundException("event.not.found"));
    }

    public void registerToEvent(Long eventId, String nickName, Boolean isSpeaker, User user) {
        Participant participant = new Participant();

        participant.setNickName(nickName);
        participant.setSpeaker(isSpeaker);
        participant.setEventId(eventId);
        participant.setUserId(user.getId());

        if (isSpeaker) {
            user.setRole(Role.SPEAKER);
            userDao.updateUserRole(user);
        }
        participantDao.save(participant);
    }
}
