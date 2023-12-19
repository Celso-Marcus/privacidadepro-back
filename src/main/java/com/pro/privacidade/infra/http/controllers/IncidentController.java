package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.IncidentService;
import com.pro.privacidade.infra.http.dtos.IncidentDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incident")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<IncidentDTO> getAll() {
        return incidentService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public IncidentDTO getById(Long id) {
        return incidentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public IncidentDTO create(@RequestBody @Valid IncidentDTO incidentDTO) {
        return incidentService.create(incidentDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        incidentService.delete(id);
    }
}
