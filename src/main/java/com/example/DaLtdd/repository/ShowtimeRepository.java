package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.entity.Showtime;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, String> {
    @Query("SELECT s FROM Showtime s WHERE s.movie.id = :movieId AND s.cinema.id = :cinemaId AND DATE(s.showtime) = DATE(:date)")
    List<Showtime> findByMovieAndDateAndCinema(
            @Param("movieId") String movieId,
            @Param("cinemaId") String cinemaId,
            @Param("date") LocalDateTime date);

    @Query("SELECT s FROM Showtime s WHERE s.movie.id = :movieId AND DATE(s.showtime) = DATE(:date)")
    List<Showtime> findByMovieAndDate(
            @Param("movieId") String movieId,
            @Param("date") LocalDateTime date);

    @NotNull Optional<Showtime> findById (@NotNull String id);

    @Query("SELECT s FROM Showtime s WHERE s.cinema.id = :cinemaId AND DATE(s.showtime) = DATE(:date) ORDER BY s.movie.id, s.languageType, s.formatType, s.showtime")
    List<Showtime> getShowtimesGroupByLanguageAndFormat(
            @Param("cinemaId") String cinemaId,
            @Param("date") LocalDateTime date
    );
}
