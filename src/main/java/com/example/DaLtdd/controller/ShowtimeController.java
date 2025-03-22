package com.example.DaLtdd.controller;

import com.example.DaLtdd.entity.Showtime;
import com.example.DaLtdd.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/showtime")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/movie-date-cinema")
    public ResponseEntity<?> getShowtimesByMovieDateAndCinema(
            @RequestParam String movieId,
            @RequestParam String cinemaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Showtime> showtimes = showtimeService.getShowtimesByMovieDateAndCinema(movieId, cinemaId, date);
        return ResponseEntity.ok(showtimes);
    }
    @GetMapping("/movie-date")
    public ResponseEntity<?> getShowtimesByMovieDate(
            @RequestParam String movieId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Showtime> showtimes = showtimeService.getShowtimesByMovieDate(movieId, date);
        return ResponseEntity.ok(showtimes);
    }

}
