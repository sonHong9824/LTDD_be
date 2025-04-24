package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.*;
import com.example.DaLtdd.entity.*;
import com.example.DaLtdd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping("/create-movie")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @PostMapping("/create-food")
    public ResponseEntity<Food> createFood(@RequestBody FoodRequest request) {
        return ResponseEntity.ok(foodService.createFood(request));
    }

    @PostMapping("/create-cinema")
    public ResponseEntity<Cinema> createCinema(@RequestBody CinemaRequest request) {
        return ResponseEntity.ok(cinemaService.createCinema(request));
    }

    @PostMapping("/create-genre")
    public ResponseEntity<Genre> createGenre(@RequestBody GenreRequest request) {
        return ResponseEntity.ok(genreService.createGenre(request));
    }

    @PostMapping("/create-showtime")
    public ResponseEntity<Showtime> createShowtime(@RequestBody ShowtimeRequest request) {
        return ResponseEntity.ok(showtimeService.createShowtime(request));
    }

}
