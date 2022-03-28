package com.example.rest.repository;


import com.example.rest.models.Student;
import com.example.rest.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Student findByNameOfSubject(String nameOfSubject);

}
