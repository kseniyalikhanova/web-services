package com.epam.travelagency.controller;

import com.epam.travelagency.entity.Country;
import com.epam.travelagency.service.CountryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = {"/all/{page}", "/all"})
    public ResponseEntity<String> getAll(@PathVariable(value = "page", required = false)
                                                            Integer page) {
        int total = 10;
        if (page == null) {
            page = 1;
        }
        JSONArray result = new JSONArray();
        JSONObject toReturn = new JSONObject();
        result.put(countryService.paginate(page, total));
        toReturn.put("Response", result);
        return new ResponseEntity<>(toReturn.toString(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Country country) {
        ResponseEntity<String> responseEntity;
        if (countryService.update(country.getId(), country.getName())) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            JSONObject toReturn = new JSONObject();
            toReturn.put("Response", country);
            responseEntity = new ResponseEntity<>(toReturn.toString(), HttpStatus.CONFLICT);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        ResponseEntity<String> responseEntity;
        if (countryService.delete(id)) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>("The country, you are trying to delete, doesn't exist.", HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
