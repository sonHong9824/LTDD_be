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
    private String languageType;
    private String formatType;
}
