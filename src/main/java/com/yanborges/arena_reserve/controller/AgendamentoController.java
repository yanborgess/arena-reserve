package com.yanborges.arena_reserve.controller;


import com.yanborges.arena_reserve.dto.agendamentoDto.AgendamentoRequest;
import com.yanborges.arena_reserve.dto.agendamentoDto.AgendamentoResponse;
import com.yanborges.arena_reserve.service.services.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Endpoints para o CRUD completo de reservas")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @Operation(summary = "Cria um novo agendamento (CREATE)")
    public ResponseEntity<AgendamentoResponse> criar(@RequestBody @Valid AgendamentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.criarAgendamento(request));
    }

    @GetMapping
    @Operation(summary = "Lista todos os agendamentos cadastrados (READ ALL)")
    public ResponseEntity<List<AgendamentoResponse>> listar() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um agendamento pelo ID (READ BY ID)")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um agendamento existente (UPDATE)")
    public ResponseEntity<AgendamentoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AgendamentoRequest request) {
        return ResponseEntity.ok(agendamentoService.atualizarAgendamento(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancela um agendamento (DELETE LÓGICO)")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.noContent().build(); // Retorna HTTP 204 No Content
    }
}