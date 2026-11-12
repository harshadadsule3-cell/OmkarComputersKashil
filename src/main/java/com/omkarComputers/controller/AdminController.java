package com.omkarComputers.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omkarComputers.Entity.Course;
import com.omkarComputers.Entity.Score;
import com.omkarComputers.Entity.Student;
import com.omkarComputers.repository.CourseRepository;
import com.omkarComputers.repository.EnrollmentRepository;
import com.omkarComputers.repository.ScoreRepository;
import com.omkarComputers.repository.StudentRepository;
import com.omkarComputers.service.CourseService;
import com.omkarComputers.service.EnrollmentService;
import com.omkarComputers.service.ScoreService;
import com.omkarComputers.service.StudentService;

@Controller
public class AdminController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("students",
                studentService.getAllStudents());

        model.addAttribute("studentCount",
                studentRepository.count());

        model.addAttribute("courseCount",
                courseRepository.count());

        model.addAttribute("enrollmentCount",
                enrollmentRepository.count());

        model.addAttribute("scoreCount",
                scoreRepository.count());

        return "admin-dashboard";
    }
    
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "edit-student";
    }
    
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/admin/dashboard";
    }
    
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String viewCourses(Model model) {

        model.addAttribute("courses",
                courseService.getAllCourses());

        return "courses";
    }
    
    @GetMapping("/addCourse")
    public String addCourseForm(Model model) {

        model.addAttribute("course", new Course());

        return "add-course";
    }
    
    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute Course course) {

        courseService.saveCourse(course);

        return "redirect:/courses";
    }
    
    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return "redirect:/courses";
    }
    
    @GetMapping("/editCourse/{id}")
    public String editCourse(@PathVariable Long id,
                             Model model) {

        Course course = courseService.getCourseById(id);

        model.addAttribute("course", course);

        return "edit-course";
    }
    
    @PostMapping("/updateCourse")
    public String updateCourse(
            @ModelAttribute Course course) {

        courseService.saveCourse(course);

        return "redirect:/courses";
    }
    
    @GetMapping("/addScore")
    public String addScoreForm(Model model) {

        model.addAttribute("score", new Score());

        model.addAttribute("students",
                studentService.getAllStudents());

        return "add-score";
    }
    
    @PostMapping("/saveScore")
    public String saveScore(@RequestParam Long studentId,
                            @ModelAttribute Score score) {

        Student student =
                studentService.getStudentById(studentId);

        score.setStudent(student);

        scoreService.saveScore(score);

        return "redirect:/scores";
    }
    
    @GetMapping("/scores")
    public String viewScores(Model model) {

        model.addAttribute("scores",
                scoreService.getAllScores());

        return "scores";
    }

    @GetMapping("/deleteScore/{id}")
    public String deleteScore(@PathVariable Long id) {

        scoreService.deleteScore(id);

        return "redirect:/scores";
    }
    
    @GetMapping("/editScore/{id}")
    public String editScore(@PathVariable Long id,
                            Model model) {

        Score score =
                scoreService.getScoreById(id);

        model.addAttribute("score", score);

        model.addAttribute("students",
                studentService.getAllStudents());

        return "edit-score";
    }
    
    @PostMapping("/updateScore")
    public String updateScore(
            @RequestParam Long studentId,
            @ModelAttribute Score score) {

        Student student =
                studentService.getStudentById(studentId);

        score.setStudent(student);

        scoreService.saveScore(score);

        return "redirect:/scores";
    }
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/enrollments")
    public String viewEnrollments(Model model) {

        model.addAttribute("enrollments",
                enrollmentService.getAllEnrollments());

        return "enrollments";
    }
    @GetMapping("/searchStudent")
    public String searchStudent(
            @RequestParam("keyword") String keyword,
            Model model) {

        model.addAttribute(
                "students",
                studentService.searchStudent(keyword));

        model.addAttribute("studentCount",
                studentRepository.count());

        model.addAttribute("courseCount",
                courseRepository.count());

        model.addAttribute("enrollmentCount",
                enrollmentRepository.count());

        model.addAttribute("scoreCount",
                scoreRepository.count());

        return "admin-dashboard";
    }

   
}

