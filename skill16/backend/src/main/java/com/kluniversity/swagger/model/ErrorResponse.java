package com.kluniversity.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Standard error response returned when an operation fails")
public class ErrorResponse {

    @Schema(description = "Timestamp of the error", example = "2024-06-10T12:00:00")
    private LocalDateTime timestamp;

    @Schema(description = "Human-readable error message", example = "Student not found with id: 99")
    private String message;

    @Schema(description = "HTTP status code", example = "404")
    private int statusCode;

    public ErrorResponse(String message, int statusCode) {
        this.timestamp  = LocalDateTime.now();
        this.message    = message;
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage()          { return message; }
    public int getStatusCode()          { return statusCode; }
}
