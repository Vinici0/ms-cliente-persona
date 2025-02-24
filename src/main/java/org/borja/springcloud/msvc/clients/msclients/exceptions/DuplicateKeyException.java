package org.borja.springcloud.msvc.clients.msclients.exceptions;

public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException(String message) {
        super(message);
    }
}