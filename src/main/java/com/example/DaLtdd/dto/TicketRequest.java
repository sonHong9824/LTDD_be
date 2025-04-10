package com.example.DaLtdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String userId;
    private String showtimeId;
    private ArrayList<String> seats;
    private ArrayList<BookedFoodRequest> bookedFoods;
    private int price;

    @Data
    public static class BookedFoodRequest {
        private String id;
        private int quantity;
    }
}

