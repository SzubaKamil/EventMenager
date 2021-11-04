package com.company.dao;

import com.company.entity.User;

public interface IUserDAO {
    void save(User user);
    boolean isUserExist(String username);
}
