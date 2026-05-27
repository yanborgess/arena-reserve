package com.yanborges.arena_reserve.service.factory;

import com.yanborges.arena_reserve.dto.agendamentoDto.AgendamentoRequest;
import com.yanborges.arena_reserve.exeception.BusinessException;
import com.yanborges.arena_reserve.model.Agendamento;
import com.yanborges.arena_reserve.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidadorConflitoHorario extends ValidadorAgendamentoTemplate {

    private final AgendamentoRepository agendamentoRepository;

    @Override
    protected void validarRegrasEspecificas(AgendamentoRequest request) {
        List<Agendamento> conflitos = agendamentoRepository.buscarAgendamentosConflitantes(
                request.quadraId(), request.dataHoraInicio(), request.dataHoraFim());

        if (!conflitos.isEmpty()) {
            throw new BusinessException("Este horário já está ocupado por outro agendamento ativo.");
        }
    }
}