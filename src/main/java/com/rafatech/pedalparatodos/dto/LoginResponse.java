package com.rafatech.pedalparatodos.dto;

public class LoginResponse {

    private String message;
    private String token;
    private UsuarioResponseDTO usuario;

    public LoginResponse(String message, String token, UsuarioResponseDTO usuario) {
        this.message = message;
        this.token = token;
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }
}
