package com.otus.common.exceptions;

public class AnnotationEmptyException extends Exception {
    public AnnotationEmptyException(String annotation) {
        super(String.format("Annotations %s not present on page class", annotation));
    }
}
