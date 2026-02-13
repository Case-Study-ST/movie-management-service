package com.gsa.movie.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MovieResponse {
    private Long id;
    private String title;
    private String language;
    private String genre;
    private Integer durationMinutes;
    private String certificate;
}
