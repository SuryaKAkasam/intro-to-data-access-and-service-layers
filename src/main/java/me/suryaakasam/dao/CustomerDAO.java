package me.suryaakasam.dao;

import me.suryaakasam.entities.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("customerDAO")
public class CustomerDAO extends AbstractDAO<Customer, Integer> { }