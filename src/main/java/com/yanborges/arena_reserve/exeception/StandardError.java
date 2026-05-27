
package com.yanborges.arena_reserve.exeception;

import java.time.LocalDateTime;
import java.util.List;

// Record estruturado para padronizar a resposta visual do erro enviado ao cliente
public record StandardError(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String path,
        List<String> messages
) {}