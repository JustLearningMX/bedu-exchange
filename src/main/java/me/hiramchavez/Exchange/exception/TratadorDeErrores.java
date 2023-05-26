package me.hiramchavez.Exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DatosErrorValidacion> manageException(Exception e) {
        System.out.println("Error Exception");

        DatosErrorValidacion error = new DatosErrorValidacion("true", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DatosErrorValidacion> runtimeException(RuntimeException e) {
        System.out.println("Error RuntimeException");

        DatosErrorValidacion error = new DatosErrorValidacion("true", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
