package com.rafatech.pedalparatodos.controller;

import com.rafatech.pedalparatodos.dto.CreatePedalRequest;
import com.rafatech.pedalparatodos.dto.PedalDTO;
import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.service.ParticipacaoService;
import com.rafatech.pedalparatodos.service.PedalService;
import com.rafatech.pedalparatodos.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedais")
public class PedalController {

    private final PedalService pedalService;
    private final ParticipacaoService participacaoService;

    public PedalController(PedalService pedalService, ParticipacaoService participacaoService) {
        this.pedalService = pedalService;
        this.participacaoService = participacaoService;
    }

    // Criar Novo Pedal
    @PostMapping
    public ResponseEntity<PedalDTO> createPedal(@Valid @RequestBody CreatePedalRequest createPedalRequest) {
        Pedal pedal = pedalService.createPedal(createPedalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mapper.toPedalDTO(pedal));
    }

    //Listar todos os Pedais
    @GetMapping
    public ResponseEntity<List<PedalDTO>> listAllPedais() {
        List<Pedal> pedais = pedalService.listAll();
        List<PedalDTO> pedaisDTO = pedais.stream()
                .map(pedal -> {
                    PedalDTO dto = Mapper.toPedalDTO(pedal);
                    dto.setNumeroParticipantes(pedal.getParticipacoes().size());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedaisDTO);
    }

    //Buscar um Pedal pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<PedalDTO> getPedalById(@PathVariable Long id) {
        Pedal pedal = pedalService.findById(id);
        PedalDTO pedalDTO = Mapper.toPedalDTO(pedal);
        // Preenche o número de participantes para este pedal específico
        pedalDTO.setNumeroParticipantes(participacaoService.listUsuariosPorPedal(id).size());
        return ResponseEntity.ok(pedalDTO);
    }

    //Listar Pedais de um organizador específico
    @GetMapping("/organizador/{organizadorId}")
    public ResponseEntity<List<PedalDTO>> listPedaisByOrganizador(@PathVariable Long organizadorId) {
        List<Pedal> pedais = pedalService.listByOrganizador(organizadorId);
        List<PedalDTO> pedaisDTO = pedais.stream()
                .map(pedal -> {
                    PedalDTO dto = Mapper.toPedalDTO(pedal);
                    dto.setNumeroParticipantes(pedal.getParticipacoes().size());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedaisDTO);
    }
}
