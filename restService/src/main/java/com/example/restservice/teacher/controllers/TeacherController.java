package com.example.restservice.teacher.controllers;

import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.pojos.NewUser;
import com.example.restservice.pojos.User;
import com.example.restservice.signature.services.SignatureService;
import com.example.restservice.signatureteacher.SignatureTeacherRepository;
import com.example.restservice.teacher.entities.Teacher;
import com.example.restservice.teacher.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SignatureService signatureService;

    @Autowired
    SignatureTeacherRepository sign_teach_repository;

    // C - Create a new teacher
    @RequestMapping(
            method = RequestMethod.POST,
            path = "add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<GeneralMessage> addTeacher(@RequestBody NewUser newUser) {
        System.out.println(newUser.toString());
        var signatures = signatureService.findAll();
        String newTeacherId = teacherService.addTeacher(
                newUser.getData().getName(),
                newUser.getData().getLastName(),
                newUser.getData().getGrade(),
                new Date(newUser.getData().getSince()),
                newUser.getNip());
        signatures.forEach(s -> {
            if (s.getGrade() == newUser.getData().getGrade()) {
                sign_teach_repository.addSignatureToTeacher(s.getSignatureId(), Integer.parseInt(newTeacherId));
            }
        });
        System.out.printf("New teacher ID: %s", newTeacherId);
        return ResponseEntity.ok().body(new GeneralMessage("Teacher created successfully!", String.format("New ID: %s",newTeacherId), false));
    }

    // R - Read single teacher
    @RequestMapping(method = RequestMethod.GET, path = "id")
    public Teacher getTeacher(@RequestParam("teacherId") int id) {
        return teacherService.findById(id);
    }

    // R - Read all teachers
    @RequestMapping(method = RequestMethod.GET, path = "all")
    public ArrayList<Teacher> getAllTeachers() {
        return teacherService.findAll();
    }

    // U - Update existing student
    @RequestMapping(
            method = RequestMethod.PUT,
            path = "update",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Object> updateTeacher(@RequestBody NewUser updUser) {
        if (Objects.nonNull(teacherService.findById(updUser.getData().getEntityId()))) {
            teacherService.save(teacherService.buildObj(updUser));
            return ResponseEntity.ok().body(new GeneralMessage("Teacher update success!", "Teacher with ID: " + updUser.getData().getEntityId(), false));
        }
        return ResponseEntity.status(401).body(new GeneralMessage("Update failed!", "ID does not exist!", true));
    }

    // D - Delete existing teacher
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<GeneralMessage> deleteTeacher(@RequestBody User user) {
        if (Objects.isNull(teacherService.findById(user.getEntityId()))) {
            return ResponseEntity.status(404).body(new GeneralMessage("Deletion failed!", "User does not exist!", true));
        }
        teacherService.deleteById(user.getEntityId());
        teacherService.deleteTeacherData(user.getEntityId());
        return ResponseEntity.ok().body(new GeneralMessage("Deletion successful!", "Deleted teacher with ID: " + user.getEntityId(), false));
    }
}
