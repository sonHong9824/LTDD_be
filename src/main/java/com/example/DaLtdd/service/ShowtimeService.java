package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.ShowtimeRequest;
import com.example.DaLtdd.entity.Cinema;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.entity.Showtime;
import com.example.DaLtdd.repository.CinemaRepository;
import com.example.DaLtdd.repository.MovieRepository;
import com.example.DaLtdd.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private MovieRepository movieRepository;

    public Showtime createShowtime(ShowtimeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        Cinema cinema = cinemaRepository.findById(request.getCinemaId())
                .orElseThrow(() -> new RuntimeException("Cinema not found"));

        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setCinema(cinema);
        showtime.setShowtime(request.getShowtime());
        showtime.setRoom(request.getRoom());

        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getShowtimesByMovieDateAndCinema(String movieId, String cinemaId, LocalDate date) {
        // Chuyển LocalDate thành LocalDateTime (đầu ngày)
        LocalDateTime startOfDay = date.atStartOfDay();
        return showtimeRepository.findByMovieAndDateAndCinema(movieId, cinemaId, startOfDay);
    }
    public List<Showtime> getShowtimesByMovieDate(String movieId, LocalDate date) {
        // Chuyển LocalDate thành LocalDateTime (đầu ngày)
        LocalDateTime startOfDay = date.atStartOfDay();
        return showtimeRepository.findByMovieAndDate(movieId, startOfDay);
    }

}
