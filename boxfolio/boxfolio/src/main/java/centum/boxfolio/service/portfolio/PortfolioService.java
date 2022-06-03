package centum.boxfolio.service.portfolio;


import centum.boxfolio.controller.portfolio.PortfolioSaveForm;
import centum.boxfolio.entity.member.Member;
import centum.boxfolio.entity.portfolio.Portfolio;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PortfolioService {
    Portfolio upload(PortfolioSaveForm form, Long memberId) throws IOException, ParseException;
    void delete(Portfolio portfolio);
    void change(Portfolio portfolio, String title, String context, boolean visibility) throws IOException;
    void starChange(Portfolio portfolio, Member member);
    List<Portfolio> searchWithTitle (String title);
    Portfolio searchWithMember (Member member);
    Optional<Portfolio> findByMemberId(Long memberId);
    Optional<Portfolio> findById(Long id);
    List<Portfolio> findByNickname(String nickname);
    void scrapPortfolio (Portfolio portfolio, Member member);
    List<Portfolio> searchHighestStarInPublic(int count);

    List<Portfolio> searchLatestInPublic();

}
