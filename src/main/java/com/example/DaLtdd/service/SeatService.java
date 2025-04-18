package com.example.DaLtdd.service;

import com.example.DaLtdd.dto.SeatRequest;
import com.example.DaLtdd.entity.BookedSeat;
import com.example.DaLtdd.entity.Showtime;
import com.example.DaLtdd.repository.SeatRepository;
import com.example.DaLtdd.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<String> getSeatNumbersByShowtime(String showtimeId) {
        return seatRepository.findByShowtime_Id(showtimeId)
                .stream()
                .map(BookedSeat::getSeat)
                .collect(Collectors.toList());
    }

    public BookedSeat createSeat(SeatRequest seatRequest){
        Optional<Showtime> showtimeOpt = showtimeRepository.findById(seatRequest.getShowtime_id());

        if (showtimeOpt.isPresent()) {
            Showtime showtime = showtimeOpt.get();
            BookedSeat bookedSeat = new BookedSeat();
            bookedSeat.setShowtime(showtime);
            bookedSeat.setSeat(seatRequest.getSeat());

            return seatRepository.save(bookedSeat);
        } else {
            throw new RuntimeException("Showtime không tồn tại");
        }
    }
}
