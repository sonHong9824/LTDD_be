package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.BookedFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedFoodRepository extends JpaRepository<BookedFood, String> {

}
