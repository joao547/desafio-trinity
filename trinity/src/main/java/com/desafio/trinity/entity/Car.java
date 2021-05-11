package com.desafio.trinity.entity;

import com.desafio.trinity.controller.dto.CarDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int year;

    private String licensePlate;

    private String model;

    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public Car(User user, CarDTO carDTO){
        this.color = carDTO.getColor();
        this.licensePlate = carDTO.getLicensePlate();
        this.model = carDTO.getModel();
        this.user = user;
        this.year = carDTO.getYear();
    }
}
