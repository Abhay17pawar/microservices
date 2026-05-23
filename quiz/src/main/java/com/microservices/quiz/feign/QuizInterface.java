package com.microservices.quiz.feign;   

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import java.util.List;
import com.microservices.quiz.model.QuestionWrapper;
import com.microservices.quiz.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
        (@RequestParam String category, @RequestParam int numQuestions);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestBody List<Integer> ids);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
