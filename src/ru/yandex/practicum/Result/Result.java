package ru.yandex.practicum.Result;

import java.util.Optional;

public class Result<T> {
    private final Optional<T> value;
    private final ErrorCode errorCode;
    private final boolean success;

    private Result(Optional<T> value, ErrorCode errorCode, boolean success) {
        this.value = value;
        this.errorCode = errorCode;
        this.success = success;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(Optional.of(value), null, true);
    }

    public static <T> Result<T> success() {
        return new Result<>(Optional.empty(), null, true);
    }

    public static <T> Result<T> failure(ErrorCode errorCode) {
        return new Result<>(Optional.empty(), errorCode, false);
    }

    public boolean isSuccess() { return success; }
    public boolean isFailure() { return !success; }
    public Optional<T> getValue() { return value; }
    public ErrorCode getErrorCode() { return errorCode; }
}