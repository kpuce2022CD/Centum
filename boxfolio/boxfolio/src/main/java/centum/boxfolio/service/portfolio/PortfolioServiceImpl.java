package centum.boxfolio.service.portfolio;

import centum.boxfolio.controller.portfolio.PortfolioRowConst;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioRow;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.entity.portfolio.PortfolioStar;
import centum.boxfolio.exception.ErrorType;
import centum.boxfolio.exception.PortfolioException;
import centum.boxfolio.repository.portfolio.PortfolioRepository;
import centum.boxfolio.repository.portfolio.PortfolioScrapRepository;
import centum.boxfolio.repository.portfolio.PortfolioStarRepository;
import centum.boxfolio.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService{

    private final PortfolioRepository portfolioRepository;
    private final PortfolioStarRepository portfolioStarRepository;
    private final PortfolioScrapRepository portfolioScrapRepository;
    private final ProjectService projectService;

    @Override
    public Portfolio save(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    @Override
    public List<PortfolioRow> savePortfolioRows(Portfolio portfolio, List<PortfolioRow> portfolioRows) {
        List<PortfolioRow> portfolioRowList = new ArrayList<>();
        portfolioRows.stream()
                .forEach(portfolioRow -> {
                    PortfolioRow row = portfolioRepository.savePortfolioRow(portfolioRow);
                    if (row.getRowType().equals(PortfolioRowConst.GITHUB)) {
                        projectService.saveCompleteProjectByPortfolio(portfolio, portfolio.getMember(), row.getContents());
                    }
                    portfolioRowList.add(row);
                });
        return portfolioRowList;
    }

    @Override
    public void delete(Portfolio portfolio) {
        portfolioRepository.delete(portfolio);
    }

    @Override
    public Portfolio findByMemberId(Long memberId) {
        Optional<Portfolio> portfolio = portfolioRepository.findByMemberId(memberId);
        if (portfolio.isEmpty()) {
            throw new PortfolioException(ErrorType.PORTFOLIO_NOT_EXISTS);
        }
        return portfolio.get();
    }

    @Override
    public Portfolio findById(Long id) {
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        if (portfolio.isEmpty()) {
            throw new PortfolioException(ErrorType.PORTFOLIO_NOT_EXISTS);
        }
        return portfolio.get();
    }

    @Override
    public List<Portfolio> findByNickname(String nickname) {
        return portfolioRepository.findByNickname(nickname);
    }

    @Override
    public List<PortfolioRow> findPortfolioRowsByPortfolioId(Long portfolioId) {
        return portfolioRepository.findPortfolioRowsByPortfolioId(portfolioId);
    }

    @Override
    public List<Portfolio> findPortfoliosOrderByStarDesc(int count) {
        return portfolioRepository.findOrderByStarDesc();
    }


    @Override
    public List<Portfolio> findPortfoliosOrderByUpdatedDateAsc() {
        return portfolioRepository.findOrderByUpdatedDateAsc();
    }

    @Override
    public List<Portfolio> findPortfoliosByNickname(String nickname) {
        return portfolioRepository.findByNickname(nickname);
    }

    @Override
    public List<Portfolio> findPortfoliosByTitle(String title) {
        return portfolioRepository.findByTitle(title);
    }

    @Override
    public PortfolioStar countStar(Portfolio portfolio, Member member) {
        Optional<PortfolioStar> portfolioStar = portfolioStarRepository.findByPortfolioIdAndMemberId(portfolio.getId(), member.getId());

        if (portfolioStar.isEmpty()) {
            return portfolioStarRepository.upStar(portfolio, member);
        }
        portfolioStarRepository.downStar(portfolio, member);
        return null;
    }

    @Override
    public PortfolioScrap countScrap(Portfolio portfolio, Member member) {
        Optional<PortfolioScrap> portfolioScrap = portfolioScrapRepository.findByPortfolioIdAndMemberId(portfolio.getId(), member.getId());

        if (portfolioScrap.isEmpty()) {
            return portfolioScrapRepository.upScrap(portfolio, member);
        }
        portfolioScrapRepository.downScrap(portfolio, member);
        return null;
    }

    @Override
    public List<PortfolioScrap> findScrapsByMemberId(Long memberId) {
        return portfolioScrapRepository.findByMemberId(memberId);
    }
}
