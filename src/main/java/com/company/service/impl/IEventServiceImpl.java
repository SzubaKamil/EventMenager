package com.company.service.impl;

import com.company.dao.IEventDAO;
import com.company.entity.Event;
import com.company.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class IEventServiceImpl implements IEventService {

    @Autowired
    IEventDAO eventDAO;

    @Transactional
    @Override
    public void save(Event event) {
        eventDAO.save(event);
    }

    @Override
    public List<Event> getAll() {
        return eventDAO.getAll();
    }

    @Override
    public List<Event> getUserEvents(String username) {
        return eventDAO.getUserEvents(username);
    }

    @Override
    public List<Event> getUserOldEvents(String username) {
        return eventDAO.getUserOldEvents(username);
    }

    @Override
    public List<Event> search(String name, String place, int typeId, int categoryId, LocalDate date) {
        if (date != null){
            return eventDAO.search(name, place, typeId, categoryId, date) ;
        }
        else {
            return eventDAO.search(name,place, typeId, categoryId);
        }
    }

    @Transactional
    @Override
    public void deleteUserOldEvent(String username) {
        eventDAO.deleteUserOldEvent(username);
    }

}
