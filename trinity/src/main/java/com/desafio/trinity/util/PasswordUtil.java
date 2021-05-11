package com.desafio.trinity.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    public PasswordUtil(){}

    public static String gerarBCrypt(String senha){
        if (senha == null)
            return senha;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }
}
