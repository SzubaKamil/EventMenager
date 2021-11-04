package com.company.service;

import com.company.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface IEventService {

    void save (Event event);
    List<Event> getAll();
    List<Event> getUserEvents(String username);
    List<Event> getUserOldEvents(String username);
    List<Event> search(String name, String place, int typeId, int categoryId, LocalDate date);
    void deleteUserOldEvent(String username);
}
