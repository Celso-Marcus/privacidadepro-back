package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.IncidentService;
import com.pro.privacidade.infra.http.dtos.IncidentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
@Tag(name = "Incidentes")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar todos os incidentes", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = IncidentDTO.class))}),
    })
    public List<IncidentDTO> getAll() {
        return incidentService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Procurar um incidente pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = IncidentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Incidente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public IncidentDTO getById(Long id) {
        return incidentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Registrar um novo incidente", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = IncidentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public IncidentDTO create(@RequestBody @Valid IncidentDTO incidentDTO) {
        return incidentService.create(incidentDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um incidente pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Incidente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void delete(Long id) {
        incidentService.delete(id);
    }
}
