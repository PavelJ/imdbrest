package com.jirapave.imdb.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RestException extends RuntimeException {

    /**
     * Specific HTTP status code for the given error
     */
    private HttpStatus status;

    /**
     * application specific error code
     */
    private int code;

    /**
     * detailed error description
     */
    private String message;

}
