package com.microservice.question_service.dao;

import org.springframework.stereotype.Repository;
import com.microservice.question_service.model.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    public List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    public List<Integer> findRandomQuestionsByCategory(String category, int numQuestions);

}
