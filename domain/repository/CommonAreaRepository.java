package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.CommonArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonAreaRepository extends JpaRepository<CommonArea, Long> {
    List<CommonArea> findAllByCondominiumId(long condominiumId);
}
