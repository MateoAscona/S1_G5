package com.Sprint1.Sprint1.exception;

import com.Sprint1.Sprint1.dto.ExceptionDto;
import com.Sprint1.Sprint1.dto.ValidationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handlerRuntime(RuntimeException exception){

        ExceptionDto mensaje = new ExceptionDto(exception.getMessage());

        return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> handleBadRequestException(HttpMessageNotReadableException ex) {

        ExceptionDto mensaje = new ExceptionDto("Datos ingresados incorrectos.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
    }

     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationDTO> validationException(MethodArgumentNotValidException e){
        return ResponseEntity.ok(
                new ValidationDTO(
                        e.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList())
                )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationDTO> validationException(ConstraintViolationException e){
        return ResponseEntity.ok(
                new ValidationDTO(
                        e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList())
                )
        );
    }
}
