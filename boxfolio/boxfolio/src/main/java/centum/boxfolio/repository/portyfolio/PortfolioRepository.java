package centum.boxfolio.repository.portyfolio;

import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;


import java.util.List;


public interface PortfolioRepository {
    Portfolio save (Portfolio portfolio);
    List<Portfolio> getHighestPortfolioList(int count);
    Portfolio getById(int id);
    void delete (Portfolio portfolio);
    void upStar(Portfolio portfolio, Member member);
    void downStar(Portfolio portfolio, Member member);
}
