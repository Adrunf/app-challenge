package com.ec.app.microservices.config;

import lombok.Builder;
import lombok.Getter;

/**
 * Generic response class
 *
 * @author arobayo
 */

@Builder
@Getter
public class Response<T> {
    private T data;
    private String message;
    private Integer code;
}

