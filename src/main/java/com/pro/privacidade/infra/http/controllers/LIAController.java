package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.LIAService;
import com.pro.privacidade.infra.http.dtos.InventoryDTO;
import com.pro.privacidade.infra.http.dtos.LIADTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legitimate-interest")
@Tag(name = "Avaliação de Interesse Legítimo")
public class LIAController {

    private final LIAService legitimateInterestService;

    public LIAController(LIAService legitimateInterestService) {
        this.legitimateInterestService = legitimateInterestService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar todos os motivos de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LIADTO.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<LIADTO> getAll() {
        return legitimateInterestService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Criar um novo motivo de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LIADTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public LIADTO create(@RequestBody LIADTO liaDTO) {
        return legitimateInterestService.create(liaDTO);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Atualizar um motivo de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LIADTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Motivo de dados não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public LIADTO update(@PathVariable Long id,@RequestBody LIADTO liaDTO) {
        return legitimateInterestService.update(id, liaDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um motivo de dados pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Motivo de dados não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void delete(@PathVariable Long id) {
        legitimateInterestService.delete(id);
    }

    @GetMapping(value = "/inventories")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar todos os inventários com legítimo interesse", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InventoryDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<InventoryDTO> getLegitimateInterestInventories() {
        return legitimateInterestService.getAllLegitimateInterestInventories();
    }
}
