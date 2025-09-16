package com.xpbetsafe.usuarios.controller;

import com.xpbetsafe.usuarios.dto.UsuarioCreateDTO;
import com.xpbetsafe.usuarios.dto.UsuarioDTO;
import com.xpbetsafe.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/usuarios") @RequiredArgsConstructor
public class UsuarioController {
  private final UsuarioService service;

  @PostMapping
  public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioCreateDTO in) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(in));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
    return ResponseEntity.ok(service.buscar(id));
  }
}
