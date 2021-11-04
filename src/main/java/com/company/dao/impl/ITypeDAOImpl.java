package com.company.dao.impl;

import com.company.dao.ITypeDAO;
import com.company.entity.Type;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ITypeDAOImpl implements ITypeDAO {

    private EntityManager entityManager;

    @Autowired
    public ITypeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Type> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Type> theQuery = currentSession.createQuery("from Type", Type.class);
        return theQuery.getResultList();
    }

    @Override
    public Type getByName(String name) {
        Type type = null;

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Type> theQuery = currentSession.createQuery("from Type where name=:name", Type.class);
        theQuery.setParameter("name", name);

        try {
            type = theQuery.getSingleResult();
        } catch (NoResultException e){
            System.out.println("Nie znaleziono typu: " + type + " - " + e.getMessage());
        }
        return type;
    }

    @Override
    public void save(Type type) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(type);
    }
}
