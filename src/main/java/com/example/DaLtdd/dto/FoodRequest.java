package com.example.DaLtdd.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private String pictureUrl;
}
