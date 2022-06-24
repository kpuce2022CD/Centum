package centum.boxfolio.exception;

import centum.boxfolio.response.Response;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(AccountException.class)
    public Response accountExceptionHandle(AccountException e) {
        log.info("accountException: {}", e.getErrorType());
        return responseService.getFailResult(e.getErrorType());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Response IllegalArgumentExceptionHandle(IllegalArgumentException e) {
        log.info("IllegalArgumentException: {}", e.getMessage());
        e.printStackTrace();
        return responseService.getFailResultByMessage(e.getMessage());
    }

    @ExceptionHandler
    public Response generalExceptionHandle(Exception e) {
        log.info("generalException: {}", e.getMessage());
        e.printStackTrace();
        return responseService.getFailResultByMessage(e.getMessage());
    }
}
