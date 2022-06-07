package centum.boxfolio.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String code;
    private String message;
    private Integer status;
    private String error;

    public ErrorResponse(String code, String message, Integer status, String error) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.error = error;
    }

    public ErrorResponse(ErrorType errorType) {
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
        this.status = errorType.getStatus().value();
        this.error = errorType.getStatus().getReasonPhrase();
    }
}
