package com.omkarComputers.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkarComputers.Entity.Enrollment;
import com.omkarComputers.repository.EnrollmentRepository;



@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Enrollment saveEnrollment(Enrollment enrollment) {

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
    
    
}
