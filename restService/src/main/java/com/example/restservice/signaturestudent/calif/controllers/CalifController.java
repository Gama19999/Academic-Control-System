package com.example.restservice.signaturestudent.calif.controllers;

import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.signaturestudent.calif.entities.Calif;
import com.example.restservice.signaturestudent.calif.services.CalifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/calif")
public class CalifController {

    @Autowired
    CalifService califService;

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "update",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<GeneralMessage> updateCalif(@RequestBody Calif calif) {
        System.out.println(calif.toString());
        if (Objects.nonNull(califService.findById(calif.ssCalifId))) {
            califService.save(calif);
        }
        return ResponseEntity.ok().body(new GeneralMessage("Operation success!", "Update of scores successful!", false));
    }
}
