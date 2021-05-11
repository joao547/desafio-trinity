package com.desafio.trinity.service;

import com.desafio.trinity.controller.dto.CarDTO;
import com.desafio.trinity.entity.Car;

import java.util.List;

public interface CarService {

    Car create(Long userId, CarDTO carDTO);

    List<Car> listAll(Long userId);

    Car getCar(Long id);
}
