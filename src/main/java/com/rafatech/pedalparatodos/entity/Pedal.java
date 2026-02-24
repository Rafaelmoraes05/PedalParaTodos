package com.rafatech.pedalparatodos.entity;

import jakarta.persistence.*;
import com.rafatech.pedalparatodos.entity.enums.Categoria;
import com.rafatech.pedalparatodos.entity.enums.NivelDificuldade;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedal")
public class Pedal {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nome_pedal", nullable = false)
        private String nomePedal;

        @Column(name = "nome_grupo")
        private String nomeGrupo;

        @Column(name = "descricao", columnDefinition = "TEXT")
        private String descricao;

        @Enumerated(EnumType.STRING)
        private Categoria categoria;

        @Column(name = "data_hora", nullable = false)
        private LocalDateTime dataHora;

        @Column(name = "local_encontro", nullable = false)
        private String localEncontro;

        @Column(name = "cidade", nullable = false)
        private String cidade;

        @Column(name = "estado", nullable = false)
        private String estado;

        @Column(name = "pais", nullable = false)
        private String pais;



    @Enumerated(EnumType.STRING)
        private NivelDificuldade nivelDificuldade;

        @Column(name = "link_whatsapp")
        private String linkWhatsapp;

        //Relacionamentos
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "organizador_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedal_usuario"))
        private Usuario organizador;

        @OneToMany(mappedBy = "pedal", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Participacao> participacoes = new ArrayList<>();


        public Pedal() {
        }

        public Pedal(String nomePedal,
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
                     Usuario organizador) {

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
            this.organizador = organizador;
        }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNomePedal() {
            return nomePedal;
        }

        public void setNomePedal(String nomePedal) {
            this.nomePedal = nomePedal;
        }

        public String getNomeGrupo() {
            return nomeGrupo;
        }

        public void setNomeGrupo(String nomeGrupo) {
            this.nomeGrupo = nomeGrupo;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public Categoria getCategoria() {
            return categoria;
        }

        public void setCategoria(Categoria categoria) {
            this.categoria = categoria;
        }

        public LocalDateTime getDataHora() {
            return dataHora;
        }

        public void setDataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
        }

        public String getLocalEncontro() {
            return localEncontro;
        }

        public void setLocalEncontro(String localEncontro) {
            this.localEncontro = localEncontro;
        }

        public NivelDificuldade getNivelDificuldade() {
            return nivelDificuldade;
        }

        public void setNivelDificuldade(NivelDificuldade nivelDificuldade) {
            this.nivelDificuldade = nivelDificuldade;
        }

        public String getLinkWhatsapp() {
            return linkWhatsapp;
        }

        public void setLinkWhatsapp(String linkWhatsapp) {
            this.linkWhatsapp = linkWhatsapp;
        }

        public Usuario getOrganizador() {
            return organizador;
        }

        public void setOrganizador(Usuario organizador) {
            this.organizador = organizador;
        }

        public List<Participacao> getParticipacoes() {
            return participacoes;
        }

        public void setParticipacoes(List<Participacao> participacoes) {
            this.participacoes = participacoes;
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

        // equals() e hashCode() baseados no ID
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pedal pedal = (Pedal) o;
            return Objects.equals(id, pedal.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Pedal{" +
                    "id=" + id +
                    ", nomePedal='" + nomePedal + '\'' +
                    ", nomeGrupo='" + nomeGrupo + '\'' +
                    ", categoria=" + categoria +
                    ", dataHora=" + dataHora +
                    ", localEncontro='" + localEncontro + '\'' +
                    ", nivelDificuldade=" + nivelDificuldade +
                    ", organizadorId=" + (organizador != null ? organizador.getId() : "null") +
                    '}';
        }
    }
