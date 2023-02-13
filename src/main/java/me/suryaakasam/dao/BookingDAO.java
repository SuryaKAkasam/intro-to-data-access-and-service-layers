package me.suryaakasam.dao;

import me.suryaakasam.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDAO extends JpaRepository<Booking, Integer> {
}
