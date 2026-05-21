package com.quiz.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.Service.QuizService;
import com.quiz.app.model.QuestionWrapper;
import com.quiz.app.model.Response;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizservice;
    
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQuestions, @RequestParam String title) {
        return quizservice.createQuiz(category, numQuestions, title);
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
