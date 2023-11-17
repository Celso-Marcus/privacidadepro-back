package com.pro.privacidade.services;

import com.pro.privacidade.dtos.InventoryDTO;
import com.pro.privacidade.entities.Inventory;
import com.pro.privacidade.exceptions.ResourceNotFound;
import com.pro.privacidade.repositories.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;

    public InventoryService(ModelMapper modelMapper, InventoryRepository inventoryRepository) {
        this.modelMapper = modelMapper;
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryDTO> getAll() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTO.class))
                .toList();
    }

    public InventoryDTO findById(Long id) {
        return inventoryRepository.findById(id)
                .map(inventory -> modelMapper.map(inventory, InventoryDTO.class))
                .orElseThrow(() -> new ResourceNotFound("Inventory", id));
    }

    public InventoryDTO create(InventoryDTO inventoryDTO) {
        inventoryDTO.setCreatedAt(LocalDateTime.now());
        return modelMapper.map(inventoryRepository.save(modelMapper.map(inventoryDTO, Inventory.class)), InventoryDTO.class);
    }

    public InventoryDTO update(Long id, InventoryDTO inventoryDTO) {
        var inventory = this.inventoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Inventory", id));

        Optional.ofNullable(inventoryDTO.getTagName()).ifPresent(inventory::setTagName);

        if (inventoryDTO.getCollectedData() != null) inventory.setCollectedData(inventoryDTO.getCollectedData());
        if (inventoryDTO.getSourceData() != null) inventory.setSourceData(inventoryDTO.getSourceData());
        if (inventoryDTO.getReasonData() != null) inventory.setReasonData(inventoryDTO.getReasonData());
        if (inventoryDTO.getHowStorage() != null) inventory.setHowStorage(inventoryDTO.getHowStorage());
        if (inventoryDTO.getSecurityData() != null) inventory.setSecurityData(inventoryDTO.getSecurityData());
        if (inventoryDTO.getDeadlineData() != null) inventory.setDeadlineData(inventoryDTO.getDeadlineData());
        if (inventoryDTO.getJustificationData() != null) inventory.setJustificationData(inventoryDTO.getJustificationData());
        if (inventoryDTO.getTagName() != null) inventory.setTagName(inventoryDTO.getTagName());
        if (inventoryDTO.getSensitiveData() != null) inventory.setSensitiveData(inventoryDTO.getSensitiveData());
        if (inventoryDTO.getController() != null) inventory.setController(inventoryDTO.getController());

        inventory.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(inventoryRepository.save(inventory), InventoryDTO.class);
    }

    public void delete(Long id) {
        var inventory = this.inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Inventory", id));
        this.inventoryRepository.delete(inventory);
    }
}
