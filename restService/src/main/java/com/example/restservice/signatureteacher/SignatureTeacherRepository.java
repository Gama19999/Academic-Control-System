package com.example.restservice.signatureteacher;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

public interface SignatureTeacherRepository extends CrudRepository<SignatureTeacher, Integer> {
    @Procedure("sp_signature_by_teacher")
    void addSignatureToTeacher(int signatureId, int teacherId);
}
