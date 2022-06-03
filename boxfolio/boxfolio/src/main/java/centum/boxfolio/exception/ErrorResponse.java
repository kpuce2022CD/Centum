package centum.boxfolio.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String exception;
    private String code;
    private String message;
    private Integer status;
    private String error;
}
