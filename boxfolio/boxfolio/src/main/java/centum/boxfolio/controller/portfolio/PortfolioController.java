package centum.boxfolio.controller.portfolio;


import centum.boxfolio.controller.member.SessionConst;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.repository.member.MemberRepository;
import centum.boxfolio.repository.portfolio.PortfolioRepository;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.portfolio.PortfolioService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final MemberRepository memberRepository;
    private final PortfolioRepository portfolioRepository;

    @GetMapping
    public String portfolioPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Optional<Portfolio> myPortfolio = portfolioService.searchByMemberId(memberId);

        List<Portfolio> highestPortfolioList = portfolioService.searchHighestStarInPublic(5);
        List<Portfolio> normalPortfolioList = portfolioService.searchLatestInPublic();

        model.addAttribute("highestPortfolioList", highestPortfolioList);
        model.addAttribute("normalPortfolioList", normalPortfolioList);
        model.addAttribute("myPortfolio", myPortfolio.orElse(null));

        return "/portfolio/portfolios";
    }

    @GetMapping("/make")
    public String uploadPortfolio(Model model){
        model.addAttribute("portfolioSaveForm", new PortfolioSaveForm());
        return "/portfolio/portfolio_edit";
    }

    @PostMapping("/make")
    public String uploadPortfolio(@Valid PortfolioSaveForm portfolioSaveForm, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  HttpServletRequest request) {
        if (bindingResult.hasErrors()){
            for (ObjectError e : bindingResult.getAllErrors()){
                log.info("error" + e.toString());
            }
            return "redirect:/";
        }

        try{
            HttpSession session = request.getSession(false);
            Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
            log.info(portfolioSaveForm.getContents());

            Portfolio uploadPortfolio = portfolioService.upload(portfolioSaveForm, memberId);

            redirectAttributes.addAttribute("id", uploadPortfolio.getId());
        } catch (IllegalStateException e){
            bindingResult.reject("upload portfolio failed", e.getMessage());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        log.info("post success");

        return "redirect:/portfolio/{id}";
    }

    @GetMapping("/{id}")
    public String portfolioPage(@PathVariable Long id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Portfolio> portfolio = portfolioService.searchById(id);

        if (portfolio.isEmpty() || member.isEmpty()) {
            return "redirect:/portfolio";
        }

        if (portfolio.get().getVisibility() == false && portfolio.get().getMember().getId() != memberId) {
            return "redirect:/portfolio";
        }

        model.addAttribute("portfolio", portfolio.get());
        model.addAttribute("member", member.get());
        return "/portfolio/portfolio";
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


    @GetMapping("/delete/{id}")
    public String deletePortfolio(@PathVariable Long id, HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession(false);
        Long memberId = Long.parseLong(session.getAttribute(SessionConst.LOGIN_MEMBER).toString());
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        if (portfolio.isEmpty() || portfolio.get().getMember().getId() != memberId) {
            return "redirect:/portfolio";
        }

        portfolioService.delete(portfolio.get());
        return "redirect:/portfolio";
    }

    @GetMapping("/update")
    public String updatePortfolio(HttpServletRequest request, Model model){

        Portfolio portfolio = getPortfolioBySessionId(request);

        model.addAttribute(portfolio);

        return "/portfolio/folio_make_json";
    }


    @GetMapping("/star")
    public String starChange(HttpServletRequest request, @RequestParam long id){
        Portfolio portfolio = portfolioService.searchById(id).get();
        Member member = getLoginMember(request);

        portfolioService.starChange(portfolio, member);

        return "redirect:/";
    }

    @GetMapping("/scrap")
    public String scrapPortfolio (HttpServletRequest request, @RequestParam long id){
        Portfolio portfolio = portfolioService.searchById(id).get();
        Member member = getLoginMember(request);

        portfolioService.scrapPortfolio(portfolio, member);

        return "redirect:/";
    }

    // 포트폴리오 찾기 함수
    private Portfolio getPortfolioBySessionId(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long memberId = (long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return portfolioService.searchWithMember(memberRepository.findById(memberId).get());
    }


    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long memberId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        return memberRepository.findById(memberId).get();
    }

    private Long getLoginMemberId(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);
    }

    private PortfolioLoadForm portfolioToPortfolioLoadForm(Portfolio p){
        return new PortfolioLoadForm(
            p.getContents(),
            p.getMember().getNickname(),
            p.getStarTally(),
            p.getScrapTally(),
            p.getUpdatedDate(),
            p.getMember().getInterestField(),
            p.getId(),
            p.getTitle()
        );
    }
}
