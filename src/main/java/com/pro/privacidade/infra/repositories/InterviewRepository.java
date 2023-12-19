package com.pro.privacidade.infra.repositories;

import com.pro.privacidade.core.entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {}
