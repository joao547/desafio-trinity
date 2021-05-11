package com.desafio.trinity.repository;

import com.desafio.trinity.entity.Car;
import com.desafio.trinity.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByUser(User user);
}
