package com.yanborges.arena_reserve.dto.quadraDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record QuadraRequest(
        @NotBlank(message = "O nome da quadra é obrigatório.")
        String nome,

        @NotBlank(message = "O tipo de piso (ex: Society, Saibro, Cimento) é obrigatório.")
        String tipo,

        @NotNull(message = "O preço por hora é obrigatório.")
        @Positive(message = "O preço por hora deve ser maior que zero.")
        BigDecimal precoPorHora
) {}