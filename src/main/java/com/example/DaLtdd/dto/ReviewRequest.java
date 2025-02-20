package com.example.DaLtdd.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private String movieId;
    private String userId;
    private int rating;
    private String comment;
}
