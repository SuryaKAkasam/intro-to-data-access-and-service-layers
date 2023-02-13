package me.suryaakasam.dao;

import me.suryaakasam.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{orderId:'?0'}")
    Order findByOrderId(String id);
}
