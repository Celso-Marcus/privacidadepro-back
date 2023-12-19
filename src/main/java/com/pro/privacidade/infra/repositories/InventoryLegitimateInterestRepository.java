package com.pro.privacidade.infra.repositories;

import com.pro.privacidade.core.entities.Inventory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryLegitimateInterestRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public InventoryLegitimateInterestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Inventory> getAllLegitimateInterest() {
        //hibernate não liga para o nome no banco, e sim para o nome da classe no código
        return entityManager
                .createQuery("SELECT i FROM Inventory i WHERE i.reasonData = 'Legítimo interesse - Interesses legítimos do controlador ou de terceiro'"
                        , Inventory.class)
                .getResultList();
    }
}
