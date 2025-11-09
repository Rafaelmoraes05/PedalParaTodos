package com.rafatech.pedalparatodos.controller;

import com.rafatech.pedalparatodos.dto.ConfirmacaoRequest;
import com.rafatech.pedalparatodos.dto.ParticipacaoDTO;
import com.rafatech.pedalparatodos.dto.PedalDTO;
import com.rafatech.pedalparatodos.dto.UsuarioDTO;
import com.rafatech.pedalparatodos.entity.Participacao;
import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.entity.Usuario;
import com.rafatech.pedalparatodos.service.ParticipacaoService;
import com.rafatech.pedalparatodos.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/participacoes")
public class ParticipacaoController {

    private final ParticipacaoService participacaoService;

    public ParticipacaoController(ParticipacaoService participacaoService) {
        this.participacaoService = participacaoService;
    }

    //Confirmar a presença de um Usuário em um pedal
    @PostMapping
    public ResponseEntity<ParticipacaoDTO> confirmarPresenca(@Valid @RequestBody ConfirmacaoRequest confirmacaoRequest) {
        Participacao participacao = participacaoService.confirmarPresenca(confirmacaoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mapper.toParticipacaoDTO(participacao));
    }

    //listar todos os pedais que um usuário confirmou presença
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedalDTO>> listPedaisConfirmadosPorUsuario(@PathVariable Long usuarioId) {
        List<Pedal> pedais = participacaoService.listPedaisPorUsuario(usuarioId);
        List<PedalDTO> pedaisDTO = pedais.stream()
                .map(Mapper::toPedalDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedaisDTO);
    }

    //listar todos os usuários que confirmaram presença em um pedal específico
    @GetMapping("/pedal/{pedalId}")
    public ResponseEntity<List<UsuarioDTO>> listParticipantesPorPedal(@PathVariable Long pedalId) {
        List<Usuario> usuarios = participacaoService.listUsuariosPorPedal(pedalId);
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(Mapper::toUsuarioDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }
}
