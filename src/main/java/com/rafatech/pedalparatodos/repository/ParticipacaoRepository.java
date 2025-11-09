package com.rafatech.pedalparatodos.repository;

import com.rafatech.pedalparatodos.entity.Participacao;
import com.rafatech.pedalparatodos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Integer> {

    Optional<Participacao> findByUsuarioIdAndPedalId(Long usuarioId, Long pedalId);
    List<Participacao> findByUsuarioId(Long usuarioId);
    List<Participacao> findByPedalId(Long pedalId);
}
