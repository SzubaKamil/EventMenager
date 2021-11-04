package com.company.dao;

import com.company.entity.Category;

import java.util.List;

public interface ICategoryDAO {
    List<Category> getAll();
    Category getByName(String name);
    void save(Category category);
}
