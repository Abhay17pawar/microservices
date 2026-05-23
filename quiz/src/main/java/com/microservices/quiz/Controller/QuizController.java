package com.microservices.quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.quiz.Service.QuizService;
import com.microservices.quiz.model.QuestionWrapper;
import com.microservices.quiz.model.Quizdto;
import com.microservices.quiz.model.Response;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;
    
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody Quizdto quizdto) {
        return quizservice.createQuiz(quizdto.getCategoryName(), quizdto.getNumQuestions(), quizdto.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Integer id) {
        return quizservice.getQuizQuestionsById(id);
    }

    @PostMapping("/result/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> answers) {
        return quizservice.submitQuiz(id, answers);
    }

}
