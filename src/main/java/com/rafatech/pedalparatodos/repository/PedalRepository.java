package com.rafatech.pedalparatodos.repository;

import com.rafatech.pedalparatodos.entity.Pedal;
import com.rafatech.pedalparatodos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedalRepository extends JpaRepository<Pedal, Long> {
    List<Pedal> findByOrganizadorId(Long organizadorId);
    Optional<Pedal> findById(Long id);
}
