package com.epam.travelagency.service.impl;

import com.epam.travelagency.entity.UserPrincipal;
import com.epam.travelagency.entity.User;
import com.epam.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {
        User user = userService.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login '" + login + "' not found");
        }
        return new UserPrincipal(user);
    }
}