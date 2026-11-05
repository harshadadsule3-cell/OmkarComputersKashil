package com.omkarComputers.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omkarComputers.Entity.Score;
import com.omkarComputers.repository.ScoreRepository;



@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);
    }
    
    public Score getScoreById(Long id) {

        return scoreRepository
                .findById(id)
                .orElse(null);
    }
}

