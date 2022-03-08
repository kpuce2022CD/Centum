package centum.boxfolio.repository.portfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioScrap;


import java.util.List;


public interface PortfolioRepository {
    Portfolio save (Portfolio portfolio);
    List<Portfolio> getHighestPortfolioList(int count);
    Portfolio findById(long id);
    List<Portfolio> findByTitle(String title);
    Portfolio findByMember(Member member);
    List<Portfolio> findHighest ();
    void delete (Portfolio portfolio);
    void relationStar (Portfolio portfolio, Member member);

    void relationScrap(Portfolio portfolio, Member member);
    List<PortfolioScrap> findMyScrap(Member member);
}
