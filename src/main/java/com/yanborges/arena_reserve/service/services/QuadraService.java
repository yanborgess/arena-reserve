package com.yanborges.arena_reserve.service.services;


import com.yanborges.arena_reserve.dto.quadraDto.QuadraRequest;
import com.yanborges.arena_reserve.dto.quadraDto.QuadraResponse;
import com.yanborges.arena_reserve.model.Quadra;
import com.yanborges.arena_reserve.repository.QuadraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuadraService {

    private final QuadraRepository quadraRepository;

    @Transactional
    public QuadraResponse criarQuadra(QuadraRequest request) {
        Quadra quadra = new Quadra();
        quadra.setNome(request.nome());
        quadra.setTipo(request.tipo());
        quadra.setPrecoPorHora(request.precoPorHora());
        quadra.setAtiva(true); // Toda quadra nasce ativa

        Quadra salva = quadraRepository.save(quadra);
        return converterParaResponse(salva);
    }

    @Transactional(readOnly = true)
    public List<QuadraResponse> listarTodas() {
        return quadraRepository.findAll().stream()
                .map(this::converterParaResponse)
                .toList();
    }

    @Transactional
    public void excluirQuadra(Long id) {
        Quadra quadra = quadraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quadra não encontrada com o ID: " + id));

        // Exclusão Lógica: Desativa a quadra para não quebrar chaves estrangeiras no banco
        quadra.setAtiva(false);
        quadraRepository.save(quadra);
    }

    private QuadraResponse converterParaResponse(Quadra quadra) {
        return new QuadraResponse(
                quadra.getId(),
                quadra.getNome(),
                quadra.getTipo(),
                quadra.getPrecoPorHora(),
                quadra.isAtiva()
        );
    }
}