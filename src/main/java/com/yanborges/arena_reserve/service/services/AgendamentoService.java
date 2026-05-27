package com.yanborges.arena_reserve.service.services;


import com.yanborges.arena_reserve.dto.agendamentoDto.AgendamentoRequest;
import com.yanborges.arena_reserve.dto.agendamentoDto.AgendamentoResponse;
import com.yanborges.arena_reserve.exeception.BusinessException;
import com.yanborges.arena_reserve.model.Agendamento;
import com.yanborges.arena_reserve.model.Quadra;
import com.yanborges.arena_reserve.repository.AgendamentoRepository;
import com.yanborges.arena_reserve.repository.QuadraRepository;
import com.yanborges.arena_reserve.service.factory.CalculadoraPrecoFactory;
import com.yanborges.arena_reserve.service.factory.ValidadorConflitoHorario;
import com.yanborges.arena_reserve.service.strategy.CalculadoraPrecoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final QuadraRepository quadraRepository;
    private final ValidadorConflitoHorario validadorConflitoHorario;

    @Transactional
    public AgendamentoResponse criarAgendamento(AgendamentoRequest request) {
        // Busca a quadra desejada
        Quadra quadra = quadraRepository.findById(request.quadraId())
                .orElseThrow(() -> new BusinessException("Quadra não encontrada com o ID fornecido."));

        if (!quadra.isAtiva()) {
            throw new BusinessException("Não é possível agendar uma quadra que está inativa.");
        }

        // Executando o padrão TEMPLATE METHOD para as validações de horários e conflitos
        validadorConflitoHorario.validar(request);

        // Calcula a duração total em horas
        long horas = Duration.between(request.dataHoraInicio(), request.dataHoraFim()).toHours();
        if (horas < 1) horas = 1;

        // Executando o padrão FACTORY METHOD e STRATEGY para calcular o preço da reserva
        CalculadoraPrecoStrategy estrategia = CalculadoraPrecoFactory.obterEstrategia(request.dataHoraInicio());
        BigDecimal valorTotal = estrategia.calcular(horas, quadra.getPrecoPorHora());

        // Executando o padrão BUILDER para instanciar a entidade Agendamento de forma limpa
        Agendamento agendamento = Agendamento.builder()
                .quadra(quadra)
                .nomeCliente(request.nomeCliente())
                .dataHoraInicio(request.dataHoraInicio())
                .dataHoraFim(request.dataHoraFim())
                .valorTotal(valorTotal)
                .status("AGENDADO")
                .build();

        // Salvando o agendamento através do padrão REPOSITORY
        agendamento = agendamentoRepository.save(agendamento);

        // Retornando os dados encapsulados no padrão DTO
        return converterParaResponse(agendamento);
    }

    private AgendamentoResponse converterParaResponse(Agendamento a) {
        return new AgendamentoResponse(
                a.getId(), a.getQuadra().getId(), a.getQuadra().getNome(),
                a.getNomeCliente(), a.getDataHoraInicio(), a.getDataHoraFim(),
                a.getValorTotal(), a.getStatus()
        );
    }

    // Retorna todos os agendamentos do banco (READ ALL)
    @Transactional(readOnly = true)
    public java.util.List<AgendamentoResponse> listarTodos() {
        return agendamentoRepository.findAll().stream()
                .map(this::converterParaResponse)
                .toList();
    }

    // Busca um agendamento específico por ID (READ BY ID)
    @Transactional(readOnly = true)
    public AgendamentoResponse buscarPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Agendamento não encontrado com o ID: " + id));
        return converterParaResponse(agendamento);
    }

    // Atualiza o nome do cliente ou horários (UPDATE)
    @Transactional
    public AgendamentoResponse atualizarAgendamento(Long id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Agendamento não encontrado para atualização."));

        // Regra de Negócio: Não permite alterar agendamentos já finalizados ou cancelados
        if (!"AGENDADO".equals(agendamento.getStatus())) {
            throw new BusinessException("Apenas agendamentos ativos podem ser modificados.");
        }

        // Executa novamente o Template Method se as datas mudaram
        if (!agendamento.getDataHoraInicio().equals(request.dataHoraInicio()) ||
                !agendamento.getDataHoraFim().equals(request.dataHoraFim())) {
            validadorConflitoHorario.validar(request);

            long horas = Duration.between(request.dataHoraInicio(), request.dataHoraFim()).toHours();
            if (horas < 1) horas = 1;

            CalculadoraPrecoStrategy estrategia = CalculadoraPrecoFactory.obterEstrategia(request.dataHoraInicio());
            agendamento.setValorTotal(estrategia.calcular(horas, agendamento.getQuadra().getPrecoPorHora()));
        }

        agendamento.setNomeCliente(request.nomeCliente());
        agendamento.setDataHoraInicio(request.dataHoraInicio());
        agendamento.setDataHoraFim(request.dataHoraFim());

        return converterParaResponse(agendamentoRepository.save(agendamento));
    }

    // Controle de Fluxo de Status / Exclusão Lógica (DELETE/PATCH)
    @Transactional
    public void cancelarAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Agendamento não encontrado para cancelamento."));

        if ("CANCELADO".equals(agendamento.getStatus())) {
            throw new BusinessException("Este agendamento já se encontra cancelado.");
        }

        // Altera o status (Regra de Negócio de controle de fluxo)
        agendamento.setStatus("CANCELADO");
        agendamentoRepository.save(agendamento);
    }
}