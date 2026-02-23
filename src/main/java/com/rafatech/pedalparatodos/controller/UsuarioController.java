package com.rafatech.pedalparatodos.controller;

import com.rafatech.pedalparatodos.dto.PerfilResponseDTO;
import com.rafatech.pedalparatodos.dto.PerfilUsuarioUpdateDTO;
import com.rafatech.pedalparatodos.dto.UsuarioDTO;
import com.rafatech.pedalparatodos.entity.Usuario;
import com.rafatech.pedalparatodos.service.UsuarioService;
import com.rafatech.pedalparatodos.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Criar um Usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mapper.toUsuarioDTO(usuario));
    }

    //Lista todos os Usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listAllUsuarios() {
        List<Usuario> usuarios = usuarioService.listAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(Mapper::toUsuarioDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }

    //Busca o Usuario pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(Mapper.toUsuarioDTO(usuario));
    }

    @GetMapping("/perfil")
    public ResponseEntity<PerfilResponseDTO> getPerfil(Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                usuarioService.getPerfilUsuarioLogado(email)
        );
    }

    @PutMapping("/me")
    public ResponseEntity<PerfilResponseDTO> updatePerfil(
            @RequestBody PerfilUsuarioUpdateDTO dto,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                usuarioService.updatePerfilUsuarioLogado(email, dto)
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUsuario(Authentication authentication) {

        String email = authentication.getName();

        usuarioService.deleteUsuarioLogado(email);

        return ResponseEntity.noContent().build();
    }

}
