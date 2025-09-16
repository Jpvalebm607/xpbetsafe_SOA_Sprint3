package com.xpbetsafe.avaliacao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="avaliacoes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Avaliacao {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="usuario_id", nullable=false) private Long usuarioId;
  @Column(name="gasto_total", nullable=false) private Double gastoTotal;
  @Column(nullable=false, length=10) private String risco; // BAIXO/MEDIO/ALTO
}
