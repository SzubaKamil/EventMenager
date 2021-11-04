package com.company.dao.impl;

import com.company.dao.IUserDAO;
import com.company.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class IUserDAOImpl implements IUserDAO {

    private EntityManager entityManager;

    @Autowired
    public IUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(user);

        entityManager.createNativeQuery("INSERT INTO authorities (username, authority) VALUES (?,?)")
                .setParameter(1, user.getUsername())
                .setParameter(2, user.getAuthority())
                .executeUpdate();
    }

    @Override
    public boolean isUserExist(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = null;

        Query<User> theQuery = currentSession.createQuery("from User where  username=:username", User.class);
        theQuery.setParameter("username", username);
        try {
            user = theQuery.getSingleResult();
        } catch (NoResultException e){
            System.out.println(e.getMessage());
        }
        return user != null;
    }
}
