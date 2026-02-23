package com.rafatech.pedalparatodos.dto;

public class LocalizacaoDTO {
    private String displayName;
    private String cidade;
    private String estado;
    private String pais;

    public LocalizacaoDTO(String displayName, String cidade, String estado, String pais) {
        this.displayName = displayName;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public String getDisplayName() { return displayName; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getPais() { return pais; }
}
