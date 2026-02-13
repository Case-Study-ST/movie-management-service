package com.gsa.movie.controller;

import com.gsa.movie.dto.CreateMovieRequest;
import com.gsa.movie.dto.MovieResponse;
import com.gsa.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
@Tag(
        name = "Movie Management",
        description = "APIs for managing movie catalog including CRUD and bulk operations"
)
public class MovieController {

    private final MovieService service;

    @Operation(
            summary = "Create a new movie",
            description = "Adds a new movie to the catalog"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<MovieResponse> create(
            @Valid
            @RequestBody
            @Schema(description = "Movie creation request")
            CreateMovieRequest req) {

        return ResponseEntity.ok(service.create(req));
    }

    @Operation(
            summary = "Bulk insert movies",
            description = "Creates multiple movies in a single request"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies inserted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping("/bulk")
    public ResponseEntity<List<MovieResponse>> bulkInsert(
            @Valid
            @RequestBody
            @Schema(description = "List of movies to create")
            List<CreateMovieRequest> req) {

        return ResponseEntity.ok(service.bulkInsert(req));
    }

    @Operation(
            summary = "Get all movies",
            description = "Fetches complete movie catalog"
    )
    @ApiResponse(responseCode = "200", description = "Movies fetched successfully")
    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(
            summary = "Get movie by ID",
            description = "Fetches a movie by its unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found"),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Operation(
            summary = "Update movie",
            description = "Updates movie details by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(
            @PathVariable Long id,
            @Valid
            @RequestBody
            @Schema(description = "Updated movie details")
            CreateMovieRequest req) {

        return ResponseEntity.ok(service.update(id, req));
    }

    @Operation(
            summary = "Delete movie",
            description = "Removes a movie from catalog"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
