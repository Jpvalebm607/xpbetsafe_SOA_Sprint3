package com.xpbetsafe.apostas.controller;

import com.xpbetsafe.apostas.dto.ApostaDTO;
import com.xpbetsafe.apostas.entity.Aposta;
import com.xpbetsafe.apostas.repository.ApostaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController @RequestMapping("/apostas") @RequiredArgsConstructor
public class ApostaController {
  private final ApostaRepository repo;

  @PostMapping
  public ResponseEntity<Aposta> criar(@Valid @RequestBody ApostaDTO in) {
    var salvo = repo.save(Aposta.builder()
        .usuarioId(in.usuarioId())
        .valor(in.valor())
        .tipo(in.tipo())
        .canal(in.canal())
        .dataHora(in.dataHora())
        .build());
    return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Aposta> buscar(@PathVariable Long id) {
    return ResponseEntity.of(repo.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<Aposta>> listar(
      @RequestParam Long usuarioId,
      @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime de,
      @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime ate
  ) {
    return ResponseEntity.ok(repo.findByUsuarioIdAndDataHoraBetween(usuarioId, de, ate));
  }
}
