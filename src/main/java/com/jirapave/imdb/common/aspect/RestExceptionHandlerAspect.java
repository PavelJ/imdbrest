package com.jirapave.imdb.common.aspect;

import com.jirapave.imdb.common.constant.ErrorCodes;
import com.jirapave.imdb.common.exception.RestException;
import com.jirapave.imdb.common.exception.dto.ErrorMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class RestExceptionHandlerAspect {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        if (ex instanceof RestException) {
            return handleCustomException((RestException) ex);
        } else {
            return handleGenericException(ex);
        }
    }

    private ResponseEntity<Object> handleGenericException(Exception ex) {
        ErrorMessageDTO errorMessageDto = ErrorMessageDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(ErrorCodes.GENERIC_ERROR)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).headers(new HttpHeaders()).body(errorMessageDto);
    }

    private ResponseEntity<Object> handleCustomException(RestException ex) {
        ErrorMessageDTO errorMessageDto = ErrorMessageDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .status(ex.getStatus().value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(ex.getStatus()).headers(new HttpHeaders()).body(errorMessageDto);
    }

}
