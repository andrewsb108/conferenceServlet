package com.servlet.project.controller.command;

import com.servlet.project.model.entity.Topic;
import com.servlet.project.model.entity.User;
import com.servlet.project.model.service.TopicService;
import com.servlet.project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.servlet.project.util.ViewResolver.resolveAdmin;

public class ModeratorAssignSpeaker implements Command {

    private final UserService userService;
    private final TopicService topicService;

    public ModeratorAssignSpeaker(UserService userService, TopicService topicService) {
        this.userService = userService;
        this.topicService = topicService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        Long topicId = Optional.ofNullable(request.getParameter("topicId"))
                .map(Long::valueOf).orElse(null);
        Long eventId = Optional.ofNullable(request.getParameter("eventId"))
                .map(Long::valueOf).orElse(null);

        List<User> users = userService.getAllUsers();
        // todo: wrap with try catch
        Topic topic = topicService.getTopic(topicId);

        request.setAttribute("users", users);
        request.setAttribute("topic", topic);
        request.setAttribute("eventId", eventId);

        return resolveAdmin("topic_assign_speaker");


/*
        Long topicId  = Optional.ofNullable(request.getParameter("topicId"))
                .map(Long::valueOf).orElse(null);
        Long eventId  = Optional.ofNullable(request.getParameter("eventId"))
                .map(Long::valueOf).orElse(null);
        Long speakerId  = Optional.ofNullable(request.getParameter("speakerId"))
                .map(Long::valueOf).orElse(null);


        if (Objects.isNull(topicId) || Objects.isNull(eventId)) {
            request.getSession().setAttribute(
                    "topic_error_message", "valid.new.topic.empty");
            //todo: return the same page with all ids
            return "redirect:/event/edit?id=" + eventId;
        }

        List<User> users = userService.getAllUsers();
        Topic topic = topicService.getTopic(topicId);

        request.setAttribute("users", users);
        request.setAttribute("topic", topic);
        request.setAttribute("eventId", eventId);

        if (request.getMethod().equals("POST")) {
            if (Objects.isNull(speakerId)) {
                request.getSession().setAttribute(
                        "topic_error_message", "valid.new.topic.empty");
                //todo: return the same page with all ids
                return "redirect:/event/assign/speaker?eventId=" + eventId + "&topicId=" + topic;
            }

         topicService.assignSpeaker(eventId, topicId, speakerId);

        }
//        return "redirect:/event/assign/speaker";
*/
    }
}
