package com.example.DaLtdd.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    private Long id;

    @OneToMany
    private List<BookedSeat> bookedSeat;

    @OneToMany
    private List<BookedFood> bookedFoods;

    private int price;

    @ManyToOne
    private User user;

    @ManyToOne
    private Showtime showtime;

}
