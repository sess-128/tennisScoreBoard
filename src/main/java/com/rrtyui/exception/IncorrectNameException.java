package com.rrtyui.exception;

public class IncorrectNameException extends RuntimeException {
    public IncorrectNameException(String message) {
        super(message); // Передаем сообщение об ошибке
    }

    public IncorrectNameException(String message, Throwable cause) {
        super(message, cause); // Передаем сообщение и причину
    }
}
