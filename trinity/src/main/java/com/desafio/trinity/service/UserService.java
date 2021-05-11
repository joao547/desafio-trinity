package com.desafio.trinity.service;

import com.desafio.trinity.controller.dto.UserDTO;
import com.desafio.trinity.entity.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    User create(UserDTO userDTO);

    User update(Long id, UserDTO userDTO);

    User delete(Long id);

    List<User> listAll();

    User getUser(String login);

    User getUser(Long id) throws NotFoundException;
}
