package me.suryaakasam.dao;

import me.suryaakasam.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreDAO extends JpaRepository<Theatre, Integer> {
    Theatre findByTheatreName(String name);
    List<Theatre> findByTheatreNameContaining(String name);
    List<Theatre> findByTicketPriceLessThan(float price);
}
