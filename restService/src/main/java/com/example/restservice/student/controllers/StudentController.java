package com.example.restservice.student.controllers;

import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.pojos.NewUser;
import com.example.restservice.pojos.User;
import com.example.restservice.student.entities.Student;
import com.example.restservice.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // C - Create a new student
    @RequestMapping(
            method = RequestMethod.POST,
            path = "add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<GeneralMessage> addStudent(@RequestBody NewUser newUser) {
        System.out.println(newUser.toString());
        var newStudentId = studentService.addStudent(
                newUser.getData().getName(),
                newUser.getData().getLastName(),
                newUser.getData().getGrade(),
                new Date(newUser.getData().getSince()),
                newUser.getNip());
        System.out.printf("New student ID: %s", newStudentId);
        return ResponseEntity.ok().body(new GeneralMessage("Student created successfully!", String.format("New ID: %s",newStudentId), false));
    }

    // R - Read single student
    @RequestMapping(method = RequestMethod.GET, path = "id")
    public Student getStudent(@RequestParam("studentId") int id) {
        return studentService.findById(id);
    }

    // R - Read all students
    @RequestMapping(method = RequestMethod.GET, path = "all")
    public @ResponseBody ArrayList<Student> getAllStudents() {
        return studentService.findAll();
    }

    // U - Update existing student
    @RequestMapping(
            method = RequestMethod.PUT,
            path = "update",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<?> updateStudent(@RequestBody NewUser updUser) {
        System.out.println(updUser.toString());
        if (Objects.nonNull(studentService.findById(updUser.getData().getEntityId()))) {
            studentService.save(studentService.buildObj(updUser));
            return ResponseEntity.ok().body(new GeneralMessage("Student update success!", "Student with ID: " + updUser.getData().getEntityId(), false));
        }
        return ResponseEntity.status(401).body(new GeneralMessage("Update failed!", "ID does not exist!", true));
    }

    // D - Delete existing student
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "delete",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<GeneralMessage> deleteStudent(@RequestBody User user) {
        if (Objects.isNull(studentService.findById(user.getEntityId()))) {
            return ResponseEntity.status(404).body(new GeneralMessage("Deletion failed!", "User does not exist!", true));
        }
        studentService.deleteById(user.getEntityId());
        studentService.deleteStudentData(user.getEntityId());
        return ResponseEntity.ok().body(new GeneralMessage("Deletion successful!", "Deleted student with ID: " + user.getEntityId(), false));
    }

}
