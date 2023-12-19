package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.LIAService;
import com.pro.privacidade.core.services.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/inventory/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public byte[] getInventoryPdf(@PathVariable Long id) {
        return pdfService.getInventoryPdf(id);
    }

    @GetMapping(value = "/download/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<byte[]> getAllLegitimateInterestInventories(@PathVariable Long id) {
        var pdfBytes = pdfService.getLIAPdf(id);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=info_ponto.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE);

        return ResponseEntity.ok().headers(headers).contentLength(pdfBytes.length).contentType(MediaType.APPLICATION_PDF).body(pdfBytes);
    }

}
