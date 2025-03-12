package com.example.DaLtdd.repository;

import com.example.DaLtdd.dto.MovieRating;
import com.example.DaLtdd.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query("SELECT r.movie_id, AVG(r.rating) as avgRating " +
            "FROM Review r GROUP BY r.movie_id " +
            "ORDER BY avgRating DESC")
    List<Object[]> findTopRatedMovies();

    @Query("SELECT new com.example.DaLtdd.dto.MovieRating(AVG(r.rating), COUNT(r)) FROM Review r WHERE r.movie_id = :movieId")
    MovieRating getMovieRatingSummary(@Param("movieId") String movieId);
}
