package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.VisitorMapper;
import com.cit.backend.api.request.VisitorRequest;
import com.cit.backend.api.response.VisitorResponse;
import com.cit.backend.domain.entity.Visitant;
import com.cit.backend.domain.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/visitor-cadastration")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private VisitorMapper visitorMapper;

    @PostMapping
    public ResponseEntity<VisitorResponse> createVisitor(@RequestBody @Valid VisitorRequest request){
        Visitant visitant = visitorMapper.toVisitant(request);
        visitant = visitorService.save(visitant);
        VisitorResponse response = visitorMapper.toVisitantResponse(visitant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<VisitorResponse> getVisitorById(@PathVariable("id") Long id){
        Optional<Visitant> visitant = visitorService.findById(id);
        if(visitant == null){
            return ResponseEntity.notFound().build();
        }
        VisitorResponse response = visitorMapper.toVisitantResponse(visitant);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> visitorDelete(@PathVariable("id") Long id) {
        visitorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
