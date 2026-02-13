package com.gsa.movie.dto;

import lombok.Data;

@Data
public class CreateMovieRequest {
    private String title;
    private String language;
    private String genre;
    private Integer durationMinutes;
    private String certificate;
}