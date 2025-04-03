package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.FoodRequest;
import com.example.DaLtdd.entity.Food;
import com.example.DaLtdd.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public Food createFood(FoodRequest request) {
        Food food = new Food();
        food.setName(request.getName());
        food.setDescription(request.getDescription());
        food.setPictureUrl(request.getPictureUrl());
        food.setPrice(request.getPrice());
        return foodRepository.save(food);

    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
