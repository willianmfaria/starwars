package br.com.willian.handler;

import br.com.willian.exception.ConflictException;
import br.com.willian.exception.NotFoundException;
import br.com.willian.util.ErrorDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@ControllerAdvice
@RestController
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public ResourceExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> resourceNotFound(NotFoundException ex, HttpStatus status, WebRequest request) {
        ErrorDetails msg = new ErrorDetails(
                System.currentTimeMillis(),
                status.value(),
                status.getReasonPhrase(),
                "404: NOT FOUND"
        );
        return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    protected ResponseEntity<Object> resourceConflict(ConflictException ex, HttpStatus status, WebRequest request) {
        ErrorDetails msg = new ErrorDetails(
                System.currentTimeMillis(),
                status.value(),
                status.getReasonPhrase(),
                "409: CONFLICT"
        );
        return new ResponseEntity(msg, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale local = LocaleContextHolder.getLocale();
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<String> mensagens = new ArrayList<>();
        errors.stream().forEach(
                msg -> {
                    String campo = msg.getField().substring(msg.getField().indexOf(".") + 1);
                    String error = messageSource.getMessage(msg, local);
                    mensagens.add("Campo " + campo + ": " + error);
                }
        );
        ErrorDetails msg = new ErrorDetails(
                System.currentTimeMillis(),
                status.value(),
                status.getReasonPhrase(),
                "Augmentos inválidos."
        );
        msg.setErrors(mensagens);
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }
}