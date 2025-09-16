package com.xpbetsafe.apostas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="apostas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Aposta {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="usuario_id", nullable=false) private Long usuarioId;
  @Column(nullable=false) private Double valor;
  @Column(nullable=false, length=20) private String tipo;
  @Column(nullable=false, length=30) private String canal;
  @Column(name="data_hora", nullable=false) private LocalDateTime dataHora;
}
