package com.quiz.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.app.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
    


}
