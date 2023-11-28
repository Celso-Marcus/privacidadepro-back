package com.pro.privacidade.core.services;

import com.pro.privacidade.infra.http.dtos.QuizDTO;
import com.pro.privacidade.core.entities.Quiz;
import com.pro.privacidade.core.exceptions.ResourceNotFound;
import com.pro.privacidade.infra.repositories.QuizRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;

    public QuizService(ModelMapper modelMapper, QuizRepository quizRepository) {
        this.modelMapper = modelMapper;
        this.quizRepository = quizRepository;
    }

    public List<QuizDTO> getAll() {
        return quizRepository.findAll()
                .stream()
                .map(quiz -> modelMapper.map(quiz, QuizDTO.class))
                .toList();
    }

    public QuizDTO findById(Long id) {
        return quizRepository.findById(id)
                .map(quiz -> modelMapper.map(quiz, QuizDTO.class))
                .orElseThrow(() -> new ResourceNotFound("Quiz", id));
    }

    public QuizDTO create(QuizDTO quizDTO) {
        return modelMapper.map(quizRepository.save(modelMapper.map(quizDTO, Quiz.class)), QuizDTO.class);
    }

    public void delete(Long id) {
        var quiz = this.quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Quiz", id));
        this.quizRepository.delete(quiz);
    }

}
