package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
    @Query("SELECT r FROM reserves r WHERE r.date = ?1 AND r.commonArea.id = ?2 AND (r.timeStart < ?4 AND r.timeEnd > ?3)")
    List<Reserve> findConflict(LocalDate date, Long commonAreaId, LocalTime timeStart, LocalTime timeEnd);
}
