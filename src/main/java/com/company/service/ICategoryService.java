package com.company.service;

import com.company.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Category getByName(String name);
    void save(Category category);
}
