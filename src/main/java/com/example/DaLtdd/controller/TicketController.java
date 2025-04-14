package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.MovieRequest;
import com.example.DaLtdd.dto.TicketRequest;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.entity.Ticket;
import com.example.DaLtdd.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.createTicket(request));
    }

    @GetMapping("/userId")
    public ResponseEntity<List<Ticket>> getTicketsById(@RequestParam String userId) {
        List<Ticket> tickets = ticketService.getTicketsById(userId);
        return ResponseEntity.ok(tickets);
    }
}
