package com.example.DaLtdd.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Data
public class ShowtimeRequest {
    private String movieId;
    private String cinemaId;
    private LocalDateTime showtime;

}
