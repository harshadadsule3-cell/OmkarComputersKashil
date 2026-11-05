package com.omkarComputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkarComputers.Entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {

}