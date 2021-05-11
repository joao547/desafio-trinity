package com.desafio.trinity.controller;

import com.desafio.trinity.controller.dto.UserDTO;
import com.desafio.trinity.entity.User;
import com.desafio.trinity.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Listar todos os usuários ",
            notes = ".listAll()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuários encontrados com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível encontrar os usuários"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @GetMapping
    public ResponseEntity listAll(){
        try {
            List<User> users = userService.listAll();
            return ResponseEntity.ok(users.stream().map(User::fromResponse).collect(Collectors.toList()));
        }catch (NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Busca um usuário pelo id",
            notes = ".getUser()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível encontrar o usuário"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", required = true, paramType = "path",
                    value = "Id do usuário", dataType = "Long"),
    })
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id){
        try {
            User user = userService.getUser(id);
            return ResponseEntity.ok(user.fromResponse());
        }catch (NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Cria usuário",
            notes = ".createUser()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário criado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível criar o usuário "),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userDTO", required = true, paramType = "body",
                    value = "Informações do usuário", dataType = "UserDTO"),
    })
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        try {
            User user = userService.create(userDTO);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Altera usuário",
            notes = ".updateUser()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível atualizar o usuário "),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", required = true, paramType = "path",
                    value = "Id do usuário", dataType = "Long"),
            @ApiImplicitParam(name = "userDTO", required = true, paramType = "body",
                    value = "Informações do usuário", dataType = "UserDTO"),
    })
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO){
        try {
            User user = userService.update(id, userDTO);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Deletar usuário",
            notes = ".updateUser()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário deletado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível deletar o usuário "),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", required = true, paramType = "path",
                    value = "Id do usuário", dataType = "Long"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        try {
            User user = userService.delete(id);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
