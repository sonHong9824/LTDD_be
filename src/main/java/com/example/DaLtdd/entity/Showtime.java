package com.example.DaLtdd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Cinema cinema;

    private String room;

    private LocalDateTime showtime;

    public String getFormattedTimeRange() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String start = showtime.format(formatter);
        String end = showtime.plusMinutes(movie.getDuration()).format(formatter);
        return start + " ~ " + end;
    }
}
