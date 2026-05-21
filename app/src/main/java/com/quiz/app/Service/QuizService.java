package com.quiz.app.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.dao.QuestionDao;
import com.quiz.app.dao.QuizDao;
import com.quiz.app.model.Question;
import com.quiz.app.model.QuestionWrapper;
import com.quiz.app.model.Quiz;
import com.quiz.app.model.Response;

@Service
public class QuizService {
    
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {
        
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (!quiz.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question : questions) {
            QuestionWrapper qw = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<String> submitQuiz(Integer id, List<Response> answers) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (!quiz.isPresent()) {
            return new ResponseEntity<>("Quiz not found", HttpStatus.NOT_FOUND);
        }
        List<Question> questions = quiz.get().getQuestions();
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getRightAnswer().equals(answers.get(i).getResponse())) {
                score++;
            }
        }
        return new ResponseEntity<>("Your score is: " + score + "/" + questions.size(), HttpStatus.OK);
    }
}
