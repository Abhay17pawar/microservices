package com.microservices.quiz.model;

import lombok.Data;

@Data
public class Quizdto {
    
    String categoryName;
    Integer numQuestions;
    String title;

}
