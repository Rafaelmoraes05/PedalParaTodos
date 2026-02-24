package com.rafatech.pedalparatodos.repository;

import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface PedalRepository extends JpaRepository<Pedal, Long> {
    List<Pedal> findByOrganizadorId(Long organizadorId);

    Optional<Pedal> findById(Long id);

    List<Pedal> findByCidadeIgnoreCase(String cidade);

    List<Pedal> findByDataHoraBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<Pedal> findByCidadeIgnoreCaseAndDataHoraBetween(
            String cidade,
            LocalDateTime inicio,
            LocalDateTime fim
    );
//    @Query("""
//    SELECT p FROM Pedal p
//    WHERE (:cidade IS NULL OR p.cidade = :cidade)
//    AND (
//        :data IS NULL OR
//        (p.dataHora >= :dataInicio AND p.dataHora < :dataFim)
//    )
//    """)
//    List<Pedal> filtrar(
//            @Param("cidade") String cidade,
//            @Param("data") LocalDate data,
//            @Param("dataInicio") LocalDateTime dataInicio,
//            @Param("dataFim") LocalDateTime dataFim
//    );
}
