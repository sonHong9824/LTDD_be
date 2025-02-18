package com.example.DaLtdd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Showtime {
    @Id
    private String id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Cinema cinema;

    private LocalDateTime showtime;
}
