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
    void delete (Portfolio portfolio);
    void changeStar (Portfolio portfolio, Member member, boolean upDown);

    void relationScrap(Portfolio portfolio, Member member);
    List<PortfolioScrap> findMyScrap(Member member);
}
