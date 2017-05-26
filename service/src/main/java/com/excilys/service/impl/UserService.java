package com.excilys.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.dao.UserDao;
import com.excilys.model.Role;
import com.excilys.model.User;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);
        if (user == null) {
            LOGGER.debug("user not found with the provided username");
            return null;
        }
        LOGGER.debug(" user from username " + user.toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> autorithies = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole().toUpperCase());
            autorithies.add(grantedAuthority);
        }
        LOGGER.debug("user authorities are " + autorithies.toString());
        return autorithies;
    }

}
