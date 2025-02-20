package com.example.DaLtdd.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.get_all_moive());
    }

    @GetMapping("/genre/{genreName}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genreName) {
        return ResponseEntity.ok(movieService.getMoviesByGenre(genreName));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Map<String, Object>>> getTopRatedMovies() {
        return ResponseEntity.ok(movieService.getTopRatedMovies());
    }
}
