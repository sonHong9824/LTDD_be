package com.example.DaLtdd.model;

import com.example.DaLtdd.entity.Movie;
import com.example.DaLtdd.entity.Showtime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class MovieShowtimeResponse {
    private Movie movie; // thông tin phim

    private List<ShowtimeType> showtimetype; // danh sách các nhóm showtime theo languageFormat

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShowtimeType {
        private String languageFormat;           // ví dụ: "Phụ đề 2D"
        private List<Showtime> showtimes;        // danh sách các suất chiếu đầy đủ
    }
}
