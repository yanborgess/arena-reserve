package com.yanborges.arena_reserve.exeception;

// Exceção genérica para quando alguma regra de negócio falhar
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}