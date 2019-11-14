package com.epam.travelagency.controller;

import com.epam.travelagency.service.ReviewService;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.service.UserService;
import com.epam.travelagency.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tour")
public class TourController extends AbstractController<TourService> {

    private final UserService userService;
    private final SessionUser sessionUser;
    private final ReviewService reviewService;

    @Autowired
    public TourController(TourService tourService, SessionUser sessionUser,
                          UserService userService, ReviewService reviewService) {
        super(tourService);
        this.sessionUser = sessionUser;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @PostMapping("/order/{id}")
    public ResponseEntity<String> order(@PathVariable(value = "id") Integer id) {
        ResponseEntity<String> responseEntity;
        if (userService.addTour(sessionUser.getId(), id)) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<String> writeReviewOnTour(@PathVariable("id") Integer id,
                                                    @RequestParam("text") String text) {
        ResponseEntity<String> responseEntity;
        if (reviewService.create(text, sessionUser.getId(), id)) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
