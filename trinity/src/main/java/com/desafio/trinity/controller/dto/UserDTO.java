package com.desafio.trinity.controller.dto;

import com.desafio.trinity.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -2435951840877448931L;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String login;

    private String password;

    private String phone;

}
