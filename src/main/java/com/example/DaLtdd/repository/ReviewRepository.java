package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query("SELECT r.movie.id, r.movie.title, AVG(r.rating) as avgRating " +
            "FROM Review r GROUP BY r.movie.id, r.movie.title " +
            "ORDER BY avgRating DESC")
    List<Object[]> findTopRatedMovies();
}
