package com.cit.backend.domain.repository;

import com.cit.backend.domain.entity.Apartment;
import com.cit.backend.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OmbudsmanRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByApartment(Apartment apartment);
    List<Ticket> findAllByApartmentIn(Set<Apartment> apartments);
}
