package com.epam.travelagency.controller;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<Country>> getAll(@PathVariable(value = "page", required = false)
                                                            Integer page) {
        int total = 10;
        if (page == null) {
            page = 1;
        }
        return new ResponseEntity<>(countryService.paginate(page, total), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@RequestBody Country country) {
        ResponseEntity<Country> responseEntity;

        if (countryService.update(country.getId(), country.getName())) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(country, HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<Country> create(@RequestBody Country country) {
        ResponseEntity<Country> responseEntity;

        if (countryService.create(country.getName())) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>(country, HttpStatus.CONFLICT);
        }

        return responseEntity;
    }
}
