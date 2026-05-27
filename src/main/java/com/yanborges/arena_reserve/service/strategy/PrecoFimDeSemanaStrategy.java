package com.yanborges.arena_reserve.service.strategy;

import java.math.BigDecimal;

public class PrecoFimDeSemanaStrategy implements CalculadoraPrecoStrategy {
    @Override
    public BigDecimal calcular(Long horas, BigDecimal precoBase) {
        BigDecimal precoNormal = BigDecimal.valueOf(horas).multiply(precoBase);
        // Multiplica o preço normal por 1.20 (20% de acréscimo)
        return precoNormal.multiply(BigDecimal.valueOf(1.20));
    }
}