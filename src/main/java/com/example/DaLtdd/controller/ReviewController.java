package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.ReviewRequest;
import com.example.DaLtdd.entity.Review;
import com.example.DaLtdd.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(ReviewRequest reviewRequest) {
        return ResponseEntity.ok(reviewService.createReview(reviewRequest));
    }
}
