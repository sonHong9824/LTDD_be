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

    @OneToMany(cascade = CascadeType.ALL)
    private List<BookedSeat> bookedSeat = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<BookedFood> bookedFoods = new ArrayList<>();

    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Showtime showtime;


}
