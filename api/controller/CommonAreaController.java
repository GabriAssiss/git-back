package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.CommonAreaMapper;
import com.cit.backend.api.mapper.ReserveMapper;
import com.cit.backend.api.request.CommonAreaRequest;
import com.cit.backend.api.request.ReserveRequest;
import com.cit.backend.api.response.CommonAreaResponse;
import com.cit.backend.api.response.ReserveResponse;
import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.service.CommonAreaService;
import com.cit.backend.domain.service.EmployeeService;
import com.cit.backend.domain.service.ReserveService;
import com.cit.backend.domain.service.ResidentService;
import com.cit.backend.exceptions.MissingVariableException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/common-area")
public class CommonAreaController {
    @Autowired
    private CommonAreaService commonAreaService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private ReserveMapper reserveMapper;

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<CommonAreaResponse> createCommonArea(@RequestBody CommonAreaRequest request) {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByProfile(profile);

        CommonArea commonArea = commonAreaMapper.toCommonArea(request);
        commonArea.setCondominium(employee.getCondominium());
        commonArea = commonAreaService.save(commonArea);
        CommonAreaResponse response = commonAreaMapper.toCommonAreaResponse(commonArea);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reserve/{id:\\d+}")
    @RolesAllowed("RESIDENT")
    public ResponseEntity<Set<ReserveResponse>> reserveCommonArea(@PathVariable("id") Long id, @RequestBody Set<ReserveRequest> request) {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Resident resident = residentService.getFromProfile(profile);

        final CommonArea commonArea = commonAreaService.findById(id);
        if (commonArea == null) return ResponseEntity.notFound().build();

        Set<Reserve> reserves = reserveMapper.toReserve(request);
        reserves.forEach(reserve -> {
            reserve.setCommonArea(commonArea);
            reserve.setApartment(resident.getApartment());
        });
        Set<Reserve> savedReserves = reserveService.reserve(reserves);
        Set<ReserveResponse> response = reserveMapper.toReserveResponse(savedReserves);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CommonAreaResponse>> getCommonAreas() {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Resident resident = residentService.getFromProfile(profile);

        List<CommonArea> commonAreas;
        if (resident != null) commonAreas = commonAreaService.getCommonAreas(resident);
        else commonAreas = commonAreaService.getCommonAreas();

        List<CommonAreaResponse> response = commonAreaMapper.toCommonAreaResponse(commonAreas);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-condominium/{id:\\d+}")
    public ResponseEntity<List<CommonAreaResponse>> getCommonAreaByCondominium(@PathVariable("id") Long condominiumId) {
        List<CommonArea> commonAreas = commonAreaService.getCommonAreasByCondominiumId(condominiumId);
        List<CommonAreaResponse> response = commonAreaMapper.toCommonAreaResponse(commonAreas);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<CommonAreaResponse> getCommonAreaById(@PathVariable("id") Long id) {
        CommonArea commonArea = commonAreaService.findById(id);
        if (commonArea == null) return ResponseEntity.notFound().build();

        CommonAreaResponse response = commonAreaMapper.toCommonAreaResponse(commonArea);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<CommonAreaResponse> putCommonArea(@RequestBody CommonAreaRequest request) {
        if (request.getId() == null)
            throw new MissingVariableException("The id of the Common Area is needed", List.of("id"));

        CommonArea commonArea = commonAreaMapper.toCommonArea(request);
        commonArea = commonAreaService.save(commonArea);
        CommonAreaResponse response = commonAreaMapper.toCommonAreaResponse(commonArea);

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<CommonAreaResponse> updateCommonArea(@RequestBody CommonAreaRequest request) {
        if (request.getId() == null)
            throw new MissingVariableException("The id of the Common Area is needed", List.of("id"));

        CommonArea commonArea = commonAreaMapper.toCommonArea(request);
        commonArea = commonAreaMapper.fillNullFields(commonArea, commonAreaService.findById(request.getId()));
        commonArea = commonAreaService.save(commonArea);
        CommonAreaResponse response = commonAreaMapper.toCommonAreaResponse(commonArea);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id:\\d+}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> deleteCommonArea(@PathVariable("id") Long id) {
        commonAreaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
