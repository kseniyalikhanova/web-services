package com.epam.travelagency.controller;

import com.epam.travelagency.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractController<S extends Service> {

    protected final S service;
    protected static final Logger LOG = LogManager.getLogger("logger");

    @Autowired
    public AbstractController(S service) {
        this.service = service;
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
        result.put(service.paginate(page, total));
        toReturn.put("Response", result);
        return new ResponseEntity<>(toReturn.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        ResponseEntity<String> responseEntity;
        if (service.delete(id)) {
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            responseEntity = new ResponseEntity<>("The object, you are trying to delete, doesn't exist.", HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
