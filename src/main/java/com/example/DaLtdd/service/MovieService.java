package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.MovieRequest;
import com.example.DaLtdd.entity.Genre;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.repository.GenreRepository;
import com.example.DaLtdd.repository.MovieRepository;
import com.example.DaLtdd.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Movie> get_all_moive(){
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByGenre(String genreName) {
        return movieRepository.findByGenres_Name(genreName);
    }

    public Movie createMovie(MovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDuration(request.getDuration());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setPosterUrl(request.getPosterUrl());
        movie.setStatus(request.getStatus());


        List<Genre> genres = genreRepository.findByNameIn(request.getGenreName());
        movie.setGenres(genres);

        return movieRepository.save(movie);
    }
    public List<Map<String, Object>> getTopRatedMovies() {
        List<Object[]> results = reviewRepository.findTopRatedMovies();
        List<Map<String, Object>> topMovies = new ArrayList<>();

        for (Object[] row : results) {
            Map<String, Object> movieData = new HashMap<>();
            movieData.put("movieId", row[0]);
            movieData.put("title", row[1]);
            movieData.put("averageRating", row[2]);
            topMovies.add(movieData);
        }
        return topMovies;
    }
}
