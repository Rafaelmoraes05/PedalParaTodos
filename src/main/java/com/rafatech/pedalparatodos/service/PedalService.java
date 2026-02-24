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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
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

        // 🔐 Pegar usuário logado pelo token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Usuario organizador = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário autenticado não encontrado"));

        Pedal pedal = new Pedal(
                dto.getNomePedal(),
                dto.getNomeGrupo(),
                dto.getDescricao(),
                dto.getCategoria(),
                dto.getDataHora(),
                dto.getLocalEncontro(),
                dto.getCidade(),
                dto.getEstado(),
                dto.getPais(),
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
                pedal.getCidade(),
                pedal.getEstado(),
                pedal.getPais(),
                pedal.getNivelDificuldade(),
                pedal.getLinkWhatsapp(),
                pedal.getOrganizador().getId(),
                pedal.getOrganizador().getNome(),
                total != null ? total.intValue() : 0
        );
    }

    private Usuario getUsuarioLogado() {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário logado não encontrado."));
    }

    @Transactional
    public PedalDTO updatePedal(Long id, CreatePedalRequest dto) {

        //Buscar pedal
        Pedal pedal = pedalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pedal não encontrado com ID: " + id));

        //Buscar usuário logado
        Usuario usuarioLogado = getUsuarioLogado();

        //Validar se é o organizador
        if (!pedal.getOrganizador().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não pode editar este pedal.");
        }

        //Atualizar campos
        pedal.setNomePedal(dto.getNomePedal());
        pedal.setNomeGrupo(dto.getNomeGrupo());
        pedal.setDescricao(dto.getDescricao());
        pedal.setCategoria(dto.getCategoria());
        pedal.setDataHora(dto.getDataHora());
        pedal.setLocalEncontro(dto.getLocalEncontro());
        pedal.setCidade(dto.getCidade());
        pedal.setEstado(dto.getEstado());
        pedal.setPais(dto.getPais());
        pedal.setNivelDificuldade(dto.getNivelDificuldade());
        pedal.setLinkWhatsapp(dto.getLinkWhatsapp());

        //Salvar
        Pedal updated = pedalRepository.save(pedal);

        return mapToDTO(updated);
    }

    @Transactional
    public void deletePedal(Long id) {

        //Buscar pedal
        Pedal pedal = pedalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pedal não encontrado com ID: " + id));

        //Buscar usuário logado
        Usuario usuarioLogado = getUsuarioLogado();

        //Verificar se é o organizador
        if (!pedal.getOrganizador().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não pode deletar este pedal.");
        }

        //Deletar
        pedalRepository.delete(pedal);
    }

    public List<PedalDTO> filtrar(String cidade, LocalDate data) {

        cidade = (cidade != null && !cidade.isBlank())
                ? cidade.trim()
                : null;

        // Nenhum filtro
        if (cidade == null && data == null) {
            return listAll();
        }

        // Apenas cidade
        if (cidade != null && data == null) {
            return pedalRepository
                    .findByCidadeIgnoreCase(cidade)
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }

        // Apenas data
        if (cidade == null) {
            LocalDateTime inicio = data.atStartOfDay();
            LocalDateTime fim = data.plusDays(1).atStartOfDay();

            return pedalRepository
                    .findByDataHoraBetween(inicio, fim)
                    .stream()
                    .map(this::mapToDTO)
                    .toList();
        }

        // Cidade + data
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.plusDays(1).atStartOfDay();

        return pedalRepository
                .findByCidadeIgnoreCaseAndDataHoraBetween(cidade, inicio, fim)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

}
