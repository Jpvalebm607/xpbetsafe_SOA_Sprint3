package com.xpbetsafe.apostas.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record ApostaDTO(
  @NotNull Long usuarioId,
  @NotNull @Positive Double valor,
  @NotBlank String tipo,
  @NotBlank String canal,
  @NotNull LocalDateTime dataHora
) {}
