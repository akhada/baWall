package com.github.mobileartisans.bawall.domain;

public class HttpClientException extends RuntimeException {
    public final ClientError error;

    public enum ClientError {
        UNKNOWN, UNAUTHORIZED, NOT_FOUND
    }

    public HttpClientException(ClientError error) {
        this.error = error;
    }
}
