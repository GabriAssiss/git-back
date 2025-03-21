package com.cit.backend.api.mapper;

import com.cit.backend.api.request.OmbudsmenRequest;
import com.cit.backend.api.response.OmbudsmenResponse;
import com.cit.backend.domain.entity.Ticket;
import com.cit.backend.domain.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class OmbudsmanMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeService employeeService;

    public Ticket toTicket(OmbudsmenRequest ombudsman) {
        Ticket ticket = modelMapper.map(ombudsman, Ticket.class);
        if (ombudsman.getEmployeeId() != null) ticket.setEmployee(employeeService.findById(ombudsman.getEmployeeId()));
        return ticket;
    }

    public OmbudsmenResponse toOmbudsmenResponse(Ticket ticket) {
        OmbudsmenResponse response = modelMapper.map(ticket, OmbudsmenResponse.class);
        if (ticket.getEmployee() != null) response.setEmployeeId(ticket.getEmployee().getId());
        response.setApartmentId(ticket.getApartment().getId());
        return response;
    }

    public List<OmbudsmenResponse> toOmbudsmenResponse(List<Ticket> tickets) {
        return tickets.stream().map(this::toOmbudsmenResponse).toList();
    }

    // TODO analisar outros meios e se o contexto faz sentido
    public void fillNullFields(Ticket current, Ticket target) {
        List<Method> getters = Arrays.stream(Ticket.class.getMethods()).filter(method -> method.getName().startsWith("get")).toList();
        List<Method> setters = Arrays.stream(Ticket.class.getMethods()).filter(method -> method.getName().startsWith("set")).toList();
        getters.forEach(getter -> {
            try {
                if (getter.invoke(current) == null) {
                    String fieldName = getter.getName().substring(3);
                    Method setter = setters.stream().filter(method -> method.getName().substring(3).equals(fieldName)).findFirst().orElse(null);
                    if (setter != null) setter.invoke(current, getter.invoke(target));
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
