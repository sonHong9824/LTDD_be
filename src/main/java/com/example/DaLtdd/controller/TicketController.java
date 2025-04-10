package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.MovieRequest;
import com.example.DaLtdd.dto.TicketRequest;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.entity.Ticket;
import com.example.DaLtdd.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.createTicket(request));
    }
}
