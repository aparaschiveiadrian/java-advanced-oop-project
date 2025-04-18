package org.adrianaparaschivei.services;

import org.adrianaparaschivei.models.Quiz;

public interface QuizService {
    void addQuestion(Quiz quiz, String question, String answer);
    void printQuizDetails(Quiz quiz);
}
