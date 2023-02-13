package me.suryaakasam.services;

import me.suryaakasam.dao.MovieDAO;
import me.suryaakasam.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    public MovieDAO movieDAO;

    @Override
    public Movie addMovie(Movie movie) {
        return movieDAO.save(movie);
    }

    @Override
    @Transactional
    public List<Movie> allAllMovies(List<Movie> movies) {
        List<Movie> savedMovies = new ArrayList<>();

        for (Movie newMovie : movies)
            savedMovies.add(addMovie(newMovie));

        return savedMovies;
    }

    @Override
    public Movie getMovie(int id) {
        return movieDAO.findById(id).orElseThrow();
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.findAll();
    }

    @Override
    public Page<Movie> getPaginatedMovies(Pageable page) {
        return movieDAO.findAll(page);
    }

    @Override
    public Movie updateMovie(int id, Movie movie) {
        Movie savedMovie = getMovie(id);

        savedMovie.setMovieName(movie.getMovieName());
        savedMovie.setMovieDesc(movie.getMovieDesc());
        savedMovie.setDuration(movie.getDuration());
        savedMovie.setTrailerURL(movie.getTrailerURL());
        savedMovie.setCoverPhotoURL(movie.getCoverPhotoURL());
        savedMovie.setReleaseDate(movie.getReleaseDate());

        return movieDAO.save(savedMovie);
    }

    @Override
    public boolean deleteMovie(int id) {
        movieDAO.delete(getMovie(id));
        return true;
    }
}
