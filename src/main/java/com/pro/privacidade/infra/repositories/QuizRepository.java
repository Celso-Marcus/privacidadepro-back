package com.pro.privacidade.infra.repositories;

import com.pro.privacidade.core.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
