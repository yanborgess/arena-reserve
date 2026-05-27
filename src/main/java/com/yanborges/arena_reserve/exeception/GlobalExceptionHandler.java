package com.yanborges.arena_reserve.exeception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // Captura qualquer erro lançado pelos Controllers do sistema
public class GlobalExceptionHandler {

    // Captura erros de regras de negócio (Retorna HTTP 400 Bad Request)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> handleBusiness(BusinessException ex, HttpServletRequest request) {
        StandardError err = new StandardError(
                LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "Regra de Negócio Violada", request.getRequestURI(), List.of(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // Captura erros de validação do @Valid (Retorna HTTP 422 Unprocessable Entity)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        StandardError err = new StandardError(
                LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de Validação de Dados", request.getRequestURI(), erros);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }
}