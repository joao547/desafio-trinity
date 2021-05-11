package com.desafio.trinity.service.impl;

import com.desafio.trinity.controller.dto.CarDTO;
import com.desafio.trinity.entity.Car;
import com.desafio.trinity.entity.User;
import com.desafio.trinity.repository.CarRepository;
import com.desafio.trinity.repository.UserRepository;
import com.desafio.trinity.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository repository;

    private final UserRepository userRepository;

    public CarServiceImpl(CarRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Car create(Long userId, CarDTO carDTO) {
        User user = userRepository.findById(userId).get();
        if (user == null)
            throw new NullPointerException("Usuário não encontrado");
        return repository.save(new Car(user, carDTO));
    }

    @Override
    public List<Car> listAll(Long userId) {
        User user = userRepository.findById(userId).get();
        if (user == null)
            throw new NullPointerException("Usuário não encontrado");
        return repository.findByUser(user);
    }

    @Override
    public Car getCar(Long id) {
        Optional<Car> optional = repository.findById(id);
        if (optional.isEmpty())
            throw new NullPointerException("Carro não encontrado");
        return optional.get();
    }
}
