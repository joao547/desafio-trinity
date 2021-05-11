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
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 8733695533448928368L;

    private String login;

    private String password;
}
