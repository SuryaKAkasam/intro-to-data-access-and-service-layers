package me.suryaakasam.dao;

import me.suryaakasam.entities.UserType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("userTypeDAO")
public class UserTypeDAO extends AbstractDAO<UserType, Integer> { }