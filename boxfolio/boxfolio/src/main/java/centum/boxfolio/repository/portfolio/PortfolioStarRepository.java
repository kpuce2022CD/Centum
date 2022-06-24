package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.board.Post;
import centum.boxfolio.entity.board.PostStar;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioStar;

import java.util.List;
import java.util.Optional;

public interface PortfolioStarRepository {
    PortfolioStar upStar(Portfolio portfolio, Member member);
    void downStar(Portfolio portfolio, Member member);
    List<PortfolioStar> findAll();
    Optional<PortfolioStar> findByPortfolioIdAndMemberId(Long portfolioId, Long memberId);
}
