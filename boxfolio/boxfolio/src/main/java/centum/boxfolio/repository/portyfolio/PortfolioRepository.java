package centum.boxfolio.repository.portyfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;


import java.util.List;


public interface PortfolioRepository {
    Portfolio save (Portfolio portfolio, Member member);
    List<Portfolio> getHighestPortfolioList(int count);
    Portfolio getById(long id);
    void delete (Portfolio portfolio);
    void changeStar (Portfolio portfolio, Member member, boolean upDown);
}
