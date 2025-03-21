package com.cit.backend.api.mapper;

import com.cit.backend.api.request.ReserveRequest;
import com.cit.backend.api.response.ReserveResponse;
import com.cit.backend.domain.entity.Reserve;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReserveMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Reserve toReserve(ReserveRequest reserve) {
        return modelMapper.map(reserve, Reserve.class);
    }

    public Set<Reserve> toReserve(Set<ReserveRequest> reserves) {
        return reserves.stream()
                .map(this::toReserve)
                .collect(Collectors.toSet());
    }

    public ReserveResponse toReserveResponse(Reserve reserve) {
        return modelMapper.map(reserve, ReserveResponse.class);
    }

    public Set<ReserveResponse> toReserveResponse(Set<Reserve> reserves) {
        return reserves.stream()
                .map(this::toReserveResponse)
                .collect(Collectors.toSet());
    }
}
