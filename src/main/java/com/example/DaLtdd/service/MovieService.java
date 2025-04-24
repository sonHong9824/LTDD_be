package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.MovieRequest;
import com.example.DaLtdd.dto.MovieSummary;
import com.example.DaLtdd.entity.FeatureMovie;
import com.example.DaLtdd.entity.Genre;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.enums.MovieStatus;
import com.example.DaLtdd.repository.FeatureMovieRepository;
import com.example.DaLtdd.repository.GenreRepository;
import com.example.DaLtdd.repository.MovieRepository;
import com.example.DaLtdd.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.lang.management.MonitorInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FeatureMovieRepository featureMovieRepository;


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
        movie.setTrailerUrl(request.getTrailerUrl());
        movie.setScope(request.getScope());
        movie.setLanguage(request.getLanguage());
        movie.setBackdropUrl(request.getBackdropUrl());

        List<Genre> genres = genreRepository.findByNameIn(request.getGenreName());
        movie.setGenres(genres);

        movieRepository.save(movie);

        FeatureMovie featureMovie = new FeatureMovie();
        featureMovie.setId(movie.getId());
        featureMovie.setScore(0);
        featureMovieRepository.save(featureMovie);

        return movie;
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

    public List<MovieSummary> getNowShowingMoviesWithSummary() {
        List<Movie> movies = movieRepository.findNowShowingMovies();
        List<Object[]> ratingData = movieRepository.getMovieRatingsSummary();

        // Chuyển dữ liệu rating thành Map để tra cứu nhanh
        Map<String, MovieSummary> summaryMap = ratingData.stream()
                .collect(Collectors.toMap(
                        data -> (String) data[0], // movieId
                        data -> new MovieSummary(null, (double) data[1], (long) data[2])
                ));

        // Ghép dữ liệu movie với summary
        return movies.stream()
                .map(movie -> {
                    MovieSummary summary = summaryMap.get(movie.getId());
                    return new MovieSummary(movie,
                            summary != null ? summary.getAverageRating() : 0.0,
                            summary != null ? summary.getTotalReviews() : 0);
                })
                .collect(Collectors.toList());

    }
    public List<MovieSummary> getComingSoonMoviesWithSummary() {
        List<Movie> movies = movieRepository.findCoimngSoonMovies();
        List<Object[]> ratingData = movieRepository.getMovieRatingsSummary();

        // Chuyển dữ liệu rating thành Map để tra cứu nhanh
        Map<String, MovieSummary> summaryMap = ratingData.stream()
                .collect(Collectors.toMap(
                        data -> (String) data[0], // movieId
                        data -> new MovieSummary(null, (double) data[1], (long) data[2])
                ));

        // Ghép dữ liệu movie với summary
        return movies.stream()
                .map(movie -> {
                    MovieSummary summary = summaryMap.get(movie.getId());
                    return new MovieSummary(movie,
                            summary != null ? summary.getAverageRating() : 0.0,
                            summary != null ? summary.getTotalReviews() : 0);
                })
                .collect(Collectors.toList());

    }

    public List<MovieSummary> getFeatureMovie(){
        Pageable topFive = (Pageable) PageRequest.of(0, 5);
        List<Movie> movies = movieRepository.findTopFeatureMovies(topFive);

        List<Object[]> ratingData = movieRepository.getMovieRatingsSummary();

        // Chuyển dữ liệu rating thành Map để tra cứu nhanh
        Map<String, MovieSummary> summaryMap = ratingData.stream()
                .collect(Collectors.toMap(
                        data -> (String) data[0], // movieId
                        data -> new MovieSummary(null, (double) data[1], (long) data[2])
                ));

        // Ghép dữ liệu movie với summary
        return movies.stream()
                .map(movie -> {
                    MovieSummary summary = summaryMap.get(movie.getId());
                    return new MovieSummary(movie,
                            summary != null ? summary.getAverageRating() : 0.0,
                            summary != null ? summary.getTotalReviews() : 0);
                })
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void updateMovieStatusIfReleased() {
        List<Movie> movies = movieRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Movie movie : movies) {
            if (movie.getReleaseDate() != null &&
                    movie.getReleaseDate().isBefore(today)) {
                movie.setStatus(MovieStatus.NOW_SHOWING);
                movieRepository.save(movie);
            }
        }

        System.out.println("Movie statuses auto-updated at 1 AM");
    }
}
