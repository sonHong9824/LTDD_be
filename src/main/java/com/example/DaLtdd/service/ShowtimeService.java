package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.ShowtimeRequest;
import com.example.DaLtdd.entity.Cinema;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.entity.Showtime;
import com.example.DaLtdd.model.MovieShowtimeResponse;
import com.example.DaLtdd.repository.CinemaRepository;
import com.example.DaLtdd.repository.MovieRepository;
import com.example.DaLtdd.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public List<MovieShowtimeResponse> getShowtimesGroupByLanguageAndFormat(String cinemaId, LocalDateTime date) {
        List<Object[]> result = showtimeRepository.getShowtimesGroupByLanguageAndFormat(cinemaId, date);

        Map<String, MovieShowtimeResponse> movieMap = new LinkedHashMap<>();

        for (Object[] row : result) {
            String movieId = (String) row[0];
            String languageType = (String) row[1];
            String formatType = (String) row[2];
            LocalDateTime showtime = (LocalDateTime) row[3];

            String languageFormat = languageType + " " + formatType;

            MovieShowtimeResponse movieResponse = movieMap.computeIfAbsent(movieId, k -> {
                MovieShowtimeResponse m = new MovieShowtimeResponse();
                m.setMovieId(movieId);
                m.setShowtimes(new ArrayList<>());
                return m;
            });

            // Tìm nhóm languageFormat đã có chưa
            MovieShowtimeResponse.ShowtimeGroup group = movieResponse.getShowtimes()
                    .stream()
                    .filter(g -> g.getLanguageFormat().equals(languageFormat))
                    .findFirst()
                    .orElseGet(() -> {
                        MovieShowtimeResponse.ShowtimeGroup g = new MovieShowtimeResponse.ShowtimeGroup();
                        g.setLanguageFormat(languageFormat);
                        g.setTimes(new ArrayList<>());
                        movieResponse.getShowtimes().add(g);
                        return g;
                    });

            group.getTimes().add(showtime.toLocalTime().toString());
        }
        return new ArrayList<>(movieMap.values());
    }
}
