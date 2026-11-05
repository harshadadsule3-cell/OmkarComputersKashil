package com.omkarComputers.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.omkarComputers.Entity.Course;
import com.omkarComputers.Entity.Enrollment;
import com.omkarComputers.Entity.Student;
import com.omkarComputers.service.CourseService;
import com.omkarComputers.service.EnrollmentService;
import com.omkarComputers.service.ScoreService;
import com.omkarComputers.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private ScoreService scoreService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    // Student Registration Page
    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute("student", new Student());

        return "register";
    }

    // Save Student
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {

        student.setPassword(
                passwordEncoder.encode(
                        student.getPassword()));

        student.setRole("ROLE_STUDENT");

        studentService.saveStudent(student);

        return "redirect:/admin/dashboard";
    }

    // View Available Courses
    @GetMapping("/student/courses")
    public String viewCourses(Model model) {

        model.addAttribute("courses",
                courseService.getAllCourses());

        return "student-courses";
    }

    // Enroll Course
    @GetMapping("/enroll/{courseId}")
    public String enrollCourse(@PathVariable Long courseId) {

        Student student = studentService.getStudentById(1L);

        Course course = courseService.getCourseById(courseId);

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        enrollmentService.saveEnrollment(enrollment);

        return "redirect:/student/myCourses";
    }

    // View Enrolled Courses
    @GetMapping("/student/myCourses")
    public String myCourses(Model model) {

        model.addAttribute("enrollments",
                enrollmentService.getAllEnrollments());

        return "my-courses";
    }

    // View Student Scores
    @GetMapping("/student/scores")
    public String studentScores(Model model) {

        model.addAttribute("scores",
                scoreService.getAllScores());

        return "student-scores";
    }

}