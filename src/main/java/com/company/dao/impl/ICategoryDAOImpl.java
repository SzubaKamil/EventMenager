package com.company.dao.impl;

import com.company.dao.ICategoryDAO;
import com.company.entity.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ICategoryDAOImpl implements ICategoryDAO {

    private EntityManager entityManager;

    @Autowired
    public ICategoryDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Category> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Category> theQuery = currentSession.createQuery("from Category", Category.class);
        return theQuery.getResultList();
    }

    @Override
    public Category getByName(String name) {
        Category category = null;

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Category> theQuery = currentSession.createQuery("from Category where name=:name", Category.class);
        theQuery.setParameter("name", name);

        try {
            category = theQuery.getSingleResult();
        } catch (NoResultException e){
            System.out.println("Nie znaleziono kategori: " + category + " - " + e.getMessage());
        }
        return category;
    }

    @Override
    public void save(Category category) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(category);
    }
}
