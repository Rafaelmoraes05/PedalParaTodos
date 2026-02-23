package com.rafatech.pedalparatodos.service;

import com.rafatech.pedalparatodos.dto.PerfilResponseDTO;
import com.rafatech.pedalparatodos.dto.PerfilUsuarioUpdateDTO;
import com.rafatech.pedalparatodos.dto.UsuarioDTO;
import com.rafatech.pedalparatodos.entity.Usuario;
import com.rafatech.pedalparatodos.exception.BadRequestException;
import com.rafatech.pedalparatodos.exception.ResourceNotFoundException;
import com.rafatech.pedalparatodos.repository.UsuarioRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario createUsuario(UsuarioDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Email já cadastrado.");
        }
        String encodedPassword = passwordEncoder.encode(dto.getSenha());
        Usuario usuario = new Usuario(
                dto.getNome(),
                dto.getEmail(),
                encodedPassword
        );
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public List<Usuario> listAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                new ArrayList<>());
    }

    @Transactional(readOnly = true)
    public PerfilResponseDTO getPerfilUsuarioLogado(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado"));

        int numeroParticipacoes = usuario.getParticipacoes().size();

        return new PerfilResponseDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLocalizacao(),
                usuario.getDataNascimento(),
                usuario.getBiografia(),
                numeroParticipacoes
        );
    }

    @Transactional
    public PerfilResponseDTO updatePerfilUsuarioLogado(
            String email,
            PerfilUsuarioUpdateDTO dto) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado")
                );

        usuario.setLocalizacao(dto.getLocalizacao());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setBiografia(dto.getBiografia());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new PerfilResponseDTO(
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getEmail(),
                usuarioAtualizado.getLocalizacao(),
                usuarioAtualizado.getDataNascimento(),
                usuarioAtualizado.getBiografia(),
                usuarioAtualizado.getParticipacoes().size()
        );
    }

    @Transactional
    public void deleteUsuarioLogado(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado.")
                );

        usuarioRepository.delete(usuario);
    }
}
