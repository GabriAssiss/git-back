package com.cit.backend.api.controller;

import com.cit.backend.api.response.ReportTicketResponse;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @GetMapping("/ticket")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<List<ReportTicketResponse>> ReportTicket() {
        ReportTicketResponse response1 = new ReportTicketResponse("Reclamação", 1);
        ReportTicketResponse response2 = new ReportTicketResponse("Sugestao", 34);
        ReportTicketResponse response3 = new ReportTicketResponse("Manutenção", 12);
        ReportTicketResponse response4 = new ReportTicketResponse("Outros", 1);

        List<ReportTicketResponse> response = List.of(new ReportTicketResponse[]{
                response1,
                response2,
                response3,
                response4
        });
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/visitors")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<List<ReportTicketResponse>> ReportVisitors() {
        ReportTicketResponse response1 = new ReportTicketResponse("Moradores", 100);
        ReportTicketResponse response2 = new ReportTicketResponse("Visitantes", 34);

        List<ReportTicketResponse> response = List.of(new ReportTicketResponse[]{
                response1,
                response2,
        });
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/notice")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<List<ReportTicketResponse>> ReportNotice () {
        ReportTicketResponse response1 = new ReportTicketResponse("Alertas", 34);

        List<ReportTicketResponse> response = List.of(new ReportTicketResponse[]{
                response1,
        });
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
