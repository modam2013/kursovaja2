package com.example.kursovaja2.service;

import com.example.kursovaja2.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
