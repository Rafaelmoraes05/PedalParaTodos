package com.rafatech.pedalparatodos.controller;

import com.rafatech.pedalparatodos.dto.LoginRequest;
import com.rafatech.pedalparatodos.dto.LoginResponse;
import com.rafatech.pedalparatodos.dto.UsuarioResponseDTO;
import com.rafatech.pedalparatodos.entity.Usuario;
import com.rafatech.pedalparatodos.exception.UnauthorizedException;
import com.rafatech.pedalparatodos.repository.UsuarioRepository;
import com.rafatech.pedalparatodos.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> createAuthenticationToken(
            @RequestBody @Valid LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getSenha()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Email ou senha incorretos");
        }

        // Carrega dados de segurança
        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(loginRequest.getEmail());

        // Gera token
        final String jwt = jwtUtil.generateToken(userDetails);

        // Busca usuário real no banco
        Usuario usuario = usuarioRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Converte para DTO seguro
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );

        // Monta resposta final
        LoginResponse response = new LoginResponse(
                "Login realizado",
                jwt,
                usuarioDTO
        );

        return ResponseEntity.ok(response);
    }
}