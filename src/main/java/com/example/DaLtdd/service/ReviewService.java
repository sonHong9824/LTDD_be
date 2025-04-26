package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.MovieRating;
import com.example.DaLtdd.dto.ReviewRequest;
import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.entity.Review;
import com.example.DaLtdd.entity.User;
import com.example.DaLtdd.repository.MovieRepository;
import com.example.DaLtdd.repository.ReviewRepository;
import com.example.DaLtdd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Review createReview(ReviewRequest request) {
        System.out.println("User ID: " + request.getUserId());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        System.out.println("User ID: " + request.getUserId());
        System.out.println("Movie ID: " + request.getMovieId());

        Review review = new Review();
        review.setUser(user);
        review.setMovieId(request.getMovieId());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public MovieRating getMovieRatingSummary(String movieId) {
        MovieRating summary = reviewRepository.getMovieRatingSummary(movieId);

        if (summary == null || summary.getTotalReviews() == 0) {
            return new MovieRating(0.0, 0);
        }
        return summary;
    }
    public List<Review> getReviews(String movieId) {
        return reviewRepository.findByMovieId(movieId);
    }
}
