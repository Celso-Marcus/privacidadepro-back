package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.LIAService;
import com.pro.privacidade.core.services.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
@Tag(name = "Gerar PDF")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/inventory/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Gerar PDF de inventário", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/pdf")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public byte[] getInventoryPdf(@PathVariable Long id) {
        return pdfService.getInventoryPdf(id);
    }

    @GetMapping(value = "/lia/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Gerar PDF de LIA", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/pdf")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<byte[]> getAllLegitimateInterestInventories(@PathVariable Long id) {
        var pdfBytes = pdfService.getLIAPdf(id);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=info_ponto.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity.ok().headers(headers).contentLength(pdfBytes.length).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
    }

}
