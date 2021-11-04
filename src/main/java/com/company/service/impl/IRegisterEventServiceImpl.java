package com.company.service.impl;

import com.company.dao.IEventDAO;
import com.company.dao.IRegisterEventDAO;
import com.company.entity.RegisterEvent;
import com.company.service.IRegisterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IRegisterEventServiceImpl implements IRegisterEventService {

    @Autowired
    IRegisterEventDAO registerEventDao;
    @Autowired
    IEventDAO eventDAO;

    @Transactional
    @Override
    public boolean signUp(int eventId, String username) {
        RegisterEvent registerEvent = new RegisterEvent();
        registerEvent.setEvent(eventDAO.getById(eventId));
        registerEvent.setUser(username);
        return registerEventDao.save(registerEvent);
    }
}
