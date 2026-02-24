package com.rafatech.pedalparatodos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafatech.pedalparatodos.entity.enums.Categoria;
import com.rafatech.pedalparatodos.entity.enums.NivelDificuldade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreatePedalRequest {

    @NotBlank(message = "O nome do pedal é obrigatório")
    private String nomePedal;

    private String nomeGrupo;
    private String descricao;

    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;

    @NotNull(message = "A data e hora do pedal são obrigatórias")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    @NotBlank(message = "O local de encontro é obrigatório")
    private String localEncontro;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    private String estado;

    @NotBlank(message = "O país é obrigatório")
    private String pais;

    @NotNull(message = "O nível de dificuldade é obrigatório")
    private NivelDificuldade nivelDificuldade;

    private String linkWhatsapp;

    public CreatePedalRequest() {}

    public CreatePedalRequest(String nomePedal,
                              String nomeGrupo,
                              String descricao,
                              Categoria categoria,
                              LocalDateTime dataHora,
                              String localEncontro,
                              String cidade,
                              String estado,
                              String pais,
                              NivelDificuldade nivelDificuldade,
                              String linkWhatsapp) {

        this.nomePedal = nomePedal;
        this.nomeGrupo = nomeGrupo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.localEncontro = localEncontro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.nivelDificuldade = nivelDificuldade;
        this.linkWhatsapp = linkWhatsapp;
    }

    // --- Getters ---
    public String getNomePedal() {
        return nomePedal;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getLocalEncontro() {
        return localEncontro;
    }

    public NivelDificuldade getNivelDificuldade() {
        return nivelDificuldade;
    }

    public String getLinkWhatsapp() {
        return linkWhatsapp;
    }

    // --- Setters ---
    public void setNomePedal(String nomePedal) {
        this.nomePedal = nomePedal;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setLocalEncontro(String localEncontro) {
        this.localEncontro = localEncontro;
    }

    public void setNivelDificuldade(NivelDificuldade nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public void setLinkWhatsapp(String linkWhatsapp) {
        this.linkWhatsapp = linkWhatsapp;
    }

    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getPais() { return pais; }

    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setPais(String pais) { this.pais = pais; }

    // --- equals(), hashCode(), toString() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePedalRequest that = (CreatePedalRequest) o;
        return Objects.equals(nomePedal, that.nomePedal) &&
                categoria == that.categoria &&
                Objects.equals(dataHora, that.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomePedal, categoria, dataHora);
    }

    @Override
    public String toString() {
        return "CreatePedalRequest{" +
                "nomePedal='" + nomePedal + '\'' +
                ", nomeGrupo='" + nomeGrupo + '\'' +
                ", categoria=" + categoria +
                ", dataHora=" + dataHora +
                ", localEncontro='" + localEncontro + '\'' +
                ", nivelDificuldade=" + nivelDificuldade +
                '}';
    }
}