package com.xpbetsafe.avaliacao.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PeriodoVO(@NotNull LocalDateTime de, @NotNull LocalDateTime ate) {
  public void validar() { if (de.isAfter(ate)) throw new IllegalArgumentException("Período inválido"); }
}
