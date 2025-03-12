package com.example.DaLtdd.dto;

import com.example.DaLtdd.enums.MovieStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private int duration;
    private LocalDate releaseDate;
    private String posterUrl;
    private MovieStatus status;
    private String trailerUrl;
    private String scope;
    private String language;
    @Setter
    @Getter
    private List<String> genreName;

}
