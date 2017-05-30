package com.excilys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.User;

@Repository
public interface UserDao extends JpaRepository<User, String>{

    public User findByUsername(String username);
}
