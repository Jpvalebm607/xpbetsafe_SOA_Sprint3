package com.xpbetsafe.usuarios.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 120)
  private String nome;

  @Column(nullable = false, unique = true, length = 160)
  private String email;

  @Column(name = "senha_hash", nullable = false, length = 255)
  private String senhaHash;

  @Column(nullable = false, length = 20)
  private String perfil;
}
