package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominiumRepository extends JpaRepository<Condominium, Long> {
    Condominium findByCnpj(String cnpj);
    Condominium findByManagerId(long managerId);
}
