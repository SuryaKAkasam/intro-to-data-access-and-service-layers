package me.suryaakasam.dao;

import me.suryaakasam.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDAO extends JpaRepository<Status, Integer> {
    Status findByStatusName(String name);
}
