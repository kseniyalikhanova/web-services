package com.epam.travelagency.util;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.entity.UserPrincipal;
import com.epam.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SessionUser {

    private final UserService userService;

    @Autowired
    public SessionUser(UserService userService) {
        this.userService = userService;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userService.getById(userPrincipal.getId());
    }

    public Integer getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getId();
    }
}
