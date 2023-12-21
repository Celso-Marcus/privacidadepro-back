package com.pro.privacidade.core.services;

import com.pro.privacidade.infra.http.dtos.ChecklistDTO;
import com.pro.privacidade.core.exceptions.ResourceNotFound;
import com.pro.privacidade.infra.repositories.ChecklistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class ChecklistService {

    private final ModelMapper modelMapper;
    private final ChecklistRepository checklistRepository;
    private final FileStorageService fileStorageService;

    public ChecklistService(ModelMapper modelMapper, ChecklistRepository checklistRepository,
                            FileStorageService fileStorageService) {
        this.modelMapper = modelMapper;
        this.checklistRepository = checklistRepository;
        this.fileStorageService = fileStorageService;
    }

    public Resource downloadFile(String fileName) {
        return fileStorageService.loadFileAsResource(fileName, true);
    }

    public ChecklistDTO uploadFiles(MultipartFile[] files, Long id) {
        var checklist = this.checklistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Checklist not found", id));

        String dateNow = LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                .replace(" ", "_").replace("/","_")
                .replace(":","_");

        Arrays.stream(files).forEach(file -> {
            String newFileName =  dateNow + "_" + checklist.getCategory() + "_";
            var fileName = fileStorageService.storeFile(file, newFileName, true);
            checklist.getFiles().add(fileName);
        });

        checklist.setUpdatedAt(LocalDateTime.now());

        return modelMapper.map(this.checklistRepository.save(checklist), ChecklistDTO.class);
    }

    public List<ChecklistDTO> getAll() {
        return checklistRepository.findAll()
                .stream()
                .map(checklist -> modelMapper.map(checklist, ChecklistDTO.class))
                .toList();
    }

    public ChecklistDTO findOne(Long id) {
        var checklist = this.checklistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Checklist not found", id));
        return modelMapper.map(checklist, ChecklistDTO.class);
    }

    public ChecklistDTO update(ChecklistDTO checklistDTO) {
        var checklist = this.checklistRepository.findById(checklistDTO.getId())
                .orElseThrow(() -> new ResourceNotFound("Checklist not found", checklistDTO.getId()));

        if(checklistDTO.getAnswers() != null) {
            checklist.setAnswers(checklistDTO.getAnswers());
            checklist.setUpdatedAt(LocalDateTime.now());
        }

        return this.modelMapper.map(this.checklistRepository.save(checklist), ChecklistDTO.class);
    }

    public void deleteFile(long id, String fileName) {
        var checklist = this.checklistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Checklist not found", id));
        this.fileStorageService.deleteFile(fileName, true);
        checklist.getFiles().remove(fileName);
        checklist.setUpdatedAt(LocalDateTime.now());
        this.checklistRepository.save(checklist);
    }
}
