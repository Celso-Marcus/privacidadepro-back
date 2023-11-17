package com.pro.privacidade.controllers;


import com.pro.privacidade.dtos.InventoryDTO;
import com.pro.privacidade.services.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar todos os inventários"
            , tags = {"Inventory"}
            , responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InventoryDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    }
    )
    public List<InventoryDTO> getAll() {
        return inventoryService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Procurar um inventário pelo ID"
            , tags = {"Inventory"}
            , responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InventoryDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Inventário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    }
    )
    public InventoryDTO findById(@PathVariable Long id) {
        return inventoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Registrar um novo inventário"
            , tags = {"Inventory"}
            , responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InventoryDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    }
    )
    public InventoryDTO create(@RequestBody @Valid InventoryDTO inventoryDTO) {
        return inventoryService.create(inventoryDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Atualizar um inventário pelo ID"
            , description = "O ID do inventário deve ser informado na URL da requisição e os dados a serem atualizados devem ser informados no corpo da requisição"
            , tags = {"Inventory"}
            , responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = InventoryDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Inventário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    }
    )
    public void update(@PathVariable Long id, @RequestBody @Valid InventoryDTO inventoryDTO) {
        inventoryService.update(id, inventoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir um inventário pelo ID"
            , tags = {"Inventory"}
            , responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Inventário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    }
    )
    public void delete(@PathVariable Long id) {
        inventoryService.delete(id);
    }
}
