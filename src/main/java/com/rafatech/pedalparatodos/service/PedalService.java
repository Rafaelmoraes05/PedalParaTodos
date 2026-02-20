package com.rafatech.pedalparatodos.service;

import com.rafatech.pedalparatodos.dto.CreatePedalRequest;
import com.rafatech.pedalparatodos.dto.PedalDTO;
import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.entity.Usuario;
import com.rafatech.pedalparatodos.exception.ResourceNotFoundException;
import com.rafatech.pedalparatodos.repository.ParticipacaoRepository;
import com.rafatech.pedalparatodos.repository.PedalRepository;
import com.rafatech.pedalparatodos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedalService {

    private final PedalRepository pedalRepository;
    private final UsuarioRepository usuarioRepository;
    private final ParticipacaoRepository participacaoRepository;

    public PedalService(PedalRepository pedalRepository,
                        UsuarioRepository usuarioRepository,
                        ParticipacaoRepository participacaoRepository) {
        this.pedalRepository = pedalRepository;
        this.usuarioRepository = usuarioRepository;
        this.participacaoRepository = participacaoRepository;
    }

    @Transactional
    public PedalDTO createPedal(CreatePedalRequest dto) {

        Usuario organizador = usuarioRepository.findById(dto.getOrganizadorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Organizador não encontrado com ID: " + dto.getOrganizadorId()));

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

        Pedal saved = pedalRepository.save(pedal);

        return mapToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<PedalDTO> listAll() {
        return pedalRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedalDTO findById(Long id) {
        Pedal pedal = pedalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pedal não encontrado com ID: " + id));

        return mapToDTO(pedal);
    }

    @Transactional(readOnly = true)
    public List<PedalDTO> listByOrganizador(Long organizadorId) {

        usuarioRepository.findById(organizadorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Organizador não encontrado com ID: " + organizadorId));

        return pedalRepository.findByOrganizadorId(organizadorId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private PedalDTO mapToDTO(Pedal pedal) {

        Long total = participacaoRepository.countByPedalId(pedal.getId());

        return new PedalDTO(
                pedal.getId(),
                pedal.getNomePedal(),
                pedal.getNomeGrupo(),
                pedal.getDescricao(),
                pedal.getCategoria(),
                pedal.getDataHora(),
                pedal.getLocalEncontro(),
                pedal.getNivelDificuldade(),
                pedal.getLinkWhatsapp(),
                pedal.getOrganizador().getId(),
                pedal.getOrganizador().getNome(),
                total != null ? total.intValue() : 0
        );
    }
}
