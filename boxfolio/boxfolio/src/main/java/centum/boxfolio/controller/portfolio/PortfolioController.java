package centum.boxfolio.controller.portfolio;


import centum.boxfolio.controller.member.SessionConst;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.repository.member.MemberRepositoryImpl;
import centum.boxfolio.service.portfolio.PortfolioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Port;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioServiceImpl portfolioService;
    private final MemberRepositoryImpl memberRepository;




    @GetMapping
    public String portfolioPage(Model model, HttpServletRequest request) {

        List<Portfolio> highestPortfolioList = portfolioService.searchHighestStar(5);
        List<Portfolio> normalPortfolioList = portfolioService.searchLatest();

        List<PortfolioLoadForm> highestPortfolioLoadFormList = new ArrayList<>();
        List<PortfolioLoadForm> normalPortfolioLoadFormList = new ArrayList<>();


        for (Portfolio p : highestPortfolioList){
            highestPortfolioLoadFormList.add(portfolioToPortfolioLoadForm(p));
        }

        for (Portfolio p : normalPortfolioList){
            normalPortfolioLoadFormList.add(portfolioToPortfolioLoadForm(p));
        }
        if (highestPortfolioLoadFormList.isEmpty()) {
            highestPortfolioLoadFormList.add(new PortfolioLoadForm(
                    "test",
                    "test",
                    0,
                    0,
                    LocalDateTime.now(),
                    "test",
                    0
            ));
        }

        if (normalPortfolioLoadFormList.isEmpty()) {
            normalPortfolioLoadFormList.add(new PortfolioLoadForm(
                    "test",
                    "test",
                    0,
                    0,
                    LocalDateTime.now(),
                    "test",
                    0
            ));
        }


        model.addAttribute("highestPortfolioList", highestPortfolioLoadFormList);
        model.addAttribute("normalPortfolioList", normalPortfolioLoadFormList);

        return "/portfolio/folio_pub";
    }

    @PostMapping("/make")
    public String uploadPortfolio(@Valid PortfolioSaveForm form,
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
                long memberId = (long) session.getAttribute(SessionConst.LOGIN_MEMBER);
                log.info(form.getContents());

                Portfolio savedPortfolio = portfolioService.upload(form, memberId);
            } catch (IllegalStateException e){
                bindingResult.reject("upload portfolio failed", e.getMessage());
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        log.info("post success");

            return "/portfolio/folio_pub";
        }

    @GetMapping("/make")
    public String uploadPortfolio(Model model){
        model.addAttribute("portfolioSaveForm", new PortfolioSaveForm());
        return "/portfolio/folio_make_json";
    }

    @GetMapping("/search/title")
    public String searchPortfolioWithTitle(@RequestParam String title, Model model){
        List<Portfolio> portfolio = portfolioService.searchWithTitle(title);

        List<PortfolioLoadForm> portfolioLoadFormList = new ArrayList<>();

        for (Portfolio p : portfolio){
            portfolioLoadFormList.add(portfolioToPortfolioLoadForm(p));
        }

        model.addAttribute("portfolioList", portfolioLoadFormList);
        return "/portfolio/folio_pub";
    }

    @GetMapping("/search/member")
    public String searchPortfolioWithMember(@RequestParam Member member, Model model) {
        Portfolio portfolio = portfolioService.searchWithMember(member);


        model.addAttribute("portfolioList", portfolio);

        return "/portfolio/folio_other";
    }

    @GetMapping("/search/Id")
    public String searchPortfolioWithId(@RequestParam long id, Model model) {
        Portfolio portfolio = portfolioService.searchWithId(id);
        model.addAttribute("portfolio", portfolio);
        return "/portfolio/folio_other";
    }

    @GetMapping("/search/mine")
    public String searchPortfolioMine(Model model, HttpServletRequest request) {

        Portfolio portfolio = getPortfolioBySessionId(request);


        model.addAttribute("portfolio", portfolio);

        return "/portfolio/folio_mine";
    }

    @GetMapping("/search/other")
    public String searchHighest(Model model){

        List<Portfolio> portfolioList = portfolioService.searchHighestStar(5);

        model.addAttribute("portfolioList", portfolioList);

        return "/portfolio/folio_other";
    }

    @GetMapping("/search/name")
    public String searchPortfolioWithNickname(@RequestParam String nickname, Model model){

        List<Portfolio> portfolioList = portfolioService.searchWithNickname(nickname);
        List<PortfolioLoadForm> portfolioLoadFormList = new ArrayList<>();

        for (Portfolio p : portfolioList){
            portfolioLoadFormList.add(portfolioToPortfolioLoadForm(p));
        }

        model.addAttribute("portfolioList", portfolioLoadFormList);
        return "/portfolio/folio_pub";
    }



    @GetMapping("/delete")
    public String deletePortfolio(HttpServletRequest request){
        Portfolio portfolio = getPortfolioBySessionId(request);

        portfolioService.delete(portfolio);
        return "/portfolio/folio_pub";
    }

    @GetMapping("/update")
    public String updatePortfolio(HttpServletRequest request, Model model){

        Portfolio portfolio = getPortfolioBySessionId(request);

        model.addAttribute(portfolio);

        return "/portfolio/folio_make_json";
    }


    @GetMapping("/star")
    public String starChange(HttpServletRequest request, @RequestParam long id){
        Portfolio portfolio = portfolioService.searchWithId(id);
        Member member = getLoginMember(request);

        portfolioService.starChange(portfolio, member);

        return "redirect:/";
    }

    @GetMapping("/scrap")
    public String scrapPortfolio (HttpServletRequest request, @RequestParam long id){
        Portfolio portfolio = portfolioService.searchWithId(id);
        Member member = getLoginMember(request);

        portfolioService.scrapPortfolio(portfolio, member);

        return "redirect:/";
    }

    // 포트폴리오 찾기 함수
    private Portfolio getPortfolioBySessionId(HttpServletRequest request){
        HttpSession session = request.getSession();
        long memberId = (long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return portfolioService.searchWithMember(memberRepository.findById(memberId).get());
    }


    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession();
        long memberId = (long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return memberRepository.findById(memberId).get();
    }

    private Long getLoginMemberId(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (long) session.getAttribute(SessionConst.LOGIN_MEMBER);
    }

    private PortfolioLoadForm portfolioToPortfolioLoadForm(Portfolio p){
        return new PortfolioLoadForm(
                p.getContents(),
                p.getMember().getNickname(),
                p.getStarTally(),
                p.getScrapTally(),
                p.getUpdatedDate(),
                p.getMember().getInterestField(),
                p.getId());
    }
}
