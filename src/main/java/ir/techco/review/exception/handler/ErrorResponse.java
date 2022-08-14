package ir.techco.review.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorResponse {
    private String message;
    private int code;
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, int code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
