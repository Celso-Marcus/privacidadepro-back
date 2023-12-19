package com.pro.privacidade.infra.repositories;

import com.pro.privacidade.core.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
