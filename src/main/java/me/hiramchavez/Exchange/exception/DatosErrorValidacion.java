package me.hiramchavez.Exchange.exception;

import org.springframework.validation.FieldError;

public record DatosErrorValidacion(String error, String mensaje) {
    public DatosErrorValidacion(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}