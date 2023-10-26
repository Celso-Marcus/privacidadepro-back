package com.pro.privacidade.controllers;

import com.pro.privacidade.dtos.QuizDTO;
import com.pro.privacidade.services.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<QuizDTO> getAll() {
        return quizService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public QuizDTO findById(@PathVariable Long id) {
        return quizService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public QuizDTO create(@RequestBody @Valid QuizDTO quizDTO) {
        return quizService.create(quizDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        quizService.delete(id);
    }
}
