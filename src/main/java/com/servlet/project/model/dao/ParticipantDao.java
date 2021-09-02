package com.servlet.project.model.dao;

import com.servlet.project.model.entity.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantDao extends Dao<Participant> {
    List<Participant> findByUserId(long userId);
}
