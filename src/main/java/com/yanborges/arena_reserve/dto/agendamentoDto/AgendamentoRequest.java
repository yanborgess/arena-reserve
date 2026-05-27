package com.yanborges.arena_reserve.dto.agendamentoDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema; // 🇧🇷 Importação do OpenAPI
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record AgendamentoRequest(
        @NotNull(message = "O ID da quadra é obrigatório.")
        @Schema(example = "1")
        Long quadraId,

        @NotBlank(message = "O nome do cliente não pode estar em branco.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre {min} e {max} caracteres.")
        @Schema(example = "Yan Borges")
        String nomeCliente,

        @NotNull(message = "A data de início é obrigatória.")
        @Future(message = "A data de início deve ser no futuro.")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm", example = "27/05/2026 19:21")
        LocalDateTime dataHoraInicio,

        @NotNull(message = "A data de término é obrigatória.")
        @Future(message = "A data de término deve ser no futuro.")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm", example = "27/05/2026 20:21") 
        LocalDateTime dataHoraFim
) {}