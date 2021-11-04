package com.company.dao;

import com.company.entity.Type;

import java.util.List;

public interface ITypeDAO {
    List<Type> getAll ();
    Type getByName (String name);
    void save (Type type);
}
