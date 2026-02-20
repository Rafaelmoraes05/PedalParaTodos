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

    public PedalController(PedalService pedalService) {
        this.pedalService = pedalService;
    }

    @PostMapping
    public ResponseEntity<PedalDTO> createPedal(
            @Valid @RequestBody CreatePedalRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedalService.createPedal(request));
    }

    @GetMapping
    public ResponseEntity<List<PedalDTO>> listAllPedais() {
        return ResponseEntity.ok(pedalService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedalDTO> getPedalById(@PathVariable Long id) {
        return ResponseEntity.ok(pedalService.findById(id));
    }

    @GetMapping("/organizador/{organizadorId}")
    public ResponseEntity<List<PedalDTO>> listPedaisByOrganizador(
            @PathVariable Long organizadorId) {

        return ResponseEntity.ok(
                pedalService.listByOrganizador(organizadorId)
        );
    }
}

