package com.example.DaLtdd.entity;

import com.example.DaLtdd.enums.MovieStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String description;
    private int duration;
    private LocalDate releaseDate;
    private String posterUrl;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    @ManyToMany
    private List<Genre> genres;
}
