package com.cit.backend.domain.repository;
import com.cit.backend.domain.entity.Apartment;
import com.cit.backend.domain.entity.Condominium;
import com.cit.backend.domain.entity.Deliveries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveriesRepository extends JpaRepository<Deliveries, Long>{
    public List<Deliveries> findAllByApartment(Apartment apartment);
}
