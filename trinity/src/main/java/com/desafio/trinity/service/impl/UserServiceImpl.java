package com.desafio.trinity.service.impl;

import com.desafio.trinity.controller.dto.UserDTO;
import com.desafio.trinity.entity.User;
import com.desafio.trinity.repository.UserRepository;
import com.desafio.trinity.service.UserService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(UserDTO userDTO) {
        return repository.save(new User(userDTO));
    }

    @Override
    public User update(Long id, UserDTO userDTO) {
        User user = repository.findById(id).get();
        if (user == null)
            throw new NullPointerException("Usuário não encontrado");
        user = setUpdateInfo(user, userDTO);
        return repository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = repository.findById(id).get();
        if (user == null)
            throw new NullPointerException("Usuário não encontrado");
        repository.delete(user);
        return user;
    }

    @Override
    public List<User> listAll() {
        List<User> users = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        if (users.isEmpty())
            throw new NullPointerException("Nenhum usuário encontrado");
        return users;
    }

    @Override
    public User getUser(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isEmpty())
            throw new NullPointerException("Usuário não encontrado");
        return optional.get();
    }

    private User setUpdateInfo(User user, UserDTO userDTO){
        user.setFirstName(userDTO.getFirstName());
        user.setBirthday(userDTO.getBirthday());
        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
        user.setLogin(userDTO.getLogin());
        user.setPhone(userDTO.getPhone());
        if (userDTO.getPassword() != null)
            user.setPassword(userDTO.getPassword());
        return user;
    }
}
