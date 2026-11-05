package com.omkarComputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkarComputers.Entity.Course;

public interface CourseRepository  
extends JpaRepository<Course, Long> {

}
