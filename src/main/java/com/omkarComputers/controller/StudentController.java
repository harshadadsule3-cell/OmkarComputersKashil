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
import com.omkarComputers.repository.EnrollmentRepository;
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
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;


    @GetMapping("/student/dashboard")
    public String studentDashboard() {

        return "student-dashboard";
    }
    
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

        return "redirect:/login";
    }

    // View Available Courses
    @GetMapping("/student/courses")
    public String viewCourses(Model model) {

        model.addAttribute("courses",
                courseService.getAllCourses());

        return "student-courses";
    }

    @GetMapping("/enroll/{courseId}")
    public String enrollCourse(@PathVariable Long courseId,
                               java.security.Principal principal) {

        Student student =
                studentService.findByEmail(principal.getName());

        Course course =
                courseService.getCourseById(courseId);

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());

        enrollmentService.saveEnrollment(enrollment);

        return "redirect:/student/myCourses";
    }

    @GetMapping("/student/myCourses")
    public String myCourses(Model model,
            java.security.Principal principal) {

        Student student =
                studentService
                .findByEmail(principal.getName());

        model.addAttribute(
                "enrollments",
                enrollmentRepository
                .findByStudentId(student.getId()));

        return "my-courses";
    }

    // View Student Scores
    @GetMapping("/student/scores")
    public String studentScores(Model model,
            java.security.Principal principal) {

        Student student =
                studentService
                .findByEmail(principal.getName());

        model.addAttribute(
                "scores",
                scoreService.getScoresByStudentId(
                        student.getId()));

        return "student-scores";
    }

}