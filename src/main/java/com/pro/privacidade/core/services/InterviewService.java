package com.pro.privacidade.core.services;

import com.pro.privacidade.core.entities.Interview;
import com.pro.privacidade.core.exceptions.ResourceNotFound;
import com.pro.privacidade.infra.http.dtos.InterviewDTO;
import com.pro.privacidade.infra.repositories.InterviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final FileStorageService fileStorageService;
    private final ModelMapper modelMapper;

    public InterviewService( InterviewRepository interviewRepository, FileStorageService fileStorageService, ModelMapper modelMapper) {
        this.fileStorageService = fileStorageService;
        this.modelMapper = modelMapper;
        this.interviewRepository = interviewRepository;
    }

    public List<InterviewDTO> getAll() {
        return interviewRepository
                .findAll()
                .stream()
                .map(interview -> modelMapper.map(interview, InterviewDTO.class))
                .toList();
    }

    public InterviewDTO create(MultipartFile file, String newFilename){
        var fileName = fileStorageService.storeInterviewFile(file, newFilename);
        var interview = new InterviewDTO();

        interview.setFilePath(fileName);
        interview.setCreatedAt(LocalDateTime.now());

        return modelMapper.map(interviewRepository.save(modelMapper.map(interview, Interview.class)), InterviewDTO.class);
    }

    public Resource downloadFile(String fileName) {
        return fileStorageService.loadFileAsResource(fileName);
    }

    public void deleteFile(Long id) {
        var interview = this.interviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Interview not found", id));
        var fileName = interview.getFilePath();
        fileStorageService.deleteFile(fileName);
        this.interviewRepository.delete(interview);
    }
}
