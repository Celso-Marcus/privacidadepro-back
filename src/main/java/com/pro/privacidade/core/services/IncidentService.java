package com.pro.privacidade.core.services;

import com.pro.privacidade.core.entities.Incident;
import com.pro.privacidade.core.exceptions.ResourceNotFound;
import com.pro.privacidade.infra.http.dtos.IncidentDTO;
import com.pro.privacidade.infra.repositories.IncidentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final ModelMapper modelMapper;

    public IncidentService(IncidentRepository incidentRepository, ModelMapper modelMapper) {
        this.incidentRepository = incidentRepository;
        this.modelMapper = modelMapper;
    }

    public List<IncidentDTO> getAll() {
        return incidentRepository
                .findAll()
                .stream()
                .map(incident -> modelMapper.map(incident, IncidentDTO.class))
                .toList();
    }

    public IncidentDTO findById(Long id) {
        return incidentRepository
                .findById(id)
                .map(incident -> modelMapper.map(incident, IncidentDTO.class))
                .orElseThrow(() -> new ResourceNotFound("Incident", id));
    }

    public IncidentDTO create(IncidentDTO incidentDTO) {
        incidentDTO.setCreatedAt(LocalDateTime.now());
        return modelMapper.map(incidentRepository.save(modelMapper.map(incidentDTO, Incident.class)), IncidentDTO.class);
    }

    public void delete(Long id) {
        incidentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("Incident", id));
        incidentRepository.deleteById(id);
    }
}
