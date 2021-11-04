package com.company.service.impl;

import com.company.dao.ICategoryDAO;
import com.company.entity.Category;
import com.company.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryServiceImpl implements ICategoryService {

    @Autowired
    ICategoryDAO categoryDAO;

    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryDAO.getByName (name);
    }

    @Override
    public void save(Category category) {
        categoryDAO.save(category);
    }
}
