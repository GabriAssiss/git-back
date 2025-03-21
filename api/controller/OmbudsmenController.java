package com.cit.backend.api.controller;

import com.cit.backend.api.mapper.OmbudsmanMapper;
import com.cit.backend.api.request.OmbudsmenRequest;
import com.cit.backend.api.response.OmbudsmenResponse;
import com.cit.backend.domain.entity.*;
import com.cit.backend.domain.entity.enums.ProfilePermissions;
import com.cit.backend.domain.entity.enums.StatusTicket;
import com.cit.backend.domain.service.EmployeeService;
import com.cit.backend.domain.service.OmbudsmanService;
import com.cit.backend.domain.service.ResidentService;
import com.cit.backend.domain.service.UploadFilesService;
import com.cit.backend.exceptions.EmployeeNotRegisteredInACondominiumException;
import com.cit.backend.exceptions.FileDoesNotExistsException;
import com.cit.backend.exceptions.InvalidResidentException;
import com.cit.backend.exceptions.MissingVariableException;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/ombudsmen")
public class OmbudsmenController {
    @Autowired
    private OmbudsmanMapper ombudsmanMapper;

    @Autowired
    private OmbudsmanService ombudsmanService;

    @Autowired
    private ResidentService residentService;

    @Autowired
    private EmployeeService employeeService;

    private final UploadFilesService uploadFilesService = new UploadFilesService("/ombudsmen");

    @GetMapping
    public ResponseEntity<List<OmbudsmenResponse>> getAll() {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByProfile(profile);
        Resident resident = residentService.getFromProfile(profile);

        List<Ticket> tickets;
        if (employee != null) {
            Condominium condominium = employee.getCondominium();
            if (condominium == null)
                throw new EmployeeNotRegisteredInACondominiumException("Employee is not registered in a condominium");

            tickets = ombudsmanService.findAllByCondominium(condominium);
        } else if (resident != null) {
            Apartment apartment = resident.getApartment();
            if (apartment == null) throw new InvalidResidentException("Resident is not registered in an apartment");

            tickets = ombudsmanService.findAllByApartment(apartment);
        } else throw new RuntimeException("User is not an employee or a resident");

        List<OmbudsmenResponse> response = ombudsmanMapper.toOmbudsmenResponse(tickets);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OmbudsmenResponse> findById(@PathVariable Long id) {
        Ticket ticket = ombudsmanService.findById(id);
        OmbudsmenResponse response = ombudsmanMapper.toOmbudsmenResponse(ticket);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RolesAllowed("RESIDENT")
    public ResponseEntity<OmbudsmenResponse> create(@RequestBody OmbudsmenRequest request) {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Resident resident = residentService.getFromProfile(profile);
        Apartment apartment = resident.getApartment();

        if (apartment == null) throw new InvalidResidentException("Resident is not registered in an apartment");

        Ticket ticket = ombudsmanMapper.toTicket(request);
        ticket.setApartment(apartment);
        ticket.setStatus(StatusTicket.OPEN);
        Ticket saved = ombudsmanService.create(ticket);
        OmbudsmenResponse response = ombudsmanMapper.toOmbudsmenResponse(saved);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload/{id}")
    @RolesAllowed("RESIDENT")
    public ResponseEntity<Void> uploadFiles(@PathVariable("id") Long id, @RequestParam("files") List<MultipartFile> files) {
        Ticket ticket = ombudsmanService.findById(id);
        IntStream.range(0, files.size()).forEach(index -> {
            MultipartFile file = files.get(index);
            String[] parts = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
            String filename = ticket.getId() + "-" + index + "." + parts[parts.length - 1];
            uploadFilesService.store(file, filename);
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/files/info/{id}")
    public ResponseEntity<List<String>> getFilesInfo(@PathVariable("id") Long id) {
        Ticket ticket = ombudsmanService.findById(id);
        List<String> files = uploadFilesService
                .loadAll("/" + ticket.getId() + "-*")
                .stream().map(path -> path.getFileName().toString())
                .toList();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/file/download/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename) throws MalformedURLException {
        Path file = uploadFilesService.load(filename);
        if(!file.toFile().exists()) throw new FileDoesNotExistsException("File does not exist");
        Resource resource = new UrlResource(file.toUri());
        return ResponseEntity.ok(resource);
    }

    @PutMapping
    public ResponseEntity<OmbudsmenResponse> update(@RequestBody OmbudsmenRequest request) {
        if (request.getId() == null) {
            throw new MissingVariableException("Id is required", List.of("id"));
        }

        Ticket ticket;
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!profile.hasPermission(ProfilePermissions.ROLE_ADMIN) && profile.hasPermission(ProfilePermissions.ROLE_RESIDENT)) {
            ticket = ombudsmanService.findById(request.getId());
            ticket.setStatus(request.getStatus());
        } else {
            ticket = ombudsmanMapper.toTicket(request);
            ombudsmanMapper.fillNullFields(ticket, ombudsmanService.findById(request.getId()));
        }

        Ticket updated = ombudsmanService.update(ticket);
        OmbudsmenResponse response = ombudsmanMapper.toOmbudsmenResponse(updated);
        return ResponseEntity.ok(response);
    }
}
