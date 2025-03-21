package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.People;
import com.cit.backend.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository<T extends People> extends JpaRepository<T, Long> {
    Optional<T> findByCpf(String Cpf);
    Optional<T> findByProfile(Profile profile);
}
