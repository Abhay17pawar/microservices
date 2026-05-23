package com.microservices.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.quiz.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
    


}
