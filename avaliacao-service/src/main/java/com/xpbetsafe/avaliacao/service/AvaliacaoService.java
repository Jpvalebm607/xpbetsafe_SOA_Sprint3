package com.xpbetsafe.avaliacao.service;

import com.xpbetsafe.avaliacao.dto.*;
import com.xpbetsafe.avaliacao.entity.Avaliacao;
import com.xpbetsafe.avaliacao.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service @RequiredArgsConstructor
public class AvaliacaoService {
  private final WebClient apostasClient;
  private final AvaliacaoRepository repo;

  public AvaliacaoDTO calcular(Long usuarioId, PeriodoVO periodo) {
    periodo.validar();
    List<ApostaDTO> apostas = apostasClient.get()
        .uri(uri -> uri.path("/apostas")
          .queryParam("usuarioId", usuarioId)
          .queryParam("de", periodo.de())
          .queryParam("ate", periodo.ate())
          .build())
        .retrieve()
        .bodyToFlux(ApostaDTO.class)
        .collectList()
        .block();

    double gasto = apostas.stream().mapToDouble(ApostaDTO::valor).sum();
    String risco = (gasto > 1000 || apostas.size() > 20) ? "ALTO"
                 : (gasto > 300  || apostas.size() > 8)  ? "MEDIO"
                 : "BAIXO";

    var saved = repo.save(Avaliacao.builder()
      .usuarioId(usuarioId).gastoTotal(gasto).risco(risco).build());

    return new AvaliacaoDTO(saved.getId(), usuarioId, gasto, risco);
  }
}
