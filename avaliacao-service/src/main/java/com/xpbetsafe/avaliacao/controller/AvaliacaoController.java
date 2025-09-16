package com.xpbetsafe.avaliacao.controller;

import com.xpbetsafe.avaliacao.dto.AvaliacaoDTO;
import com.xpbetsafe.avaliacao.dto.PeriodoVO;
import com.xpbetsafe.avaliacao.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/avaliacoes") @RequiredArgsConstructor
public class AvaliacaoController {
  private final AvaliacaoService service;

  @PostMapping("/calcular")
  public ResponseEntity<AvaliacaoDTO> calcular(@RequestParam Long usuarioId,
                                               @Valid @RequestBody PeriodoVO periodo) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.calcular(usuarioId, periodo));
  }
}
