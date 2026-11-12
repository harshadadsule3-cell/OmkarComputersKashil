package com.omkarComputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkarComputers.Entity.Score;

public interface ScoreRepository
        extends JpaRepository<Score, Long> {

    List<Score> findByStudentId(Long studentId);

}