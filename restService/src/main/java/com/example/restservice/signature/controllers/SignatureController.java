package com.example.restservice.signature.controllers;

import com.example.restservice.pojos.GeneralMessage;
import com.example.restservice.signature.entities.Signature;
import com.example.restservice.signature.services.SignatureService;
import com.example.restservice.signaturestudent.calif.entities.Calif;
import com.example.restservice.signaturestudent.calif.services.CalifService;
import com.example.restservice.signaturestudent.repositories.SignatureStudentRepository;
import com.example.restservice.signatureteacher.SignatureTeacherRepository;
import com.example.restservice.student.entities.Student;
import com.example.restservice.student.services.StudentService;
import com.example.restservice.teacher.services.TeacherService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/signature")
public class SignatureController {

    @Autowired
    SignatureService signatureService;

    @Autowired
    SignatureStudentRepository sign_stud_repo;

    @Autowired
    CalifService califService;

    @Autowired
    SignatureTeacherRepository sign_teach_repo;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    // R - Read signatures of a student
    @RequestMapping(method = RequestMethod.GET, path = "student/{id}")
    public ArrayList<Pair<Signature, ArrayList<Calif>>> getSignaturesOfStudent(@PathVariable("id") int studentId) {
        var sign_stud_records = sign_stud_repo.findAll();
        var signatures_records = signatureService.findAll();
        var calif_records = califService.findAll();

        ArrayList<Pair<Signature, ArrayList<Calif>>> signaturesWithCalifsOfStudent = new ArrayList<>();

        sign_stud_records.forEach(ssRecord -> {
            if (ssRecord.getStudentId() == studentId) {
                var sign_stud_record_id = ssRecord.getSignStudId();

                for (Signature signature : signatures_records) {
                    var califs = new ArrayList<Calif>();

                    if (signature.getSignatureId() == ssRecord.getSignatureId()) {
                        calif_records.forEach(califRecord -> {
                            if (califRecord.getSignStudId() == sign_stud_record_id) {
                                califs.add(califRecord);
                            }
                        });
                        signaturesWithCalifsOfStudent.add(new Pair<>(signature, califs));
                    }
                }
            }
        });

        return signaturesWithCalifsOfStudent;
    }

    // R - Read signatures of a teacher
    @RequestMapping(method = RequestMethod.GET, path = "teacher/{id}")
    public ArrayList<Signature> getSignaturesOfTeacher(@PathVariable("id") int teacherId) {
        var sign_teach_records = sign_teach_repo.findAll();
        var signatures_records = signatureService.findAll();
        var teacher_of_grade = new int[]{0};

        if (Objects.nonNull(teacherService.findById(teacherId))) {
            teacher_of_grade[0] = teacherService.findById(teacherId).getOfGrade();
        } else return null;

        ArrayList<Signature> signaturesOfTeacher = new ArrayList<>();

        sign_teach_records.forEach(stRecord -> {
            if (stRecord.getTeacherId() == teacherId) {
                for (Signature signature : signatures_records) {
                    if (signature.getSignatureId() == stRecord.getSignatureId()) {
                        if (signature.getGrade() == teacher_of_grade[0]) {
                            signaturesOfTeacher.add(signature);
                        }
                    }
                }
            }
        });

        return signaturesOfTeacher;
    }

    // R - Read students of teacher's signature
    @RequestMapping(method = RequestMethod.GET, path = "teacher/{signatureId}/calif")
    public ArrayList<Pair<Student, ArrayList<Calif>>> getStudentsOfTeacherSignature(
            @PathVariable("signatureId") int signatureId
    ) {
        var sign_stud_records = sign_stud_repo.findAll();
        var calif_records = califService.findAll();

        ArrayList<Pair<Student, ArrayList<Calif>>> signaturesWithCalifsOfStudent = new ArrayList<>();

        sign_stud_records.forEach(ssRecord -> {
            if (ssRecord.getSignatureId() == signatureId) {
                ArrayList<Calif> califsOfStudent = new ArrayList<>();
                var student = studentService.findById(ssRecord.getStudentId());

                if (Objects.nonNull(student) && student.getStatus()) {
                    var sign_stud_id = ssRecord.getSignStudId();

                    calif_records.forEach(cRecord -> {
                        if (cRecord.getSignStudId() == sign_stud_id) {
                            califsOfStudent.add(cRecord);
                        }
                    });

                    signaturesWithCalifsOfStudent.add(new Pair<>(student, califsOfStudent));
                }
            }
        });

        return signaturesWithCalifsOfStudent;
    }

    // U - Update existing signature
    @RequestMapping(
            method = RequestMethod.PUT,
            path = "update/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> updateSignature(
            @PathVariable("id") int id,
            @RequestBody Signature signature
    ) {
        if (Objects.nonNull(signatureService.findById(id))) {
            signatureService.save(signatureService.buildObj(id, signature));
            return ResponseEntity.ok().body(new GeneralMessage("Update success!", "Signature with ID: " + id + "saved", false));
        }
        return ResponseEntity.status(404).body(new GeneralMessage("Update failed!", "Signature with id: " + id + "does NOT exist!", true));
    }

    // D - Delete existing signature
    @RequestMapping(method = RequestMethod.DELETE, path = "delete")
    public ResponseEntity<?> deleteSignature(@RequestBody Signature signature) {
        if (Objects.isNull(signatureService.findById(signature.getSignatureId()))) {
            return ResponseEntity.status(404).body(new GeneralMessage("Delete failed!", "Signature with ID: " + signature.getSignatureId() + " does NOT exist!", true));
        }
        signatureService.deleteById(signature.getSignatureId());
        return ResponseEntity.ok().body(new GeneralMessage("Deletion success!", "Signature with ID: " + signature.getSignatureId() + " successfully deleted!", false));
    }
}
