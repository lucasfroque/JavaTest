package br.com.cd2.sigabem.controllers.exceptions;

import br.com.cd2.sigabem.services.exceptions.CepNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(CepNotFoundException e, HttpServletRequest request) {
        String error = "Cep not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}