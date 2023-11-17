package com.pro.privacidade.controllers;

import com.pro.privacidade.dtos.QuizDTO;
import com.pro.privacidade.services.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Listar todos os quizes feitos"
            , tags = {"Quiz"}
            , responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuizDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    public List<QuizDTO> getAll() {
        return quizService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Procurar um quiz pelo ID"
            , tags = {"Quiz"}
            , responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuizDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Quiz não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    public QuizDTO findById(@PathVariable Long id) {
        return quizService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Registrar um novo quiz"
            , tags = {"Quiz"}
            , responses = {
                    @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuizDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    public QuizDTO create(@RequestBody @Valid QuizDTO quizDTO) {
        return quizService.create(quizDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um quiz pelo ID"
            , tags = {"Quiz"}
            , responses = {
                    @ApiResponse(responseCode = "204", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuizDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                    @ApiResponse(responseCode = "404", description = "Quiz não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    public void delete(@PathVariable Long id) {
        quizService.delete(id);
    }
}
