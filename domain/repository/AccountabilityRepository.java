package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.Condominium;
import com.cit.backend.domain.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountabilityRepository extends JpaRepository<Income, Long> {
    public List<Income> findAllByCondominium(Condominium condominium);
}
