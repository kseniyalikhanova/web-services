package com.epam.travelagency.controller;

import com.epam.travelagency.entity.Review;
import com.epam.travelagency.entity.Tour;
import com.epam.travelagency.service.ReviewService;
import com.epam.travelagency.service.TourService;
import com.epam.travelagency.service.UserService;
import com.epam.travelagency.util.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/tour")
public class TourController {

    private final TourService tourService;
    private final UserService userService;
    private final SessionUser sessionUser;
    private final ReviewService reviewService;

    @Autowired
    public TourController(TourService tourService, SessionUser sessionUser,
                          UserService userService, ReviewService reviewService) {
        this.tourService = tourService;
        this.sessionUser = sessionUser;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<Tour>> getAll(@PathVariable(value = "page", required = false)
                                                         Integer page) {
        int total = 10;
        if (page == null) {
            page = 1;
        }
        return new ResponseEntity<>(tourService.paginate(page, total), HttpStatus.OK);
    }

    @PostMapping("/order/{id}")
    public ResponseEntity<Tour> order(@PathVariable(value = "id") Integer id) {
        ResponseEntity<Tour> responseEntity;
        if (userService.addTour(sessionUser.getId(), id)) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("/{id}/addReview")
    public ResponseEntity<Review> writeReviewOnTour(@PathVariable("id") Integer id,
                                                    @RequestParam("text") String text) {
        ResponseEntity<Review> responseEntity;

        if (reviewService.create(text, sessionUser.getId(), id)) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}