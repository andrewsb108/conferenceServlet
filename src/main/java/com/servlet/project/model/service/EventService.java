package com.servlet.project.model.service;

import com.servlet.project.exceptions.EventDeleteException;
import com.servlet.project.exceptions.EventNotFoundException;
import com.servlet.project.model.dao.EventDao;
import com.servlet.project.model.dao.ParticipantDao;
import com.servlet.project.model.dao.TopicDao;
import com.servlet.project.model.dao.UserDao;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.dto.EventDto;
import com.servlet.project.model.entity.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EventService {

    private final EventDao eventDao = DaoFactory.createEventDao();
    private final UserDao userDao = DaoFactory.createUserDao();
    private final TopicDao topicDao = DaoFactory.createTopicDao();

    private final ParticipantDao participantDao = DaoFactory.createParticipantDao();

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Optional<Event> createEvent(String title, String scheduledDate) {
        return eventDao.save(Event.builder()
                .title(title)
                .scheduledDate(LocalDateTime.parse(scheduledDate))
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

    public EventDto getEventById(long id) {
        Optional<Event> event = eventDao.findById(id);
        return event.map(this::convertEventToEventDto)
                .orElseThrow(() -> new EventNotFoundException("event.not.found"));
    }

    public void registerToEvent(long eventId, String nickName, Boolean isSpeaker, User user) {
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

    public boolean deleteById(long id) throws EventDeleteException {
        return eventDao.deleteById(id);
    }

    public Optional<Event> updateEvent(long id, String title, String scheduledDate) {
        return eventDao.update(Event.builder()
                .id(id)
                .title(title)
                .scheduledDate(LocalDateTime.parse(scheduledDate))
                .build());

    }

    public Topic createTopic(String title, long eventId) {
        Topic topic = Topic.builder()
                .title(title)
                .eventId(eventId).build();


        topicDao.save(topic);
        return topic;
    }
}
