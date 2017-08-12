package com.jirapave.imdb.common.exception.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorMessageDTO {

    /**
     * contains the same HTTP Status code returned by the server
     */
    int status;

    /**
     * application specific error code
     */
    int code;

    /**
     * message describing the error
     */
    String message;

    /**
     * Time of occurence
     */
    LocalDateTime timestamp;

}
