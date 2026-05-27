package com.yanborges.arena_reserve.dto.agendamentoDto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

// ✨ PADRÃO DTO: Usando Java Record para transferir os dados de entrada com Bean Validation
public record AgendamentoRequest(
        @NotNull(message = "O ID da quadra é obrigatório.")
        Long quadraId,

        @NotBlank(message = "O nome do cliente não pode estar em branco.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
        String nomeCliente,

        @NotNull(message = "A data de início é obrigatória.")
        @Future(message = "A data de início deve ser no futuro.")
        LocalDateTime dataHoraInicio,

        @NotNull(message = "A data de término é obrigatória.")
        @Future(message = "A data de término deve ser no futuro.")
        LocalDateTime dataHoraFim
) {}