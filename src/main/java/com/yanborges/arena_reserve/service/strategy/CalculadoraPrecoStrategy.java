package com.yanborges.arena_reserve.service.strategy;

import java.math.BigDecimal;

//  PADRÃO STRATEGY: Interface que define o comportamento genérico de cálculo
public interface CalculadoraPrecoStrategy {
    BigDecimal calcular(Long horas, BigDecimal precoBase);
}