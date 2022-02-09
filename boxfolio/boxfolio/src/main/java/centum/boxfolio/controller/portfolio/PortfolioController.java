package centum.boxfolio.controller.portfolio;


import centum.boxfolio.service.portfolio.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;


}
