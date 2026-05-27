package com.yanborges.arena_reserve.service.factory;

import com.yanborges.arena_reserve.service.strategy.CalculadoraPrecoStrategy;
import com.yanborges.arena_reserve.service.strategy.PrecoFimDeSemanaStrategy;
import com.yanborges.arena_reserve.service.strategy.PrecoPadraoStrategy;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

// ✨ PADRÃO FACTORY METHOD: Fábrica responsável por decidir qual instância de estratégia criar
public class CalculadoraPrecoFactory {

    public static CalculadoraPrecoStrategy obterEstrategia(LocalDateTime data) {
        DayOfWeek dia = data.getDayOfWeek();

        if (dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY) {
            return new PrecoFimDeSemanaStrategy();
        }
        return new PrecoPadraoStrategy();
    }
}