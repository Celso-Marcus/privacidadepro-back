package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.ReasonDataService;
import com.pro.privacidade.infra.http.dtos.ReasonDataDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reason-data")
@Tag(name = "Razão Legal de Dados")
public class ReasonDataController {

    private final ReasonDataService reasonDataService;

    public ReasonDataController(ReasonDataService reasonDataService) {
        this.reasonDataService = reasonDataService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar todos os motivos de dados"
            , responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<ReasonDataDTO> getAll() {
        return reasonDataService.getAll();
    }
}
