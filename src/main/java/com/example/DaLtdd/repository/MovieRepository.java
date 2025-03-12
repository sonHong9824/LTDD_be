package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByGenres_Name(String genre);

    @Query("SELECT r.movie_id, AVG(r.rating), COUNT(r) FROM Review r GROUP BY r.movie_id")
    List<Object[]> getMovieRatingsSummary();

    @Query("SELECT m FROM Movie m WHERE m.status = 'NOW_SHOWING'")
    List<Movie> findNowShowingMovies();

    @Query("SELECT m FROM Movie m WHERE m.status = 'COMING_SOON'")
    List<Movie> findCoimngSoonMovies();
}
