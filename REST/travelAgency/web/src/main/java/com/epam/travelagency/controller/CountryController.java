package com.epam.travelagency.controller;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.service.CountryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController extends AbstractController<CountryService> {

    @Autowired
    public CountryController(CountryService countryService) {
        super(countryService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Country country) {
        ResponseEntity<String> responseEntity;
        if (service.update(country.getId(), country.getName())) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            String errorMessage = "Country with name = " + country.getName() + " doesn't exist.";
            LOG.warn(errorMessage);
            JSONObject toReturn = new JSONObject();
            toReturn.put("Response", country);
            toReturn.put("Message", errorMessage);
            responseEntity = new ResponseEntity<>(toReturn.toString(), HttpStatus.NOT_MODIFIED);
        }
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody Country country) {
        ResponseEntity<String> responseEntity;
        if (service.create(country.getName())) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            String errorMessage = "Country with name = " + country.getName() + " exists.";
            LOG.warn(errorMessage);
            JSONObject toReturn = new JSONObject();
            toReturn.put("Response", country);
            toReturn.put("Message", errorMessage);
            responseEntity = new ResponseEntity<>(toReturn.toString(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
