package com.example.DaLtdd.dto;

import com.example.DaLtdd.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieSummary
{
    private Movie movie;
    private double averageRating;
    private long totalReviews;
}
