package centum.boxfolio.controller.portfolio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    @GetMapping
    public String portfolioPage(Model model) {
        return "portfolio/portfolios.html";
    }
}
