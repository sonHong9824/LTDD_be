package com.example.DaLtdd.controller;

import com.example.DaLtdd.entity.FeatureMovie;
import com.example.DaLtdd.entity.Food;
import com.example.DaLtdd.service.FeatureMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
public class FeatureMovieController {
    @Autowired
    private FeatureMovieService featureMovieService;

    @PostMapping("/increase")
    public ResponseEntity<FeatureMovie> increase(String id) {
        return ResponseEntity.ok(featureMovieService.increase(id));
    }

    @PostMapping("/increase_for_ticket")
    public ResponseEntity<FeatureMovie> increase_for_ticket(String id) {
        return ResponseEntity.ok(featureMovieService.increase_for_ticket(id));
    }
}
