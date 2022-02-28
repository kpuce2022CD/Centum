package centum.boxfolio.service.portfolio;


import centum.boxfolio.controller.portfolio.PortfolioSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;

import java.util.List;

public interface PortfolioService {
    Portfolio upload(PortfolioSaveForm form, long memberId);
    void delete(Portfolio portfolio);
    void change(Portfolio portfolio, String title, String context, boolean visibility);
    void starChange(Portfolio portfolio, Member member);

    List<Portfolio> searchWithTitle (String title);
    Portfolio searchWithMember (Member member);
}
