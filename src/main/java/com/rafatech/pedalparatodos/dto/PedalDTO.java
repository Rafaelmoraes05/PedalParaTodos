package com.rafatech.pedalparatodos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rafatech.pedalparatodos.entity.enums.Categoria;
import com.rafatech.pedalparatodos.entity.enums.NivelDificuldade;

import java.time.LocalDateTime;
import java.util.Objects;

public class PedalDTO {
    private Long id;
    private String nomePedal;
    private String nomeGrupo;
    private String descricao;
    private Categoria categoria;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    private String localEncontro;

    private String cidade;
    private String estado;
    private String pais;

    private NivelDificuldade nivelDificuldade;
    private String linkWhatsapp;
    private Long organizadorId;
    private String organizadorNome;
    private Integer numeroParticipantes;

    public PedalDTO() {}

    public PedalDTO(Long id,
                    String nomePedal,
                    String nomeGrupo,
                    String descricao,
                    Categoria categoria,
                    LocalDateTime dataHora,
                    String localEncontro,
                    String cidade,
                    String estado,
                    String pais,
                    NivelDificuldade nivelDificuldade,
                    String linkWhatsapp,
                    Long organizadorId,
                    String organizadorNome,
                    Integer numeroParticipantes) {

        this.id = id;
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
        this.organizadorId = organizadorId;
        this.organizadorNome = organizadorNome;
        this.numeroParticipantes = numeroParticipantes;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

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

    public Long getOrganizadorId() {
        return organizadorId;
    }

    public String getOrganizadorNome() {
        return organizadorNome;
    }

    public Integer getNumeroParticipantes() {
        return numeroParticipantes;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }

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

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

    public void setOrganizadorNome(String organizadorNome) {
        this.organizadorNome = organizadorNome;
    }

    public void setNumeroParticipantes(Integer numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // --- equals(), hashCode(), toString() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedalDTO pedalDTO = (PedalDTO) o;
        return Objects.equals(id, pedalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PedalDTO{" +
                "id=" + id +
                ", nomePedal='" + nomePedal + '\'' +
                ", nomeGrupo='" + nomeGrupo + '\'' +
                ", categoria=" + categoria +
                ", dataHora=" + dataHora +
                ", localEncontro='" + localEncontro + '\'' +
                ", nivelDificuldade=" + nivelDificuldade +
                ", organizadorId=" + organizadorId +
                '}';
    }
}
