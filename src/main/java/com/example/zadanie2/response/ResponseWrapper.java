package com.example.zadanie2.response;

import java.util.List;
import java.util.Map;
import java.time.Instant;

public class ResponseWrapper<T> {
    private String message;
    private T data;
    private List<Map<String, String>> errors;
    private Instant timestamp;

    public ResponseWrapper(String message, T data, List<Map<String, String>> errors) {
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = Instant.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Map<String, String>> getErrors() {
        return errors;
    }

    public void setErrors(List<Map<String, String>> errors) {
        this.errors = errors;
    }

    public Instant getTimestamp() { return timestamp; }

    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
