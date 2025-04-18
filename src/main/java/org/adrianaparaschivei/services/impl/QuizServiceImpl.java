package org.adrianaparaschivei.services.impl;

import org.adrianaparaschivei.models.Quiz;
import org.adrianaparaschivei.services.QuizService;

public class QuizServiceImpl implements QuizService {
    private static QuizServiceImpl instance;

    private QuizServiceImpl() {}

    public static QuizServiceImpl getInstance() {
        if (instance == null) {
            instance = new QuizServiceImpl();
        }
        return instance;
    }

    @Override
    public void addQuestion(Quiz quiz, String question, String answer) {
        quiz.getQuestions().add(question);
        quiz.getAnswers().add(answer);
        System.out.println("Added question: \"" + question + "\" with answer: \"" + answer + "\" to quiz: " + quiz.getTitle());
    }

    @Override
    public void printQuizDetails(Quiz quiz) {
        System.out.println("Quiz: " + quiz.getTitle());
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            System.out.println((i + 1) + ". Question: " + quiz.getQuestions().get(i) + " | Answer: " + quiz.getAnswers().get(i));
        }
    }
}
