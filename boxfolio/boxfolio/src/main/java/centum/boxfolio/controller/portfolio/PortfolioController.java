package centum.boxfolio.controller.portfolio;


import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.service.portfolio.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioServiceImpl portfolioService;

    @GetMapping
    public String portfolioPage(Model model) {
        return "/portfolio/folio_pub";
    }

    @PostMapping("/make")
    public String updatePortfolio(@Valid PortfolioSaveForm form,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  HttpServletRequest request) {
            if (bindingResult.hasErrors()){
                for (ObjectError e : bindingResult.getAllErrors()){
                    log.info("error" + e.toString());
                }
                return "redirect:/";
        }

            try{
                HttpSession session = request.getSession();
                String memberId = (String) session.getAttribute("loginMember");
                log.info(form.toString());
                Portfolio savedPortfolio = portfolioService.upload(form, memberId);
            } catch (IllegalStateException e){
                bindingResult.reject("upload portfolio failed", e.getMessage());
            }
            log.info("post success");

            return "/portfolio/folio_pub";
        }

    @GetMapping("/make")
    public String updatePortfolio(Model model){
        model.addAttribute("portfolioSaveForm", new PortfolioSaveForm());
        return "/portfolio/folio_make_json";
    }

    @GetMapping("/temp2")
    public String searchPortfolioWithTitle(@RequestParam String title, Model model){
        List<Portfolio> portfolio = portfolioService.searchWithTitle(title);
        model.addAttribute(portfolio);
        return "redirect:/";
    }

    @GetMapping("/temp3")
    public String searchPortfolioWithMember(@RequestParam Member member, Model model) {
        Portfolio portfolio = portfolioService.searchWithMember(member);
        model.addAttribute(portfolio);
        return "redirect:/";
    }


}
