package centum.boxfolio.service.portfolio;


import centum.boxfolio.controller.portfolio.PortfolioSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    Portfolio upload(PortfolioSaveForm form, long memberId) throws IOException;
    void delete(Portfolio portfolio);
    void change(Portfolio portfolio, String title, String context, boolean visibility) throws IOException;
    void starChange(Portfolio portfolio, Member member);
    List<Portfolio> searchWithTitle (String title);
    Portfolio searchWithMember (Member member);
    Portfolio searchWithId(Long id);
    void scrapPortfolio (Portfolio portfolio, Member member);
    List<Portfolio> searchHighestStar (int count);
    Portfolio searchWithId (long Id);

}
