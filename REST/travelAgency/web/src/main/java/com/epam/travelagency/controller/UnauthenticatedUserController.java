package com.epam.travelagency.controller;

import com.epam.travelagency.entity.UserPrincipal;
import com.epam.travelagency.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UnauthenticatedUserController {

    private final UserService userService;

    @Autowired
    public UnauthenticatedUserController(UserService service) {
        this.userService = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody @Valid UserPrincipal user) {
        ResponseEntity<String> responseEntity;

        if (userService.create(user.getUsername(), user.getPassword())) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            JSONObject toReturn = new JSONObject();
            toReturn.put("Response", user);
            responseEntity = new ResponseEntity<>(toReturn.toString(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

}

