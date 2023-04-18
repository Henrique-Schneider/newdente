package br.com.projetointegrador.newdente.exception.handler;


import br.com.projetointegrador.newdente.exception.InvalidDataException;
import br.com.projetointegrador.newdente.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> processarErrorResourceNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidDataException.class})
    public ResponseEntity<String> processarErrorInvalidData(InvalidDataException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
