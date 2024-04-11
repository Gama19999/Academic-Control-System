package com.example.restservice.auth.controller;

import com.example.restservice.admin.services.AdminService;
import com.example.restservice.auth.services.AuthAdminService;
import com.example.restservice.auth.services.AuthStudentService;
import com.example.restservice.auth.services.AuthTeacherService;
import com.example.restservice.pojos.Auth;
import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.pojos.User;
import com.example.restservice.student.services.StudentService;
import com.example.restservice.teacher.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    AuthStudentService authStudentService;
    @Autowired
    StudentService studentService;

    @Autowired
    AuthTeacherService authTeacherService;
    @Autowired
    TeacherService teacherService;

    @Autowired
    AdminService adminService;
    @Autowired
    AuthAdminService authAdminService;

    @RequestMapping(
            method = RequestMethod.POST,
            path = "student",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Object> authStudent(@RequestBody Auth authUser) {
        var dbUser = authStudentService.findById(authUser.getId());
        return Objects.nonNull(dbUser) && authUser.hasSameNIP(dbUser.getNip()) ?
                    ResponseEntity.ok().body(getUserData(authUser.getId(), authUser.getUserType())) :
                    ResponseEntity.status(401).body(new GeneralMessage("Auth data error!", "Invalid credentials!", true));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "teacher",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Object> authTeacher(@RequestBody Auth authUser) {
        var dbUser = authTeacherService.findById(authUser.getId());
        return Objects.nonNull(dbUser) && authUser.hasSameNIP(dbUser.getNip()) ?
                ResponseEntity.ok().body(getUserData(authUser.getId(), authUser.getUserType())) :
                ResponseEntity.status(401).body(new GeneralMessage("Auth data error!", "Invalid credentials!", true));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "admin",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Object> authAdmin(@RequestBody Auth authUser) {
        var dbUser = authAdminService.findById(authUser.getId());
        return Objects.nonNull(dbUser) && authUser.hasSameNIP(dbUser.getNip()) ?
                ResponseEntity.ok().body(getUserData(authUser.getId(), authUser.getUserType())) :
                ResponseEntity.status(401).body(new GeneralMessage("Auth data error!", "Invalid credentials!", true));
    }

    private User getUserData(int id, String userType) {
        return switch (userType) {
            case "student" -> new User().buildUser(studentService.findById(id), "student");
            case "teacher" -> new User().buildUser(teacherService.findById(id), "teacher");
            case "admin" -> new User().buildUser(adminService.findById(id), "admin");
            default -> null;
        };
    }
}
