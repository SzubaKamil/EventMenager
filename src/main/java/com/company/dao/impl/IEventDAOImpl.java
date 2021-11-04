package com.company.dao.impl;

import com.company.dao.IEventDAO;
import com.company.entity.Event;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IEventDAOImpl implements IEventDAO {

    private EntityManager entityManager;

    @Autowired
    public IEventDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Event event) {
        Session currentSession = entityManager.unwrap(Session.class);
        try {
            currentSession.save(event);
        } catch (Exception e){
            System.out.println("EXCEPTON");
        }
    }

    @Override
    public List<Event> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Event> theQuery = currentSession.createQuery("from Event", Event.class);
        return theQuery.getResultList();
    }

    @Override
    public Event getById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Event> theQuery = currentSession.createQuery("from Event where id=:id", Event.class);
        theQuery.setParameter("id", id);
        return theQuery.getSingleResult();
    }

    @Override
    public List<Event> getUserEvents(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Event> theQuery = currentSession.createQuery("from Event where owner=:username", Event.class);
        theQuery.setParameter("username", username);
        return theQuery.getResultList();
    }

    @Override
    public List<Event> getUserOldEvents(String username) {
        List<Event> eventList = getUserEvents(username);
        List<Event> oldEvents = new ArrayList<>();

        LocalDate now = LocalDate.now();

        for (Event event: eventList){
            if (now.isAfter(event.getDate())){
                oldEvents.add(event);
            }
        }
        return oldEvents;
    }

    @Override
    public List<Event> search(String name, String place, int type_Id, int category_Id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = null;

        name.trim();
        place.trim();

        theQuery =currentSession.createQuery("from Event where lower(name) like :name and lower(place) like :place and (type_id) like :type_id and (category_id) like :category_id", Event.class);
        theQuery.setParameter("name", "%" + name.toLowerCase() + "%");
        theQuery.setParameter("place", "%" + place.toLowerCase() + "%");
        theQuery.setParameter("type_id", type_Id);
        theQuery.setParameter("category_id", category_Id);

        return theQuery.getResultList();
    }

    @Override
    public List<Event> search(String name, String place, int type_Id, int category_Id, LocalDate date) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = null;

        String query = "from Event where lower(name) like :name and lower(place) like :place and (type_id) like :type_id and (category_id) like :category_id and (date) like :date";

        theQuery =currentSession.createQuery(query, Event.class);
        theQuery.setParameter("name", "%" + name.toLowerCase() + "%");
        theQuery.setParameter("place", "%" + place.toLowerCase() + "%");
        theQuery.setParameter("type_id", type_Id);
        theQuery.setParameter("category_id", category_Id);
        theQuery.setParameter("date", date);

        return theQuery.getResultList();
    }

    @Override
    public void deleteUserOldEvent(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Event> oldEvent = getUserOldEvents(username);

        if (oldEvent.size() > 0){
            for (Event event: oldEvent){
                currentSession.delete(event);
            }
        }
    }

}
