package com.example.restservice.signaturestudent.attendance.controllers;

import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.pojos.SingleAttendance;
import com.example.restservice.signaturestudent.attendance.entities.Attendance;
import com.example.restservice.signaturestudent.attendance.services.AttendanceService;
import com.example.restservice.signaturestudent.repositories.SignatureStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    SignatureStudentRepository signatureStudentRepository;

    // C - Create attendance for students
    @RequestMapping(
            method = RequestMethod.POST,
            path = "save/today",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<GeneralMessage> saveTodayAtt(@RequestBody SingleAttendance attendance) {
        System.out.println(attendance.toString());
        attendanceService.save(attendanceService.buildObj(attendance));
        return ResponseEntity.ok().body(new GeneralMessage("Attendance saved", "", false));
    }

    // R - Read attendance of a student
    @RequestMapping(method = RequestMethod.GET, path = "student/{id}/{signatureId}")
    public @ResponseBody ArrayList<Attendance> getAttendanceOfStudent(
            @PathVariable("id") int studentId,
            @PathVariable("signatureId") int signatureId
    ) {
        var sign_stud_records = signatureStudentRepository.findAll();
        var attendance_records = attendanceService.findAll();

        ArrayList<Attendance> signatureAttendanceOfStudent = new ArrayList<>();

        sign_stud_records.forEach(ssRecord -> {
            if (ssRecord.getStudentId() == studentId) {
                if (ssRecord.getSignatureId() == signatureId) {
                    var sign_stud_id = ssRecord.getSignStudId();

                    attendance_records.forEach(attRecord -> {
                        if (attRecord.getSignStudId() == sign_stud_id)
                            signatureAttendanceOfStudent.add(attRecord);
                    });
                }
            }
        });

        return signatureAttendanceOfStudent;
    }
}
