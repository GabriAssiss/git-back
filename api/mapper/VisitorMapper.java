package com.cit.backend.api.mapper;

import com.cit.backend.api.request.VisitorRequest;
import com.cit.backend.api.response.VisitorResponse;
import com.cit.backend.domain.entity.Visitant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Optional;

@Component
public class VisitorMapper {

    private ModelMapper modelMapper;

    public Visitant toVisitant(VisitorRequest request) {
        return modelMapper.map(request, Visitant.class);
    }

    public VisitorResponse toVisitantResponse(Visitant visitant) {
        VisitorResponse response = modelMapper.map(visitant, VisitorResponse.class);
        return response;
    }

    public VisitorResponse toVisitantResponse(Optional<Visitant> visitant) {
        VisitorResponse response = modelMapper.map(visitant, VisitorResponse.class);
        return response;
    }

}
