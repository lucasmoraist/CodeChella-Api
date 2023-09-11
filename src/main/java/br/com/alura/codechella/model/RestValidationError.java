package br.com.alura.codechella.model;

public record RestValidationError(
    Integer code,
    String field,
    String message
) {}
