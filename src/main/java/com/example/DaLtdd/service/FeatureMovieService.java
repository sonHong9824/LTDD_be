package com.example.DaLtdd.service;

import com.example.DaLtdd.entity.FeatureMovie;
import com.example.DaLtdd.repository.FeatureMovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureMovieService {
    @Autowired
    private FeatureMovieRepository featureMovieRepository;

    public void createFeatureMovie(FeatureMovie featureMovie) {
        featureMovieRepository.save(featureMovie);
    }

    public FeatureMovie increase(String id) {
        FeatureMovie featureMovie = featureMovieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phim với id: " + id));
        featureMovie.increase_score();
        featureMovieRepository.save(featureMovie);
        return featureMovie;
    }

    public FeatureMovie increase_for_ticket(String id) {
        FeatureMovie featureMovie = featureMovieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phim với id: " + id));
        featureMovie.increase_for_ticket();
        featureMovieRepository.save(featureMovie);
        return featureMovie;
    }
}
