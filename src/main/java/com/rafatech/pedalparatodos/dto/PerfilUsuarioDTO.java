package com.rafatech.pedalparatodos.dto;

import java.time.LocalDate;

public class PerfilUsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String localizacao;
    private LocalDate dataNascimento;
    private String biografia;
    private int totalParticipacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getTotalParticipacoes() {
        return totalParticipacoes;
    }

    public void setTotalParticipacoes(int totalParticipacoes) {
        this.totalParticipacoes = totalParticipacoes;
    }
}
