package com.example.DaLtdd.controller;

import java.util.Map;
import java.util.List;

import com.example.DaLtdd.dto.MovieSummary;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/now-showing")
    public ResponseEntity<List<MovieSummary>> getNowShowingMovies() {
        return ResponseEntity.ok(movieService.getNowShowingMoviesWithSummary());
    }
    @GetMapping("/coming-soon")
    public ResponseEntity<List<MovieSummary>> getComingSoonMovies() {
        return ResponseEntity.ok(movieService.getComingSoonMoviesWithSummary());
    }

    @GetMapping("/genre/{genreName}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genreName) {
        return ResponseEntity.ok(movieService.getMoviesByGenre(genreName));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Map<String, Object>>> getTopRatedMovies() {
        return ResponseEntity.ok(movieService.getTopRatedMovies());
    }

    @GetMapping("/feature")
    public ResponseEntity<List<MovieSummary>> getFeatureMovies() {
        return ResponseEntity.ok(movieService.getFeatureMovie());
    }
}
