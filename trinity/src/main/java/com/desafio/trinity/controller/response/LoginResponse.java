package com.desafio.trinity.controller.response;

import com.desafio.trinity.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 8733695533448928368L;

    private String token;

    private UserResponse user;
}
