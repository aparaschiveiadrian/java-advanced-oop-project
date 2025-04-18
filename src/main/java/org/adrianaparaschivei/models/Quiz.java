package org.adrianaparaschivei.models;

import java.util.List;

public class Quiz {
    private String title;
    private List<String> questions;
    private List<String> answers;

    public Quiz(String title, List<String> questions, List<String> answers) {
        this.title = title;
        this.questions = questions;
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Quiz Title: " + title;
    }
}
