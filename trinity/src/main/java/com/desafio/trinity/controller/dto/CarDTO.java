package com.desafio.trinity.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO implements Serializable {

    private static final long serialVersionUID = -1545041047601989885L;

    private int year;

    private String licensePlate;

    private String model;

    private String color;
}
