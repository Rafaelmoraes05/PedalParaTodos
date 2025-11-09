package com.rafatech.pedalparatodos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class ParticipacaoDTO {
    private Long id;
    private Long usuarioId;
    private String usuarioNome;
    private Long pedalId;
    private String nomePedal;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataConfirmacao;

    public ParticipacaoDTO() {
    }

    public ParticipacaoDTO(Long id, Long usuarioId, String usuarioNome, Long pedalId, String nomePedal, LocalDateTime dataConfirmacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.pedalId = pedalId;
        this.nomePedal = nomePedal;
        this.dataConfirmacao = dataConfirmacao;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public Long getPedalId() {
        return pedalId;
    }

    public String getNomePedal() {
        return nomePedal;
    }

    public LocalDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public void setPedalId(Long pedalId) {
        this.pedalId = pedalId;
    }

    public void setNomePedal(String nomePedal) {
        this.nomePedal = nomePedal;
    }

    public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    // --- equals(), hashCode(), toString() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipacaoDTO that = (ParticipacaoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ParticipacaoDTO{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", pedalId=" + pedalId +
                ", dataConfirmacao=" + dataConfirmacao +
                '}';
    }
}
