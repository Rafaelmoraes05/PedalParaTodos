package com.rafatech.pedalparatodos.dto;

import java.time.LocalDate;

public class PerfilResponseDTO {

    private String nome;
    private String email;
    private String localizacao;
    private LocalDate dataNascimento;
    private String biografia;
    private int numeroDeParticipacoes;

    public PerfilResponseDTO(String nome,
                             String email,
                             String localizacao,
                             LocalDate dataNascimento,
                             String biografia,
                             int numeroDeParticipacoes) {
        this.nome = nome;
        this.email = email;
        this.localizacao = localizacao;
        this.dataNascimento = dataNascimento;
        this.biografia = biografia;
        this.numeroDeParticipacoes = numeroDeParticipacoes;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getLocalizacao() { return localizacao; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getBiografia() { return biografia; }
    public int getNumeroDeParticipacoes() { return numeroDeParticipacoes; }
}
