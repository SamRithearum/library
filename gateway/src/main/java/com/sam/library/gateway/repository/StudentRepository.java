package com.sam.library.gateway.repository;

import com.sam.library.gateway.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s.* FROM student s WHERE LOWER(s.student_id) = LOWER(:studentId)", nativeQuery = true)
    Student getStudentByStudentId(@Param("studentId") String studentId);
}
