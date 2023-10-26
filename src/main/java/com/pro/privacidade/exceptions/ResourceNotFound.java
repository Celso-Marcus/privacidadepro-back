package com.pro.privacidade.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String Resource, Long id) {
        super(String.format("Resource %s not found with id %s", Resource, id));
    }
}
