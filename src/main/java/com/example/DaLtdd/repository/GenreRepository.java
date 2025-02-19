package com.example.DaLtdd.repository;

import com.example.DaLtdd.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
    List<Genre> findByNameIn(List<String> names);
}
