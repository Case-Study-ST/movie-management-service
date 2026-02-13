package com.gsa.movie.service;

import com.gsa.movie.dto.CreateMovieRequest;
import com.gsa.movie.dto.MovieResponse;
import com.gsa.movie.entity.Movie;
import com.gsa.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repo;

    public MovieResponse create(CreateMovieRequest req) {
        Movie movie = repo.save(map(req));
        return map(movie);
    }

    public List<MovieResponse> bulkInsert(List<CreateMovieRequest> reqs) {
        List<Movie> movies = reqs.stream()
                .map(this::map)
                .toList();

        return repo.saveAll(movies)
                .stream()
                .map(this::map)
                .toList();
    }

    public List<MovieResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public MovieResponse get(Long id) {
        return map(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found")));
    }

    public MovieResponse update(Long id, CreateMovieRequest req) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setTitle(req.getTitle());
        movie.setLanguage(req.getLanguage());
        movie.setGenre(req.getGenre());
        movie.setDurationMinutes(req.getDurationMinutes());
        movie.setCertificate(req.getCertificate());

        return map(repo.save(movie));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private Movie map(CreateMovieRequest r) {
        return Movie.builder()
                .title(r.getTitle())
                .language(r.getLanguage())
                .genre(r.getGenre())
                .durationMinutes(r.getDurationMinutes())
                .certificate(r.getCertificate())
                .build();
    }

    private MovieResponse map(Movie m) {
        return MovieResponse.builder()
                .id(m.getId())
                .title(m.getTitle())
                .language(m.getLanguage())
                .genre(m.getGenre())
                .durationMinutes(m.getDurationMinutes())
                .certificate(m.getCertificate())
                .build();
    }
}
