package centum.boxfolio.service.response;

import centum.boxfolio.response.Response;
import lombok.Getter;

public interface ResponseService {
    @Getter
    public enum ReturnResponse {
        SUCCESS(0, "Success"),
        FAIL(-1, "Fail");

        Integer returnCode;
        String returnMessage;

        ReturnResponse(Integer returnCode, String returnMessage) {
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
        }
    }

    public <T> Response<T> getResult(String key, Object obj);
    public Response getSuccessResult();
    public Response getFailResult(String message);

}
