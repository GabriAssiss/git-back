package com.cit.backend.api.mapper;

import com.cit.backend.api.request.DeliveriesRequest;
import com.cit.backend.api.response.DeliveriesResponse;
import com.cit.backend.domain.entity.Deliveries;
import com.cit.backend.domain.service.ApartmentService;
import com.cit.backend.domain.service.DeliveriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveriesMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApartmentService apartmentService;

    public Deliveries toDeliveries(DeliveriesRequest request){
        Deliveries deliveries = modelMapper.map(request, Deliveries.class);

        deliveries.setApartment(apartmentService.findById(request.getApartmentId()));

        return deliveries;
    }

    public DeliveriesResponse toDeliveriesResponse(Deliveries deliveries){
        DeliveriesResponse response = modelMapper.map(deliveries, DeliveriesResponse.class);

        response.setApartmentId(deliveries.getApartment().getId());

        return response;
    }

    public List<DeliveriesResponse> toDeliveriesResponse(List<Deliveries> deliveries) {
        return deliveries.stream().map(this::toDeliveriesResponse).toList();
    }
}
