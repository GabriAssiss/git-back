package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository  extends JpaRepository<Block, Long> {}
