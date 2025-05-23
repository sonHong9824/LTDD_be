package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.MovieRating;
import com.example.DaLtdd.dto.ReviewRequest;
import com.example.DaLtdd.entity.Review;
import com.example.DaLtdd.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;


    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.createReview(reviewRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Review>> getReviews(@RequestParam String movieId) {
        return ResponseEntity.ok(reviewService.getReviews(movieId));
    }

    @GetMapping("/movie/{movieId}/summary")
    public ResponseEntity<Object> getMovieRatingSummary(@PathVariable String movieId) {
        MovieRating summary = reviewService.getMovieRatingSummary(movieId);

        if (summary.getTotalReviews() == 0) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Chưa có đánh giá cho bộ phim này.");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(summary);
    }
}
