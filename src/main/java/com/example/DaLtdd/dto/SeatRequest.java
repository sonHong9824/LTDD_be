package com.example.DaLtdd.dto;

import com.example.DaLtdd.entity.Showtime;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeatRequest {
    private String showtime_id;
    private String seat;
    private BigDecimal price;
}
