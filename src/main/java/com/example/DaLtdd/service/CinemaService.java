package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.CinemaRequest;
import com.example.DaLtdd.entity.Cinema;
import com.example.DaLtdd.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public Cinema createCinema(CinemaRequest request) {
        Cinema cinema = new Cinema();
        cinema.setName(request.getName());
        cinema.setLocation(request.getLocation());
        return cinemaRepository.save(cinema);
    }
}
