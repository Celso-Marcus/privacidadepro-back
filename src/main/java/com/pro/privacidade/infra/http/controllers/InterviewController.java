package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.InterviewService;
import com.pro.privacidade.infra.http.dtos.InterviewDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<InterviewDTO> getAll() {
        return interviewService.getAll();
    }

    @GetMapping("{fileName:.+}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = this.interviewService.downloadFile(fileName);
        String contentType = "";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            throw new RuntimeException("Could not determine file type.");
        }

        //Content-type generic
        if (contentType.isBlank()) contentType = "application/octet-stream";

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public InterviewDTO create(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        return interviewService.create(file, fileName);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.interviewService.deleteFile(id);
    }
}
