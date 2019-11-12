package com.epam.travelagency.controller;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class UnauthenticatedUserController {

    private final UserService userService;

    @Autowired
    public UnauthenticatedUserController(UserService service) {
        this.userService = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        ResponseEntity<User> responseEntity;

        if (userService.create(user.getLogin(), user.getPassword())) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(user, HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

}

