package com.example.DaLtdd.controller;

import com.example.DaLtdd.dto.MovieSummary;
import com.example.DaLtdd.entity.Food;
import com.example.DaLtdd.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Food>> getAllFood() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }
}
