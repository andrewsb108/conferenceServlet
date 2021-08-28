package com.servlet.project.model.dao;

import com.servlet.project.model.entity.Topic;

import java.util.List;

public interface TopicDao extends Dao<Topic> {

   List<Topic> findByEventId(long eventId);
}
