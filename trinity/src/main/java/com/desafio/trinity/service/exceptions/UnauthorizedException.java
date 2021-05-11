package com.desafio.trinity.service.exceptions;

public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = -4816952496118798994L;

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
