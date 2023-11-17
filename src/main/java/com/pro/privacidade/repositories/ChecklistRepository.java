package com.pro.privacidade.repositories;

import com.pro.privacidade.entities.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

}
