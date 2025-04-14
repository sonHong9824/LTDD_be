package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Ticket;
import com.example.DaLtdd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByUserId(String userId);
}
