package com.example.rest.repository;


import com.example.rest.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
    List<Student> findByStatus(boolean status);
    List<Student> findByStatusAndAndId(boolean status, long id);

    List<Student> findAllByIdAfter(long id);

}
