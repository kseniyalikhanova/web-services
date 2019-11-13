package com.epam.travelagency.controller;

import com.epam.travelagency.entity.User;
import com.epam.travelagency.service.UserService;
import com.epam.travelagency.util.SessionUser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final SessionUser sessionUser;
    private final UserService userService;

    @Autowired
    public UserController(SessionUser sessionUser, UserService userService) {
        this.sessionUser = sessionUser;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<String> get() {
        //User user = sessionUser.getAuthenticatedUser();
        User user = userService.getById(2);
        JSONArray tours = new JSONArray();
        JSONObject toReturn = new JSONObject();
        tours.put(userService.getTours(user.getId()));
        toReturn.put("User", user);
        toReturn.put("Tours", tours);
        return new ResponseEntity<>(toReturn.toString(), HttpStatus.OK);
    }
}
