package com.omkarComputers.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;

    private Double marks;

    private String grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Default Constructor
    public Score() {

    }

    // Parameterized Constructor
    public Score(Long id, String subjectName,
                 Double marks, String grade,
                 Student student) {
        this.id = id;
        this.subjectName = subjectName;
        this.marks = marks;
        this.grade = grade;
        this.student = student;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
