package com.example.DaLtdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieSummaryFeature {
    private MovieSummary movieSummary;
    private int score;
}
