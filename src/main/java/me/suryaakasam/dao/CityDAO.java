package me.suryaakasam.dao;

import me.suryaakasam.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDAO extends JpaRepository<City, Integer> {
    City findByCityName(String name);
}
