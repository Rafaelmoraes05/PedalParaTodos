package com.rafatech.pedalparatodos.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "participacao", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "pedal_id"}, name = "uc_participacao"))
public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relacionamentos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_participacao_usuario"))
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_participacao_pedal"))
    private Pedal pedal;

    @Column(name = "data_confirmacao", nullable = false)
    private LocalDateTime dataConfirmacao;

    public Participacao() {}

    public Participacao(Usuario usuario, Pedal pedal, LocalDateTime dataConfirmacao) {
        this.usuario = usuario;
        this.pedal = pedal;
        this.dataConfirmacao = dataConfirmacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pedal getPedal() {
        return pedal;
    }

    public void setPedal(Pedal pedal) {
        this.pedal = pedal;
    }

    public LocalDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participacao that = (Participacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Participacao{" +
                "id=" + id +
                ", usuarioId=" + (usuario != null ? usuario.getId() : "null") +
                ", pedalId=" + (pedal != null ? pedal.getId() : "null") +
                ", dataConfirmacao=" + dataConfirmacao +
                '}';
    }
}
