package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.portfolio.PortfolioService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PortfolioApiController {

    private final PortfolioService portfolioService;
    private final ResponseService responseService;

    @GetMapping("/portfolios")
    public Response<List<Portfolio>> getPortfolios() {
        return responseService.getResult("portfolios", portfolioService.searchLatestInPublic());
    }

    @GetMapping("/portfolios/best")
    public Response<List<Portfolio>> getBestPortfolios() {
        return responseService.getResult("bestPortfolios", portfolioService.searchHighestStarInPublic(4));
    }

    @GetMapping("/portfolio/{id}")
    public Response<Portfolio> getPortfolio(@PathVariable Long id) {
        Optional<Portfolio> portfolio = portfolioService.findById(id);
        if (portfolio.isEmpty()) {
            return responseService.getFailResult("Not Found");
        }
        return responseService.getResult("portfolio", portfolio.get());
    }

    @DeleteMapping("/portfolio/{id}")
    public Response deletePortfolio(@PathVariable Long id) {
        Optional<Portfolio> portfolio = portfolioService.findById(id);
        if (portfolio.isEmpty()) {
            return responseService.getFailResult("Not Found");
        }

        portfolioService.delete(portfolio.get());
        return responseService.getSuccessResult();
    }
}
