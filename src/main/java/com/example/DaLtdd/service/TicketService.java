package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.TicketRequest;
import com.example.DaLtdd.entity.*;
import com.example.DaLtdd.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private BookedFoodRepository bookedFoodRepository;

    public List<Ticket> getTicketsById(String userId) {
        return ticketRepository.findAllByUserId(userId);
    }

    public Ticket createTicket(TicketRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        List<String> requestedSeats = request.getSeats();
        List<BookedSeat> bookedSeats = requestedSeats.stream().map(seatName -> {
            BookedSeat seat = new BookedSeat();
            seat.setSeat(seatName);
            seat.setShowtime(showtime);
            return seat;
        }).toList();
        seatRepository.saveAll(bookedSeats);

        List<BookedFood> bookedFoods = new ArrayList<>();
        if(request.getBookedFoods() != null) {
            bookedFoods = request.getBookedFoods().stream().map(foodRequest -> {
                Food food = foodRepository.findById(foodRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Food not found: " + foodRequest.getId()));

                BookedFood bookedFood = new BookedFood();
                bookedFood.setFood(food);
                bookedFood.setQuantity(foodRequest.getQuantity());
                return bookedFoodRepository.save(bookedFood);
            }).toList();
        }

        Ticket ticket = new Ticket();
        ticket.setId(generateId());
        ticket.setUser(user);
        ticket.setShowtime(showtime);
        ticket.setBookedSeat(bookedSeats);
        ticket.setBookedFoods(bookedFoods);
        ticket.setPrice(request.getPrice());

        return ticketRepository.save(ticket);
    }
    private Long generateId() {
        Random random = new Random();
        Long id;
        do {
            id = 10_000_000L + random.nextInt(90_000_000); // từ 10000000 đến 99999999
        } while (ticketRepository.existsById(id));
        return id;
    }

}
