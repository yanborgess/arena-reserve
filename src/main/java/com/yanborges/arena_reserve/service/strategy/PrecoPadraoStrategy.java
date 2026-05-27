package com.yanborges.arena_reserve.service.strategy;

import java.math.BigDecimal;

public class PrecoPadraoStrategy implements CalculadoraPrecoStrategy {
    @Override
    public BigDecimal calcular(Long horas, BigDecimal precoBase) {
        // Multiplicação correta: horas convertidas em BigDecimal vezes o preço base
        return BigDecimal.valueOf(horas).multiply(precoBase);
    }
}