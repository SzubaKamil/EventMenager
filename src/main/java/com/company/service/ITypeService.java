package com.company.service;

import com.company.entity.Type;

import java.util.List;

public interface ITypeService {

    List<Type> getAll ();
    Type getByName (String name);
    void save (Type type);
}
