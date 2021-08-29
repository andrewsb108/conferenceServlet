package com.servlet.project.model.dao;

import com.servlet.project.model.entity.Event;

public interface EventDao extends Dao<Event> {
    boolean deleteById(long id);
}
