package centum.boxfolio.service.response;

import centum.boxfolio.exception.ErrorResponse;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    @Override
    public <T> Response<T> getResult(String key, Object obj) {
        Response<T> result = new Response<>();
        Map<String,Object> map = new HashMap<>();
        map.put(key,obj);

        if("".equals(key)) {
            result.setData((T) obj);
        } else {
            result.setData((T) map);
        }

        result.setResponse(true);
        result.setSuccess(ReturnResponse.SUCCESS.getReturnSuccess());

        return result;
    }

    @Override
    public Response getSuccessResult() {
        Response result = new Response();
        result.setResponse(true);
        result.setSuccess(ReturnResponse.SUCCESS.getReturnSuccess());
        return result;
    }

    @Override
    public Response getFailResult(ErrorResponse errorResponse) {
        Response result = new Response();
        result.setResponse(false);
        result.setSuccess(ReturnResponse.FAIL.getReturnSuccess());
        result.setError(errorResponse);
        return result;
    }

    @Override
    public Response getFailResult(ErrorType errorType) {
        Response result = new Response();
        result.setResponse(false);
        result.setSuccess(ReturnResponse.FAIL.getReturnSuccess());
        result.setError(new ErrorResponse(errorType));
        return result;
    }

    @Override
    public Response getFailResultByMessage(String message) {
        Response result = new Response();
        result.setResponse(false);
        result.setSuccess(ReturnResponse.FAIL.getReturnSuccess());
        result.setError(message);
        return result;
    }

    @Override
    public Response getInvalidResult() {
        Response result = new Response();
        result.setResponse(false);
        result.setSuccess(ReturnResponse.SUCCESS.getReturnSuccess());
        return result;
    }
}
