package com.xpbetsafe.avaliacao.dto;

import java.time.LocalDateTime;

public record ApostaDTO(Long usuarioId, Double valor, String tipo, String canal, LocalDateTime dataHora) {}
