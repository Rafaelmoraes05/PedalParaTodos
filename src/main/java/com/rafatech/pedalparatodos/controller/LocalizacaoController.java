package com.rafatech.pedalparatodos.controller;

import com.rafatech.pedalparatodos.dto.LocalizacaoDTO;
import com.rafatech.pedalparatodos.service.LocalizacaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @GetMapping
    public List<LocalizacaoDTO> buscar(
            @RequestParam String query) {

        return localizacaoService.buscarCidades(query);
    }
}
