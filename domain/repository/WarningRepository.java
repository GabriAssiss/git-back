package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningRepository extends JpaRepository<Warning, Long> {
}
