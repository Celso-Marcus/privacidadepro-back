package com.pro.privacidade.controllers;

import com.pro.privacidade.dtos.ChecklistDTO;
import com.pro.privacidade.exceptions.FileNotFoundException;
import com.pro.privacidade.exceptions.FileStorageException;
import com.pro.privacidade.services.ChecklistService;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/checklists")
public class ChecklistController {

    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @GetMapping("file/download/{fileName:.+}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Download de um arquivo", tags = {"Checklist"}, responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Resource.class), mediaType = "application/octet-stream")),
            @ApiResponse(responseCode = "404", description = "File not found", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = FileNotFoundException.class))),
            @ApiResponse(responseCode = "500", description = "Server Error", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = RuntimeException.class))),
            @ApiResponse(responseCode = "500", description = "Storage Error", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = FileStorageException.class)))
    })
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = this.checklistService.downloadFile(fileName);
        String contentType = "";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            System.out.println("Could not determine file type.");
        }

        //Content-type generic
        if (contentType.isBlank()) contentType = "application/octet-stream";

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("file/upload")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Upload de um ou mais arquivos", tags = {"Checklist"}, responses = {
            @ApiResponse(responseCode = "200", description = "File uploaded",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ChecklistDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = Exception.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = Exception.class))),
            @ApiResponse(responseCode = "500", description = "Storage Error", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = FileStorageException.class)))

    })
    public ChecklistDTO uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("id") Long id) {
        return this.checklistService.uploadFiles(files, id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar todos os checklists", tags = {"Checklist"}, responses = {
            @ApiResponse(responseCode = "200", description = "Checklists found",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ChecklistDTO.class))),
    })
    public List<ChecklistDTO> findAll() {
        return this.checklistService.getAll();
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Atualizar checklist", tags = {"Checklist"}, responses = {
            @ApiResponse(responseCode = "200", description = "Checklist updated",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ChecklistDTO.class))),
    })
    public ChecklistDTO update(@RequestBody ChecklistDTO checklistDTO) {
        return this.checklistService.update(checklistDTO);
    }

    @DeleteMapping("file/{id}/{fileName:.+}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete file", tags = {"Checklist"}, responses = {
            @ApiResponse(responseCode = "200", description = "File deleted",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ChecklistDTO.class))),
            @ApiResponse(responseCode = "404", description = "File not found", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = FileNotFoundException.class))),
            @ApiResponse(responseCode = "500", description = "Server Error", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = Exception.class))),
            @ApiResponse(responseCode = "500", description = "Storage Error", content =
            @Content(mediaType = "application/json", schema =
            @Schema(implementation = FileStorageException.class)))
    })
    public void deleteFile(@PathVariable Long id, @PathVariable String fileName) {
        this.checklistService.deleteFile(id, fileName);
    }
}
