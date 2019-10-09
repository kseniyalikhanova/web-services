package by.bsu.travelagency.service.impl;

import by.bsu.travelagency.entity.User;
import by.bsu.travelagency.entity.UserPrincipal;
import by.bsu.travelagency.service.UserService;
import by.bsu.travelagency.specification.impl.UserByLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;
        List<User> users = userService.findBySpecification(new UserByLogin(login));
        if (users != null) {
            user = users.get(0);
        } else  {
            throw new UsernameNotFoundException("User with login '" + login + "' not found");
        }
        return new UserPrincipal(user);
    }
}