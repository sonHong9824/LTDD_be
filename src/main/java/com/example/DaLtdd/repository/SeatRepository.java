package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<BookedSeat, String> {

    List<BookedSeat> findByShowtime_Id(String showtimeId);
}
