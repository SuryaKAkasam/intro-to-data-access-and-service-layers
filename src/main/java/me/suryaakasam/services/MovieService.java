package me.suryaakasam.services;

import me.suryaakasam.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie);
    List<Movie> allAllMovies(List<Movie> movies);
    Movie getMovie(int id);
    List<Movie> getAllMovies();
    Page<Movie> getPaginatedMovies(Pageable page);
    Movie updateMovie(int id, Movie movie);
    boolean deleteMovie(int id);
}
