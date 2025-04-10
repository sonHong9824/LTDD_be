package com.example.DaLtdd.model;

import lombok.Data;

import java.util.List;

@Data
public class MovieShowtimeResponse {
    private String movieId;
    private List<ShowtimeGroup> showtimes;

    @Data
    public static class ShowtimeGroup {
        private String languageFormat;
        private List<String> times; 
    }
}
