package com.pro.privacidade.infra.repositories;

import com.pro.privacidade.core.entities.LIA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LIARepository extends JpaRepository<LIA, Long> {

    Optional<LIA> findByInventoryName(String inventoryName);
}
