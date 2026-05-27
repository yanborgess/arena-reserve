package com.yanborges.arena_reserve.controller;


import com.yanborges.arena_reserve.dto.quadraDto.QuadraRequest;
import com.yanborges.arena_reserve.dto.quadraDto.QuadraResponse;
import com.yanborges.arena_reserve.service.services.QuadraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quadras")
@RequiredArgsConstructor
@Tag(name = "Quadras", description = "Endpoints para gerenciamento das quadras da arena")
public class QuadraController {

    private final QuadraService quadraService;


    @PostMapping
    @Operation(summary = "Criar uma nova quadra (Criação)")
    public ResponseEntity<QuadraResponse> criar(@RequestBody @Valid QuadraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quadraService.criarQuadra(request));
    }

    @GetMapping
    @Operation(summary = "Mostrar todas as quadras (Listagem)")
    public ResponseEntity<List<QuadraResponse>> listar() {
        return ResponseEntity.ok(quadraService.listarTodas());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desativar/Excluir uma quadra pelo ID (Exclusão Lógica)")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        quadraService.excluirQuadra(id);
        return ResponseEntity.noContent().build();
    }
}