package me.suryaakasam.dao;

import me.suryaakasam.entities.MovieTheatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTheatreDAO extends JpaRepository<MovieTheatre, Integer> {
}
