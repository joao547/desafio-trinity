package com.desafio.trinity.controller;

import com.desafio.trinity.controller.dto.LoginDTO;
import com.desafio.trinity.controller.response.LoginResponse;
import com.desafio.trinity.entity.User;
import com.desafio.trinity.service.UserService;
import com.desafio.trinity.service.exceptions.UnauthorizedException;
import com.desafio.trinity.service.impl.LoginServiceImpl;
import com.desafio.trinity.util.JwtTokenUtil;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signin")
@CrossOrigin("*")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final LoginServiceImpl loginService;

    private final UserService userService;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, LoginServiceImpl loginService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.loginService = loginService;
        this.userService = userService;
    }

    @ApiOperation(value = "Realizar login",
            notes = ".login()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário logado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível logar o usuário "),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "loginDTO", required = true, paramType = "body",
                    value = "Informações do login(login e senha)", dataType = "LoginDTO"),
    })
    @PostMapping
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        try {
            authenticate(loginDTO.getLogin(), loginDTO.getPassword());
            final UserDetails userDetails = loginService
                    .loadUserByUsername(loginDTO.getLogin());
            final String token = jwtTokenUtil.generateToken(userDetails);
            final User user = userService.getUser(loginDTO.getLogin());
            return ResponseEntity.ok(new LoginResponse(token,user.fromResponse()));
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
