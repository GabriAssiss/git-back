package com.cit.backend.domain.service;

import com.cit.backend.domain.entity.Reserve;
import com.cit.backend.domain.repository.ReserveRepository;
import com.cit.backend.exceptions.DateConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    public Reserve reserve(Reserve reserve) {
        if (!reserveRepository.findConflict(
                reserve.getDate(),
                reserve.getCommonArea().getId(),
                reserve.getTimeStart(),
                reserve.getTimeEnd()
        ).isEmpty()) {
            throw new DateConflictException("There is already a reserve for this date and time");
        }

        return reserveRepository.save(reserve);
    }

    private void validateReserves(List<Reserve> reserves) {
        Reserve firstReserve = reserves.getFirst();
        for (Reserve reserve : reserves) {
            if (!reserve.getCommonArea().getId().equals(firstReserve.getCommonArea().getId()))
                throw new IllegalArgumentException("All reserves must be for the same common area");

            for (int start = reserves.indexOf(reserve) + 1; start < reserves.size(); start++) {
                Reserve nextReserve = reserves.get(start);
                if (reserve.getDate().equals(nextReserve.getDate())) {
                    if (reserve.getTimeStart().isBefore(nextReserve.getTimeEnd()) &&
                            reserve.getTimeEnd().isAfter(nextReserve.getTimeStart()))
                        throw new DateConflictException("Date conflict between reserves");
                }
            }
        }
    }

    public Set<Reserve> reserve(Set<Reserve> reserves) {
        if (reserves.isEmpty()) return null;

        validateReserves(reserves.stream().toList());
        return reserves.stream()
                .map(this::reserve)
                .collect(Collectors.toSet());
    }
}
