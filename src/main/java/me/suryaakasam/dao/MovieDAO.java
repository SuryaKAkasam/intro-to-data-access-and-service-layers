package me.suryaakasam.dao;

import me.suryaakasam.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDAO extends JpaRepository<Movie, Integer> {
}
