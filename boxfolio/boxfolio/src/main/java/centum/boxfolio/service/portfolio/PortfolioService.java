package centum.boxfolio.service.portfolio;


import centum.boxfolio.controller.portfolio.PortfolioSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import centum.boxfolio.entity.portfolio.PortfolioFiles;

import java.io.IOException;
import java.util.List;

public interface PortfolioService {
    Portfolio upload(PortfolioSaveForm form, long memberId) throws IOException;
    void delete(Portfolio portfolio);
    void change(Portfolio portfolio, String title, String context, boolean visibility) throws IOException;
    void starChange(Portfolio portfolio, Member member);

    List<Portfolio> searchWithTitle (String title);
    Portfolio searchWithMember (Member member);
    List<Portfolio> searchHighestStar (int count);
    List<PortfolioFiles> findPortfolioFiles(Portfolio portfolio);
}
