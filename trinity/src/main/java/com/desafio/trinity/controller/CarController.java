package com.desafio.trinity.controller;

import com.desafio.trinity.controller.dto.CarDTO;
import com.desafio.trinity.controller.dto.UserDTO;
import com.desafio.trinity.entity.Car;
import com.desafio.trinity.entity.User;
import com.desafio.trinity.service.CarService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin("*")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ApiOperation(value = "Listar todos os carros de acordo com o usuário logado",
            notes = ".listAll()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carros encontrados com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível encontrar os carros"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", required = true, paramType = "header",
                    value = "Id do usuário", dataType = "Long"),
    })
    @GetMapping
    public ResponseEntity listAll(@RequestHeader Long userId){
        try {
            List<Car> cars = carService.listAll(userId);
            return ResponseEntity.ok(cars);
        }catch (NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Busca um carro pelo id",
            notes = ".getUser()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carro encontrado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível encontrar o carro"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", required = true, paramType = "path",
                    value = "Id do usuário", dataType = "Long"),
    })
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id){
        try {
            Car car = carService.getCar(id);
            return ResponseEntity.ok(car);
        }catch (NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Adicionar carro ao usuário",
            notes = ".createUser()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carro criado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível criar o carro"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", required = true, paramType = "header",
                    value = "Id do usuário", dataType = "Long"),
            @ApiImplicitParam(name = "carDTO", required = true, paramType = "body",
                    value = "Informações do carro", dataType = "CarDTO"),
    })
    @PostMapping
    public ResponseEntity createUser(@RequestHeader Long userId, @RequestBody CarDTO carDTO){
        try {
            Car car = carService.create(userId, carDTO);
            return ResponseEntity.ok().body(car);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
