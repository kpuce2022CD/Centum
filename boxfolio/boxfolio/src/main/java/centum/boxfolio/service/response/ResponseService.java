package centum.boxfolio.service.response;

import centum.boxfolio.exception.ErrorResponse;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.response.Response;
import lombok.Getter;

public interface ResponseService {
    @Getter
    public enum ReturnResponse {
        SUCCESS(0),
        FAIL(-1);

        Integer returnSuccess;

        ReturnResponse(Integer returnSuccess) {
            this.returnSuccess = returnSuccess;
        }
    }

    public <T> Response<T> getResult(String key, Object obj);
    public Response getSuccessResult();
    public Response getFailResult(ErrorResponse errorResponse);
    public Response getFailResult(ErrorType errorType);
    public Response getFailResultByMessage(String message);
    public Response getInvalidResult();

}
