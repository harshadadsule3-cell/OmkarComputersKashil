package com.omkarComputers.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkarComputers.Entity.Enrollment;


public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentId(Long studentId);

}