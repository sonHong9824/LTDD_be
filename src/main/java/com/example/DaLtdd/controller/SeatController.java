package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.CinemaRequest;
import com.example.DaLtdd.dto.SeatRequest;
import com.example.DaLtdd.entity.BookedSeat;
import com.example.DaLtdd.entity.Cinema;
import com.example.DaLtdd.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/create")
    public ResponseEntity<BookedSeat> createSeat(@RequestBody SeatRequest seatRequest) {
        return ResponseEntity.ok(seatService.createSeat(seatRequest));
    }

    @GetMapping("/getByShowtime")
    public ResponseEntity<List<BookedSeat>> getByShowtime(@RequestParam String showtimeId) {
        return ResponseEntity.ok(seatService.getSeatsbyShowtime(showtimeId));
    }

}
