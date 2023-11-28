package com.pro.privacidade.infra.repositories;


import com.pro.privacidade.core.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
