package com.company.dao.impl;

import com.company.dao.IRegisterEventDAO;
import com.company.entity.RegisterEvent;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class IRegisterEventDAOImpl implements IRegisterEventDAO {

    private EntityManager entityManager;

    @Autowired
    public IRegisterEventDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(RegisterEvent registerEvent) {
        Session currentSession = entityManager.unwrap(Session.class);
        if (!isRegister(registerEvent)){
            currentSession.saveOrUpdate(registerEvent);
            return true;
        } else {
           return false;
        }

    }

    public boolean isRegister (RegisterEvent registerEvent){
        Session currentSession = entityManager.unwrap(Session.class);
        List<RegisterEvent> tempList = null;

        Query<RegisterEvent> theQuery = currentSession.createQuery("from RegisterEvent where  username=:username", RegisterEvent.class);
        theQuery.setParameter("username", registerEvent.getUser());
        try {
            tempList = theQuery.getResultList();
            if (tempList.size()>0){
                for (RegisterEvent registerEvent1: tempList){
                    if (registerEvent1.getEvent().getId() == registerEvent.getEvent().getId()){
                        return true;
                    }
                }
            }
        } catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
