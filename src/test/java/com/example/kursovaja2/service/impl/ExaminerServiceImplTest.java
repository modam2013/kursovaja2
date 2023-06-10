package com.example.kursovaja2.service.impl;

import com.example.kursovaja2.exception.IncorrectAmountOfQuestions;
import com.example.kursovaja2.model.Question;
import com.example.kursovaja2.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Collection<Question> questions = Set.of(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3"),
            new Question("Q4", "A4"),
            new Question("Q5", "A5")
    );

    @Test
    public void getQuestionsNegativeTest() {
        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
        assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));
    }

    @Test
    public void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Q4", "A4"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5"),
                new Question("Q2", "A2")
        );

        assertThat(examinerService.getQuestions(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Q4", "A4"),
                        new Question("Q2", "A2"),
                        new Question("Q5", "A5")
                );
    }
}
