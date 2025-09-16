package com.xpbetsafe.usuarios.service;

import com.xpbetsafe.usuarios.dto.UsuarioCreateDTO;
import com.xpbetsafe.usuarios.dto.UsuarioDTO;
import com.xpbetsafe.usuarios.entity.Usuario;
import com.xpbetsafe.usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UsuarioService {
  private final UsuarioRepository repo;

  public UsuarioDTO criar(UsuarioCreateDTO in) {
    repo.findByEmail(in.email()).ifPresent(u -> { throw new IllegalArgumentException("E-mail já cadastrado"); });
    var entity = Usuario.builder()
        .nome(in.nome())
        .email(in.email())
        .perfil(in.perfil())
        .senhaHash(BCrypt.hashpw(in.senha(), BCrypt.gensalt()))
        .build();
    var salvo = repo.save(entity);
    return new UsuarioDTO(salvo.getId(), salvo.getNome(), salvo.getEmail(), salvo.getPerfil());
  }

  public UsuarioDTO buscar(Long id) {
    var u = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    return new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getPerfil());
  }
}
