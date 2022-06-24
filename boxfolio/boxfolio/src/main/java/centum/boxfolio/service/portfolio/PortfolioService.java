package centum.boxfolio.service.portfolio;


import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostScrap;
import centum.boxfolio.entity.board.PostStar;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioRow;
import centum.boxfolio.entity.portfolio.PortfolioScrap;
import centum.boxfolio.entity.portfolio.PortfolioStar;

import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    Portfolio save(Portfolio portfolio);
    List<PortfolioRow> savePortfolioRows(Portfolio portfolio, List<PortfolioRow> portfolioRows);

    void delete(Portfolio portfolio);

    Portfolio findByMemberId(Long memberId);
    Portfolio findById(Long id);
    List<Portfolio> findByNickname(String nickname);

    List<PortfolioRow> findPortfolioRowsByPortfolioId(Long portfolioId);

    List<Portfolio> findPortfoliosOrderByStarDesc(int count);
    List<Portfolio> findPortfoliosOrderByUpdatedDateAsc();
    List<Portfolio> findPortfoliosByNickname(String nickname);
    List<Portfolio> findPortfoliosByTitle(String title);

    PortfolioStar countStar(Portfolio portfolio, Member member);
    PortfolioScrap countScrap(Portfolio portfolio, Member member);
    List<PortfolioScrap> findScrapsByMemberId(Long memberId);
}
