package com.lorenzovendrame.identityservice.controller;

import com.lorenzovendrame.identityservice.config.JwtUtil;
import com.lorenzovendrame.identityservice.dto.LoginRequest;
import com.lorenzovendrame.identityservice.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {

        // 1. O Spring Security vai pegar esse email e senha, vai chamar o seu
        // CustomUserDetailsService, buscar no banco e comparar as senhas automaticamente.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        // 2. Se a senha estiver errada, o Spring joga uma exceção antes de chegar aqui.
        // Se chegar aqui, o usuário está autenticado com sucesso!
        String token = jwtUtil.generateToken(authentication.getName());

        // 3. Retorna o token dentro do nosso DTO
        return ResponseEntity.ok(new TokenResponse(token));
    }
}