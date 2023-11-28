package com.pro.privacidade.core.services;

import com.pro.privacidade.infra.http.dtos.InventoryDTO;
import com.pro.privacidade.core.entities.Inventory;
import com.pro.privacidade.core.exceptions.ResourceNotFound;
import com.pro.privacidade.infra.repositories.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        var creationTime = LocalDateTime.now();
        inventoryDTO.setCreatedAt(creationTime);
        inventoryDTO.setUpdatedAt(creationTime);
        return modelMapper.map(inventoryRepository.save(modelMapper.map(inventoryDTO, Inventory.class)), InventoryDTO.class);
    }

    public void update(Long id, InventoryDTO inventoryDTO) {
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
        if (inventoryDTO.getUnderAgeData()) inventory.setUnderAgeData(inventoryDTO.getUnderAgeData());
        if (inventoryDTO.getSystemNames() != null) inventory.setSystemNames(inventoryDTO.getSystemNames());
        if (inventoryDTO.getOperators() != null) inventory.setOperators(inventoryDTO.getOperators());
        if (inventoryDTO.getForeignData() != null) inventory.setForeignData(inventoryDTO.getForeignData());
        if (inventoryDTO.getOperators() != null) inventory.setOperators(inventoryDTO.getOperators());

        inventory.setUpdatedAt(LocalDateTime.now());
        inventoryRepository.save(inventory);
    }

    public void delete(Long id) {
        var inventory = this.inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Inventory", id));
        this.inventoryRepository.delete(inventory);
    }
}
