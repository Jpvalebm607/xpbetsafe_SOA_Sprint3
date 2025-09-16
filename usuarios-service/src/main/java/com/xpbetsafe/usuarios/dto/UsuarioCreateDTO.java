package com.xpbetsafe.usuarios.dto;

import jakarta.validation.constraints.*;

public record UsuarioCreateDTO(
  @NotBlank String nome,
  @Email String email,
  @Size(min=8) String senha,
  @NotBlank String perfil
) {}
