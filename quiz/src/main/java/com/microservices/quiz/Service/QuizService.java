package com.microservices.quiz.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quiz.dao.QuizDao;
import com.microservices.quiz.feign.QuizInterface;
import com.microservices.quiz.model.*;

@Service
public class QuizService {
    
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {
        
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (!quiz.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Integer> questionIds = quiz.get().getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questionWrappers = quizInterface.getQuestionsByIds(questionIds);

        return new ResponseEntity<>(questionWrappers.getBody(), HttpStatus.OK);
    }

     public ResponseEntity<String> submitQuiz(Integer id, List<Response> answers) {

        ResponseEntity<Integer> scoreResponse = quizInterface.getScore(answers);
        int score = scoreResponse.getBody();

        return new ResponseEntity<>("Your score is: " + score + "/" , HttpStatus.OK);
     }
}
