package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;

import java.util.List;
import java.util.Optional;

public interface PortfolioScrapRepository {
    PortfolioScrap upScrap(Portfolio portfolio, Member member);
    void downScrap(Portfolio portfolio, Member member);

    List<PortfolioScrap> findAll();
    Optional<PortfolioScrap> findByPortfolioIdAndMemberId(Long portfolioId, Long memberId);
    List<PortfolioScrap> findByMemberId(Long memberId);
}
