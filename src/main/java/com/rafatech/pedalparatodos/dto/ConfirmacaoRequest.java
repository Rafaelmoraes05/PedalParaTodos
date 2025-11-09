package com.rafatech.pedalparatodos.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class ConfirmacaoRequest {
    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;
    @NotNull(message = "O ID do pedal é obrigatório")
    private Long pedalId;

    public ConfirmacaoRequest() {
    }

    public ConfirmacaoRequest(Long usuarioId, Long pedalId) {
        this.usuarioId = usuarioId;
        this.pedalId = pedalId;
    }

    // --- Getters ---
    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getPedalId() {
        return pedalId;
    }

    // --- Setters ---
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setPedalId(Long pedalId) {
        this.pedalId = pedalId;
    }

    // --- equals(), hashCode(), toString() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmacaoRequest that = (ConfirmacaoRequest) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(pedalId, that.pedalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, pedalId);
    }

    @Override
    public String toString() {
        return "ConfirmacaoRequest{" +
                "usuarioId=" + usuarioId +
                ", pedalId=" + pedalId +
                '}';
    }
}
