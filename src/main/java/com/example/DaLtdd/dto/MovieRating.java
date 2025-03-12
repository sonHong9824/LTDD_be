package com.example.DaLtdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieRating {
    private double averageRating;
    private long totalReviews;
}