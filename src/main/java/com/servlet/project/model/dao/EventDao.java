package com.servlet.project.model.dao;

import com.servlet.project.model.entity.Event;

import java.util.List;

public interface EventDao extends Dao<Event> {
    boolean deleteById(long id);

    List<Event> findByIdIn(List<Long> eventIds);
}
