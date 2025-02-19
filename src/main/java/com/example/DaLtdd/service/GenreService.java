package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.GenreRequest;
import com.example.DaLtdd.entity.Genre;
import com.example.DaLtdd.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Genre createGenre(GenreRequest request) {
        Genre genre = new Genre();
        genre.setName(request.getName());
        return genreRepository.save(genre);
    }
}
