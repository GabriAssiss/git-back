package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.LostAndFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostAndFoundRepository extends JpaRepository<LostAndFound, Long> {

}
