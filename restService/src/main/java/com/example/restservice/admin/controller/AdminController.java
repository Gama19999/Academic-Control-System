package com.example.restservice.admin.controller;

import com.example.restservice.auth.services.AuthStudentService;
import com.example.restservice.auth.services.AuthTeacherService;
import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.pojos.NewUser;
import com.example.restservice.student.services.StudentService;
import com.example.restservice.teacher.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    AuthStudentService authStudentService;
    @Autowired
    AuthTeacherService authTeacherService;

    @RequestMapping(method = RequestMethod.GET, path = "list/{entity}")
    public ArrayList<?> getAllEntities(@PathVariable("entity") String entity) {
        return switch (entity) {
            case "students" -> studentService.findAll();
            case "teachers" -> teacherService.findAll();
            default -> null;
        };
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "update/nip",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<GeneralMessage> updateNip(@RequestBody NewUser updUser) {
        ResponseEntity<GeneralMessage> response;
        switch (updUser.getData().getUserType()) {
            case "student" -> {
                if (userExistsAndMatchesNIP(updUser)) {
                    authStudentService.save(authStudentService.buildObj(updUser));
                    response = ResponseEntity.ok().body(new GeneralMessage("Update success!", "", false));
                } else response = ResponseEntity.status(403).body(new GeneralMessage("Update failed!", "Incorrect old nip", true));
            }
            case "teacher" -> {
                if (userExistsAndMatchesNIP(updUser)) {
                    authTeacherService.save(authTeacherService.buildObj(updUser));
                    response = ResponseEntity.ok().body(new GeneralMessage("Update success!", "", false));
                } else response = ResponseEntity.status(403).body(new GeneralMessage("Update failed!", "Incorrect old nip", true));
            }
            default -> {
                response = ResponseEntity.ok().body(new GeneralMessage("Update failed!", "Unknown user type", true));
            }
        }
        return response;
    }

    private boolean userExistsAndMatchesNIP(NewUser updUser) {
        if (updUser.getData().getUserType().equals("student")) {
            var user = authStudentService.findById(updUser.getData().getEntityId());
            return Objects.nonNull(user) && haveSameNIP(user.getNip(), updUser.getOldNip());
        } else {
            var user = authTeacherService.findById(updUser.getData().getEntityId());
            return Objects.nonNull(user) && haveSameNIP(user.getNip(), updUser.getOldNip());
        }
    }

    private boolean haveSameNIP(String dbNip, String authNip) {
        return dbNip.equals(authNip);
    }
}
