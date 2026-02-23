package com.rafatech.pedalparatodos.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafatech.pedalparatodos.exception.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {

        HttpStatus status = HttpStatus.FORBIDDEN;

        ApiError error = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                "Acesso negado.",
                request.getRequestURI()
        );

        response.setStatus(status.value());
        response.setContentType("application/json");

        objectMapper.writeValue(response.getOutputStream(), error);
    }
}

