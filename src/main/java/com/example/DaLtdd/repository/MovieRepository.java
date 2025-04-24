package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByGenres_Name(String genre);

    @Query("SELECT r.movieId, AVG(r.rating), COUNT(r) FROM Review r GROUP BY r.movieId")
    List<Object[]> getMovieRatingsSummary();

    @Query("SELECT m FROM Movie m WHERE m.status = 'NOW_SHOWING'")
    List<Movie> findNowShowingMovies();

    @Query("SELECT m FROM Movie m WHERE m.status = 'COMING_SOON'")
    List<Movie> findCoimngSoonMovies();

    @Query("SELECT m FROM Movie m JOIN FeatureMovie f ON m.id = f.id ORDER BY f.score DESC")
    List<Movie> findTopFeatureMovies(Pageable pageable);


}
