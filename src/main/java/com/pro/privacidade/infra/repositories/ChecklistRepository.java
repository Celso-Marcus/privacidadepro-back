package com.pro.privacidade.infra.repositories;

import com.pro.privacidade.core.entities.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

}
