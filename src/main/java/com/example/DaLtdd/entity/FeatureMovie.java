package com.example.DaLtdd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FeatureMovie {
    @Id
    private String id;
    private int score;

    public void increase_score(){
        this.score++;
    }
    public void increase_for_ticket(){
        this.score += 2;
    }
}
