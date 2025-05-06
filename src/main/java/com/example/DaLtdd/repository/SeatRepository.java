package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.BookedSeat;
import com.example.DaLtdd.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<BookedSeat, String> {

    List<BookedSeat> findByShowtime_Id(String showtimeId);

    List<BookedSeat> findBookedSeatsByShowtimeIdAndSeatIn(String showtime_id, List<String> seat);
}
