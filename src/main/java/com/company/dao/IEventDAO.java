package com.company.dao;

import com.company.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface IEventDAO {

    void save (Event event);
    List<Event> getAll();
    Event getById (int id);
    List<Event> getUserEvents(String username);
    List<Event> getUserOldEvents(String username);
    List<Event> search(String name, String place, int typeId, int categoryId);
    List<Event> search(String name, String place, int typeId, int categoryId, LocalDate date);
    void deleteUserOldEvent(String username);
}
