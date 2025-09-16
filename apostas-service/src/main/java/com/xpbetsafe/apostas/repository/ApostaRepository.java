package com.xpbetsafe.apostas.repository;

import com.xpbetsafe.apostas.entity.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {
  List<Aposta> findByUsuarioIdAndDataHoraBetween(Long usuarioId, LocalDateTime de, LocalDateTime ate);
}
