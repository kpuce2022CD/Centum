package centum.boxfolio.controller.home;

import centum.boxfolio.response.Response;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final ResponseService responseService;

    @GetMapping("/")
    public Response home() {
        return responseService.getSuccessResult();
    }
}