package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

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
}
