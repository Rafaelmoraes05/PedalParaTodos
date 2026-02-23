package com.rafatech.pedalparatodos.service;

import com.rafatech.pedalparatodos.dto.ConfirmacaoRequest;
import com.rafatech.pedalparatodos.dto.ParticipacaoDTO;
import com.rafatech.pedalparatodos.entity.Participacao;
import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.entity.Usuario;
import com.rafatech.pedalparatodos.exception.BadRequestException;
import com.rafatech.pedalparatodos.exception.ResourceNotFoundException;
import com.rafatech.pedalparatodos.repository.ParticipacaoRepository;
import com.rafatech.pedalparatodos.repository.PedalRepository;
import com.rafatech.pedalparatodos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PedalRepository pedalRepository;

    public ParticipacaoService(ParticipacaoRepository participacaoRepository,
                               UsuarioRepository usuarioRepository,
                               PedalRepository pedalRepository) {
        this.participacaoRepository = participacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.pedalRepository = pedalRepository;
    }

    @Transactional
    public Participacao confirmarPresenca(ConfirmacaoRequest dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioId()));


        Pedal pedal = pedalRepository.findById(dto.getPedalId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedal não encontrado com ID: " + dto.getPedalId()));

        // Verifica se a participação já existe para este usuário e pedal
        if (participacaoRepository.findByUsuarioIdAndPedalId(dto.getUsuarioId(), dto.getPedalId()).isPresent()) {
            throw new BadRequestException("Usuário já confirmou presença neste pedal.");
        }

        // Cria a nova participação com a data e hora atuais
        Participacao participacao = new Participacao(usuario, pedal, LocalDateTime.now());
        return participacaoRepository.save(participacao);
    }

    @Transactional(readOnly = true)
    public List<Pedal> listPedaisPorUsuario(Long usuarioId) {

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        // Busca todas as participações do usuário e mapeia para a entidade Pedal
        return participacaoRepository.findByUsuarioId(usuarioId).stream()
                .map(Participacao::getPedal) // Extrai o Pedal de cada Participacao
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ParticipacaoDTO> listarParticipantesPorPedal(Long pedalId) {

        pedalRepository.findById(pedalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pedal não encontrado com ID: " + pedalId
                ));

        return participacaoRepository.findByPedalId(pedalId).stream()
                .map(p -> new ParticipacaoDTO(
                        p.getId(),
                        p.getUsuario().getId(),
                        p.getUsuario().getNome(),
                        p.getPedal().getId(),
                        p.getPedal().getNomePedal(),
                        p.getDataConfirmacao()
                ))
                .toList();
    }
}
