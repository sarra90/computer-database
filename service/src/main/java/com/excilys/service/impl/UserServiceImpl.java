package com.excilys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.dao.UserDao;
import com.excilys.model.User;
import com.excilys.service.UserService;

public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

}
