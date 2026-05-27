package com.yanborges.arena_reserve.service.factory;

import com.yanborges.arena_reserve.dto.agendamentoDto.AgendamentoRequest;
import com.yanborges.arena_reserve.exeception.BusinessException;


// ✨ PADRÃO TEMPLATE METHOD: Define o esqueleto rígido do algoritmo de validação
public abstract class ValidadorAgendamentoTemplate {

    // O método principal é final para que nenhuma subclasse altere a ordem de execução
    public final void validar(AgendamentoRequest request) {
        validarHorariosBasicos(request); // Passo comum a todos
        validarRegrasEspecificas(request); // Passo customizado pelas subclasses (Método Gancho)
    }

    private void validarHorariosBasicos(AgendamentoRequest request) {
        if (request.dataHoraFim().isBefore(request.dataHoraInicio()) || request.dataHoraFim().isEqual(request.dataHoraInicio())) {
            throw new BusinessException("A data de término deve ser maior que a data de início.");
        }
    }

    // Método abstrato que força as subclasses a criarem seus critérios específicos
    protected abstract void validarRegrasEspecificas(AgendamentoRequest request);
}