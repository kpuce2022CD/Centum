package centum.boxfolio.controller.portfolio;

import centum.boxfolio.entity.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    @GetMapping
    public String portfolioPage(Model model) {
        return "portfolio/portfolios";
    }

    @PostMapping
    public String updatePortfolio(@RequestBody String body) {
        return "redirect:/";
    }
}
