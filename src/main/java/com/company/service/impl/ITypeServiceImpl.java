package com.company.service.impl;

import com.company.dao.ITypeDAO;
import com.company.entity.Type;
import com.company.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITypeServiceImpl implements ITypeService {

    @Autowired
    ITypeDAO iTypeDAO;

    @Override
    public List<Type> getAll() {
        return iTypeDAO.getAll();
    }

    @Override
    public Type getByName(String name) {
        return iTypeDAO.getByName(name);
    }

    @Override
    public void save(Type type) {
        iTypeDAO.save(type);
    }
}
