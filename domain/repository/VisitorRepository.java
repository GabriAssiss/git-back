package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.Visitant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitant, Long> {

    Optional<Visitant> findById(Long id);
}
