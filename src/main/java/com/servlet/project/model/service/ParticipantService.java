package com.servlet.project.model.service;

import com.servlet.project.model.dao.ParticipantDao;
import com.servlet.project.model.dao.impl.DaoFactory;
import com.servlet.project.model.entity.Participant;

import java.util.List;

public class ParticipantService {

    private final ParticipantDao participantDao = DaoFactory.createParticipantDao();

   public List<Participant> findAllByUserId(long userId) {
       return participantDao.findByUserId(userId);
   }

}
