package centum.boxfolio.controller.portfolio;

import centum.boxfolio.dto.portfolio.PortfolioDto;
import centum.boxfolio.dto.portfolio.PortfolioRowDto;
import centum.boxfolio.dto.portfolio.PortfolioScrapDto;
import centum.boxfolio.dto.portfolio.PortfolioStarDto;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioRow;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.response.Response;
import centum.boxfolio.service.member.MemberService;
import centum.boxfolio.service.portfolio.PortfolioService;
import centum.boxfolio.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping
    public Response<List<PortfolioDto>> getPortfolios(@RequestParam(defaultValue = "") String type, @RequestParam(defaultValue = "")String query) {
        List<Portfolio> portfolios = portfolioSearchAdapter(type, query);
        List<PortfolioDto> portfolioDtos = convertPortfoliosToPortfolioDtos(portfolios);
        return responseService.getResult("portfolios", portfolioDtos);
    }

    private List<Portfolio> portfolioSearchAdapter(String type, String query) {
        if (type.equals("nickname")) {
            return portfolioService.findPortfoliosByNickname(query);
        }
        if (type.equals("title")) {
            return portfolioService.findPortfoliosByTitle(query);
        }
        return portfolioService.findPortfoliosOrderByUpdatedDateAsc();
    }

    @GetMapping("/best")
    public Response<List<PortfolioDto>> getBestPortfolios() {
        List<Portfolio> portfolios = portfolioService.findPortfoliosOrderByStarDesc(4);
        List<PortfolioDto> portfolioDtos = convertPortfoliosToPortfolioDtos(portfolios);
        return responseService.getResult("bestPortfolios", portfolioDtos);
    }

    @GetMapping("/{id}")
    public Response<PortfolioDto> getPortfolio(@PathVariable Long id) {
        return responseService.getResult("portfolio", convertPortfolioToPortfolioDto(portfolioService.findById(id)));
    }

    @PostMapping
    public Response<PortfolioDto> savePortfolio(@RequestBody @Validated PortfolioDto portfolioDto, Principal principal) {
        Member member = memberService.findByLoginId(principal.getName());

        if (portfolioService.findByMemberId(member.getId()) != null) {
            return responseService.getFailResult(ErrorType.PORTFOLIO_EXISTS);
        }

        Portfolio portfolio = Portfolio.builder()
                .title(portfolioDto.getTitle())
                .visibility(portfolioDto.getVisibility())
                .member(member)
                .build();
        List<PortfolioRow> portfolioRows = portfolioDto.getPortfolioRows().stream()
                .map(portfolioRowDto -> PortfolioRow.builder()
                        .rowType(portfolioRowDto.getRowType())
                        .saveType(portfolioRowDto.getSaveType())
                        .contents(portfolioRowDto.getContents())
                        .rowOrder(portfolioRowDto.getRowOrder())
                        .portfolio(portfolio)
                        .build())
                .collect(Collectors.toList());

        Portfolio savedPortfolio = portfolioService.save(portfolio);
        portfolioService.savePortfolioRows(savedPortfolio, portfolioRows);

        PortfolioDto savedPortfolioDto = convertPortfolioToPortfolioDto(savedPortfolio);

        return responseService.getResult("portfolio", savedPortfolioDto);
    }

    @DeleteMapping("/{id}")
    public Response deletePortfolio(@PathVariable Long id) {
        portfolioService.delete(portfolioService.findById(id));
        return responseService.getSuccessResult();
    }

    @GetMapping("/{id}/star")
    public Response countStar(@PathVariable Long id, Principal principal) {
        Portfolio portfolio = portfolioService.findById(id);
        Member member = memberService.findByLoginId(principal.getName());

        PortfolioStar portfolioStar = portfolioService.countStar(portfolio, member);
        PortfolioStarDto portfolioStarDto = null;
        if (portfolioStar != null) {
            portfolioStarDto = PortfolioStarDto.builder()
                    .id(portfolioStar.getId())
                    .portfolioId(portfolioStar.getPortfolio().getId())
                    .memberId(portfolioStar.getMember().getId())
                    .build();
        }
        return responseService.getResult("portfolioStar", portfolioStarDto);
    }

    @GetMapping("/{id}/scrap")
    public Response countScrap(@PathVariable Long id, Principal principal) {
        Portfolio portfolio = portfolioService.findById(id);
        Member member = memberService.findByLoginId(principal.getName());

        PortfolioScrap portfolioScrap = portfolioService.countScrap(portfolio, member);
        PortfolioScrapDto portfolioScrapDto = null;
        if (portfolioScrap != null) {
            portfolioScrapDto = PortfolioScrapDto.builder()
                    .id(portfolioScrap.getId())
                    .portfolioId(portfolioScrap.getPortfolio().getId())
                    .portfolioTitle(portfolioScrap.getPortfolio().getTitle())
                    .memberId(portfolioScrap.getMember().getId())
                    .build();
        }

        return responseService.getResult("portfolioScrap", portfolioScrapDto);
    }

    private List<PortfolioDto> convertPortfoliosToPortfolioDtos(List<Portfolio> portfolios) {
        List<PortfolioDto> portfolioDtos = portfolios.stream()
                .map(portfolio -> {
                    List<PortfolioRowDto> portfolioRowDtos = portfolioService.findPortfolioRowsByPortfolioId(portfolio.getId()).stream()
                            .map(portfolioRow -> PortfolioRowDto.builder()
                                    .id(portfolioRow.getId())
                                    .rowType(portfolioRow.getRowType())
                                    .saveType(portfolioRow.getSaveType())
                                    .contents(portfolioRow.getContents())
                                    .rowOrder(portfolioRow.getRowOrder())
                                    .build())
                            .collect(Collectors.toList());
                    return PortfolioDto.builder()
                            .id(portfolio.getId())
                            .title(portfolio.getTitle())
                            .updatedDate(portfolio.getUpdatedDate())
                            .visibility(portfolio.getVisibility())
                            .starTally(portfolio.getStarTally())
                            .scrapTally(portfolio.getScrapTally())
                            .member(portfolio.getMember())
                            .portfolioRows(portfolioRowDtos)
                            .build();
                })
                .collect(Collectors.toList());
        return portfolioDtos;
    }

    private PortfolioDto convertPortfolioToPortfolioDto(Portfolio portfolio) {
        List<PortfolioRowDto> portfolioRowDtos = portfolioService.findPortfolioRowsByPortfolioId(portfolio.getId()).stream()
                .map(portfolioRow -> PortfolioRowDto.builder()
                        .id(portfolioRow.getId())
                        .rowType(portfolioRow.getRowType())
                        .saveType(portfolioRow.getSaveType())
                        .contents(portfolioRow.getContents())
                        .rowOrder(portfolioRow.getRowOrder())
                        .build())
                .collect(Collectors.toList());
        PortfolioDto portfolioDto = PortfolioDto.builder()
                .id(portfolio.getId())
                .title(portfolio.getTitle())
                .updatedDate(portfolio.getUpdatedDate())
                .visibility(portfolio.getVisibility())
                .starTally(portfolio.getStarTally())
                .scrapTally(portfolio.getScrapTally())
                .member(portfolio.getMember())
                .portfolioRows(portfolioRowDtos)
                .build();
        return portfolioDto;
    }
}
