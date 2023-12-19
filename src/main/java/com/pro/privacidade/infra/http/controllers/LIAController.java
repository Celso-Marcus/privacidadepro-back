package com.pro.privacidade.infra.http.controllers;

import com.pro.privacidade.core.services.LIAService;
import com.pro.privacidade.infra.http.dtos.InventoryDTO;
import com.pro.privacidade.infra.http.dtos.LIADTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legitimate-interest")
public class LIAController {

    private final LIAService legitimateInterestService;

    public LIAController(LIAService legitimateInterestService) {
        this.legitimateInterestService = legitimateInterestService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<LIADTO> getAll() {
        return legitimateInterestService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LIADTO create(@RequestBody LIADTO liaDTO) {
        return legitimateInterestService.create(liaDTO);
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public LIADTO update(@PathVariable Long id,@RequestBody LIADTO liaDTO) {
        return legitimateInterestService.update(id, liaDTO);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        legitimateInterestService.delete(id);
    }

    @GetMapping(value = "/inventories")
    @ResponseStatus(code = HttpStatus.OK)
    public List<InventoryDTO> getLegitimateInterestInventories() {
        return legitimateInterestService.getAllLegitimateInterestInventories();
    }
}
