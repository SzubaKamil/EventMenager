package com.company.service.impl;

import com.company.dao.IUserDAO;
import com.company.entity.User;
import com.company.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDao;

    @Transactional
    @Override
    public boolean save(User user) {
        if (user.getPassword().equals(user.getPasswordRepeat()) &&  !userDao.isUserExist(user.getUsername() )){
            userDao.save(user);
            return true;
        }
        return false;
    }
}
