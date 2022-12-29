package Enum;

public enum StatusCodes {

    OK(200),
    CREATED(201),
    NOT_FOUND(404);

    private final int code;

    StatusCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}