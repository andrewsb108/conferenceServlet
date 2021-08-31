package com.servlet.project.model.dao;

import com.servlet.project.model.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicDao extends Dao<Topic> {

   List<Topic> findByEventId(long eventId);
   Optional<Topic> updateTopic(Topic topic);
}
