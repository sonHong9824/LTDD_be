package com.example.DaLtdd.controller;

import com.example.DaLtdd.entity.Cinema;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Cinema>> getAllMovies() {
        return ResponseEntity.ok(cinemaService.getAllCinema());
    }
}
