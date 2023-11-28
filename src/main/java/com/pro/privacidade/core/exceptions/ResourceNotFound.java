package com.pro.privacidade.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFound(String Resource, Long id) {
        super(String.format("Resource %s not found with id %s", Resource, id));
    }

    public ResourceNotFound(String Resource, String name) {
        super(String.format("Resource %s not found with name %s", Resource, name));

    }
}
