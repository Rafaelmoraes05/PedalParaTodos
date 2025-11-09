package com.rafatech.pedalparatodos.service;

import com.rafatech.pedalparatodos.dto.CreatePedalRequest;
import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.entity.Usuario;
import com.rafatech.pedalparatodos.exception.ResourceNotFoundException;
import com.rafatech.pedalparatodos.repository.PedalRepository;
import com.rafatech.pedalparatodos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedalService {

    private final PedalRepository pedalRepository;
    private final UsuarioRepository usuarioRepository;

    public PedalService(PedalRepository pedalRepository, UsuarioRepository usuarioRepository) {
        this.pedalRepository = pedalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Pedal createPedal(CreatePedalRequest dto) {
        Usuario organizador = usuarioRepository.findById(dto.getOrganizadorId())
                .orElseThrow(()-> new ResourceNotFoundException("Organizador não encontrado com ID: " + dto.getOrganizadorId()));

        // Constrói o objeto Pedal a partir do DTO e do organizador encontrado.
        Pedal pedal = new Pedal(
                dto.getNomePedal(),
                dto.getNomeGrupo(),
                dto.getDescricao(),
                dto.getCategoria(),
                dto.getDataHora(),
                dto.getLocalEncontro(),
                dto.getNivelDificuldade(),
                dto.getLinkWhatsapp(),
                organizador
        );
        return pedalRepository.save(pedal);
    }

    @Transactional(readOnly = true)
    public List<Pedal> listAll() {
        return pedalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pedal findById(Long id) {
        return pedalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedal não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Pedal> listByOrganizador(Long organizadorId) {
        // Verifica se o organizador existe, para evitar retornar uma lista vazia sem motivo.
        usuarioRepository.findById(organizadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Organizador não encontrado com ID: " + organizadorId));

        return pedalRepository.findByOrganizadorId(organizadorId);
    }
}
