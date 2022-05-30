package centum.boxfolio.service.response;

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

        result.setResult(true);
        result.setResultCode(ReturnResponse.SUCCESS.getReturnCode());
        result.setMessage(ReturnResponse.SUCCESS.getReturnMessage());

        return result;
    }

    @Override
    public Response getSuccessResult() {
        Response result = new Response();
        result.setResult(true);
        result.setResultCode(ReturnResponse.SUCCESS.getReturnCode());
        result.setMessage(ReturnResponse.SUCCESS.getReturnMessage());
        return result;
    }

    @Override
    public Response getFailResult(String message) {
        Response result = new Response();
        result.setResult(false);
        result.setResultCode(ReturnResponse.FAIL.getReturnCode());
        result.setMessage(ReturnResponse.FAIL.getReturnMessage() + " : " + message);
        return result;
    }
}
