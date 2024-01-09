package com.pro.privacidade.core.services;

import com.pro.privacidade.core.entities.ReasonData;
import com.pro.privacidade.core.exceptions.ResourceNotFound;
import com.pro.privacidade.infra.http.dtos.ReasonDataDTO;
import com.pro.privacidade.infra.repositories.ReasonDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReasonDataService {

    private final ModelMapper modelMapper;
    private final ReasonDataRepository reasonDataRepository;

    public ReasonDataService(ModelMapper modelMapper, ReasonDataRepository reasonDataRepository) {
        this.modelMapper = modelMapper;
        this.reasonDataRepository = reasonDataRepository;
    }

    public List<ReasonDataDTO> getAll() {
        return reasonDataRepository.findAll()
                .stream()
                .map(reasonData -> modelMapper.map(reasonData, ReasonDataDTO.class))
                .toList();
    }

    public ReasonData findById(Long id) {
        return reasonDataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("ReasonData", id));
    }
}
